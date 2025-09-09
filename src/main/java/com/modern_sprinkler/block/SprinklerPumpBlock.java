package com.modern_sprinkler.block;

import com.modern_sprinkler.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SprinklerPumpBlock extends BaseEntityBlock {
    public SprinklerPumpBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.SPRINKLER_PUMP_BE.get().create(pos, state);
    }
}
