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
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DiamondSprinklerHeadBlockEntity extends BlockEntity implements GeoAnimatable {

    private static final int SPRAY_INTERVAL_TICKS = 5;
    private static final int RADIUS_BLOCKS = 3;      // Diamond → 7x7
    private static final int RAYS_PER_BURST = 12;
    private static final int PARTICLES_PER_RAY = 3;
    private static final float ROTATION_SPEED_DEG = 6.0f;

    private final AnimatableInstanceCache geckoCache = GeckoLibUtil.createInstanceCache(this);
    private float rotationDeg = 0f;
    private boolean lastActive = false;
    private int tickCounter = 0;

    public DiamondSprinklerHeadBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DIAMOND_SPRINKLER_HEAD_BE.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, DiamondSprinklerHeadBlockEntity be) {
        if (level == null || level.isClientSide) return;
        boolean active = be.isActiveServer();
        be.tickCounter++;
        if (active && (be.tickCounter % SPRAY_INTERVAL_TICKS == 0)) be.spawnSprayParticles((ServerLevel) level);
        if (active != be.lastActive) { be.lastActive = active; be.setChanged(); level.sendBlockUpdated(pos, state, state, 3); }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, DiamondSprinklerHeadBlockEntity be) {
        if (level == null || !level.isClientSide) return;
        if (be.isActiveClient()) be.rotationDeg = (be.rotationDeg + ROTATION_SPEED_DEG) % 360f;
    }

    private boolean isActiveServer() {
        if (this.level == null) return false;
        if (!this.getBlockState().is(ModBlocks.DIAMOND_SPRINKLER_HEAD_ACTIVE.get())) return false;
        BlockEntity below = this.level.getBlockEntity(this.worldPosition.below());
        if (below instanceof SprinklerBottomBlockEntity pump) return pump.hasCore();
        return false;
    }

    private boolean isActiveClient() {
        return this.getBlockState().is(ModBlocks.DIAMOND_SPRINKLER_HEAD_ACTIVE.get());
    }

    private void spawnSprayParticles(ServerLevel sLevel) {
        final double ox = this.worldPosition.getX() + 0.5, oy = this.worldPosition.getY() + 0.9, oz = this.worldPosition.getZ() + 0.5;
        for (int i = 0; i < RAYS_PER_BURST; i++) {
            double ang = Math.toRadians((i * (360.0 / RAYS_PER_BURST)) + this.rotationDeg);
            double sx = ox + Math.cos(ang) * 0.25, sz = oz + Math.sin(ang) * 0.25;
            double dist = 0.6 + sLevel.random.nextDouble() * (RADIUS_BLOCKS + 0.4);
            for (int p = 0; p < PARTICLES_PER_RAY; p++) {
                double aj = ang + (sLevel.random.nextDouble() - 0.5) * 0.20;
                double dx = Math.cos(aj) * dist, dz = Math.sin(aj) * dist;
                sLevel.sendParticles(ParticleTypes.SPLASH, sx + dx, oy, sz + dz, 1, 0.01, 0.01, 0.01, 0.05);
            }
        }
    }

    private static final RawAnimation ANIM_IDLE   = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation ANIM_ROTATE = RawAnimation.begin().thenLoop("rotate");

    @Override public void registerControllers(AnimatableManager.ControllerRegistrar ctr) {
        ctr.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }
    private <E extends DiamondSprinklerHeadBlockEntity> PlayState predicate(AnimationState<E> s) {
        s.setAnimation(isActiveClient() ? ANIM_ROTATE : ANIM_IDLE); return PlayState.CONTINUE;
    }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache(){ return geckoCache; }
    @Override public double getTick(Object a){ return AnimationTickHolder.getTick(this); }
    public float getRotationDeg(){ return Mth.wrapDegrees(this.rotationDeg); }
}
