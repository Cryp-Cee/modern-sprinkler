package com.modernsprinkler.block;

import com.modernsprinkler.block.entity.SprinklerHeadBlockEntity; // NEUER IMPORT
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState; // NEUER IMPORT
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;


public class SprinklerHeadBlock extends BaseEntityBlock {
    public SprinklerHeadBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .strength(2.0f, 6.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
                .noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        // GEÄNDERT: Gibt jetzt das neue "Gehirn" zurück
        return new SprinklerHeadBlockEntity(pPos, pState);
    }
    // ... (Rest der Datei bleibt gleich)
}