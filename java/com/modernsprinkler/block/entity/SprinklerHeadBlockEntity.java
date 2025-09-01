package com.modernsprinkler.block.entity;

import com.modernsprinkler.core.init.BlockEntityInit;
import com.modernsprinkler.core.init.ParticleInit; // NEUER IMPORT
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SprinklerHeadBlockEntity extends BlockEntity {
    private float rotation = 0.0f;
    private final RandomSource random = RandomSource.create();

    public SprinklerHeadBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.SPRINKLER_HEAD_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SprinklerHeadBlockEntity pBlockEntity) {
        if (pLevel.isClientSide()) {
            pBlockEntity.updateRotationAndSpawnParticles();
        }
    }

    private void updateRotationAndSpawnParticles() {
        if (this.level == null) return;

        this.rotation += 15.0f;
        if (this.rotation >= 360.0f) {
            this.rotation -= 360.0f;
        }
        spawnParticle(this.rotation);
    }

    private void spawnParticle(float angle) {
        double startX = this.worldPosition.getX() + 0.5;
        double startY = this.worldPosition.getY() + 0.8;
        double startZ = this.worldPosition.getZ() + 0.5;

        float angleRad = (float) Math.toRadians(angle);

        double speed = 0.4; 
        double motionX = Math.cos(angleRad) * speed;
        double motionZ = Math.sin(angleRad) * speed;
        double motionY = 0.2;

        // GEÄNDERT: Wir benutzen jetzt unseren eigenen, registrierten Partikel-Typ!
        this.level.addParticle(ParticleInit.WATER_DROP.get(), startX, startY, startZ, motionX, motionY, motionZ);
    }
}