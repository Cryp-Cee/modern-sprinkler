package com.modern_sprinkler.block.entity;

import com.modern_sprinkler.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SprinklerHeadBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation SPIN_ANIM = RawAnimation.begin().thenLoop("animation.sprinkler.spin");

    public SprinklerHeadBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SPRINKLER_HEAD_BE.get(), pPos, pBlockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<SprinklerHeadBlockEntity> state) {
        if(state.getAnimatable().getBlockState().is(ModBlocks.IRON_SPRINKLER_HEAD.get())) {
            state.getController().setAnimation(SPIN_ANIM);
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SprinklerHeadBlockEntity pBlockEntity) {
        if (pLevel.isClientSide()) {
            return;
        }

        if (pLevel.getGameTime() % 20 == 0) {
            BlockEntity belowEntity = pLevel.getBlockEntity(pPos.below());

            if (belowEntity instanceof SprinklerBlockEntity sprinklerBase) {
                sprinklerBase.getCapability(net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                    if (!handler.getStackInSlot(0).isEmpty()) {
                        int radius = getRadius(pState.getBlock());
                        if (radius > 0) {
                            moistenFarmland(pLevel, pPos, radius);
                            spawnParticles((ServerLevel) pLevel, pPos, radius);
                        }
                    }
                });
            }
        }
    }

    private static void moistenFarmland(Level level, BlockPos centerPos, int radius) {
        BlockPos.betweenClosedStream(centerPos.offset(-radius, -2, -radius), centerPos.offset(radius, -1, radius))
             .forEach(pos -> {
                    BlockState blockState = level.getBlockState(pos);
                    if (blockState.is(Blocks.FARMLAND)) {
                        int moisture = blockState.getValue(FarmBlock.MOISTURE);
                        if (moisture < 7) {
                            level.setBlock(pos, blockState.setValue(FarmBlock.MOISTURE, 7), 3);
                        }
                    }
                });
    }

    private static void spawnParticles(ServerLevel level, BlockPos pos, int radius) {
        for (int i = 0; i < radius * 5; i++) {
            double offsetX = (level.random.nextDouble() - 0.5) * (radius * 2);
            double offsetZ = (level.random.nextDouble() - 0.5) * (radius * 2);
            double startX = pos.getX() + 0.5 + offsetX;
            double startY = pos.getY() + 0.8;
            double startZ = pos.getZ() + 0.5 + offsetZ;

            double speed = 0.15;
            double motionX = -offsetX / (radius * 10 / speed);
            double motionY = 0.1;
            double motionZ = -offsetZ / (radius * 10 / speed);

            level.sendParticles(ParticleTypes.DRIPPING_WATER, startX, startY, startZ, 1, motionX, motionY, motionZ, 0.1);
        }
    }

    private static int getRadius(Block block) {
        if (block == ModBlocks.IRON_SPRINKLER_HEAD.get()) return 3;
        if (block == ModBlocks.GOLD_SPRINKLER_HEAD.get()) return 5;
        if (block == ModBlocks.DIAMOND_SPRINKLER_HEAD.get()) return 7;
        if (block == ModBlocks.NETHERITE_SPRINKLER_HEAD.get()) return 9;
        return 0;
    }
}