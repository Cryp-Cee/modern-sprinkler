package com.modernsprinkler.block.entity;

import com.modernsprinkler.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SprinklerHeadBlockEntity extends BlockEntity {
    public SprinklerHeadBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.SPRINKLER_HEAD_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
}