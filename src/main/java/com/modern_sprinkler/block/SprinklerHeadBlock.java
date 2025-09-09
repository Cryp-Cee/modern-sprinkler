package com.modern_sprinkler.block;

import com.modern_sprinkler.block.entity.ModBlockEntities;
import com.modern_sprinkler.block.entity.SprinklerHeadBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SprinklerHeadBlock extends BaseEntityBlock {
    public SprinklerHeadBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockBelow = pLevel.getBlockState(pPos.below());
        return blockBelow.is(ModBlocks.SPRINKLER_BOTTOM.get());
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!canSurvive(pState, pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        // WICHTIG: Dies weist Minecraft an, unseren custom Renderer zu verwenden
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SprinklerHeadBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.SPRINKLER_HEAD_BE.get(),
                SprinklerHeadBlockEntity::tick);
    }
}