package com.modern_sprinkler.blockentity;

import com.modern_sprinkler.registry.ModBlockEntities;
import com.modern_sprinkler.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animation.AnimationTickHolder;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class IronSprinklerHeadBlockEntity extends BlockEntity implements GeoAnimatable {

    private static final int SPRAY_INTERVAL_TICKS = 5;
    private static final int RADIUS_BLOCKS = 1;      // Iron → 3x3
    private static final int RAYS_PER_BURST = 12;
    private static final int PARTICLES_PER_RAY = 3;
    private static final float ROTATION_SPEED_DEG = 6.0f;

    private final AnimatableInstanceCache geckoCache = GeckoLibUtil.createInstanceCache(this);
    private float rotationDeg = 0f;
    private boolean lastActive = false;
    private int tickCounter = 0;

    public IronSprinklerHeadBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_SPRINKLER_HEAD_BE.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, IronSprinklerHeadBlockEntity be) {
        if (level == null || level.isClientSide) return;

        boolean active = be.isActiveServer();
        be.tickCounter++;

        if (active && (be.tickCounter % SPRAY_INTERVAL_TICKS == 0)) {
            be.spawnSprayParticles((ServerLevel) level);
        }

        if (active != be.lastActive) {
            be.lastActive = active;
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, IronSprinklerHeadBlockEntity be) {
        if (level == null || !level.isClientSide) return;
        if (be.isActiveClient()) {
            be.rotationDeg = (be.rotationDeg + ROTATION_SPEED_DEG) % 360f;
        }
    }

    private boolean isActiveServer() {
        if (this.level == null) return false;
        boolean isActiveHead = this.getBlockState().is(ModBlocks.IRON_SPRINKLER_HEAD_ACTIVE.get());
        if (!isActiveHead) return false;
        BlockEntity below = this.level.getBlockEntity(this.worldPosition.below());
        if (below instanceof SprinklerBottomBlockEntity pump) {
            return pump.hasCore();
        }
        return false;
    }

    private boolean isActiveClient() {
        return this.getBlockState().is(ModBlocks.IRON_SPRINKLER_HEAD_ACTIVE.get());
    }

    private void spawnSprayParticles(ServerLevel sLevel) {
        final double originX = this.worldPosition.getX() + 0.5;
        final double originY = this.worldPosition.getY() + 0.9;
        final double originZ = this.worldPosition.getZ() + 0.5;

        for (int i = 0; i < RAYS_PER_BURST; i++) {
            double baseAngle = Math.toRadians((i * (360.0 / RAYS_PER_BURST)) + this.rotationDeg);
            double sx = originX + Math.cos(baseAngle) * 0.25;
            double sz = originZ + Math.sin(baseAngle) * 0.25;

            double dist = 0.6 + sLevel.random.nextDouble() * (RADIUS_BLOCKS + 0.4);

            for (int p = 0; p < PARTICLES_PER_RAY; p++) {
                double angleJitter = baseAngle + (sLevel.random.nextDouble() - 0.5) * 0.20;
                double dx = Math.cos(angleJitter) * dist;
                double dz = Math.sin(angleJitter) * dist;

                double px = sx + dx;
                double py = originY;
                double pz = sz + dz;

                sLevel.sendParticles(ParticleTypes.SPLASH, px, py, pz, 1, 0.01, 0.01, 0.01, 0.05);
            }
        }
    }

    // GeckoLib
    private static final RawAnimation ANIM_IDLE   = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation ANIM_ROTATE = RawAnimation.begin().thenLoop("rotate");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    private <E extends IronSprinklerHeadBlockEntity> PlayState animationPredicate(AnimationState<E> state) {
        if (isActiveClient()) state.setAnimation(ANIM_ROTATE);
        else state.setAnimation(ANIM_IDLE);
        return PlayState.CONTINUE;
    }

    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return geckoCache; }
    @Override public double getTick(Object animatable) { return AnimationTickHolder.getTick(this); }
    public float getRotationDeg() { return Mth.wrapDegrees(this.rotationDeg); }
}
