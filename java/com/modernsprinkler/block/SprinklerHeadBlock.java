package com.modernsprinkler.block;

import com.modernsprinkler.block.entity.SprinklerHeadBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SprinklerHeadBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(6, 0, 6, 10, 8, 10),
            Block.box(5, 8, 5, 11, 12, 11)
    );

    public SprinklerHeadBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .strength(2.0f, 6.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SprinklerHeadBlockEntity(pPos, pState);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        // HIER IST DIE KORREKTUR:
        // Wir sagen Minecraft, dass es den Block unsichtbar machen soll,
        // damit GeckoLib das Rendern komplett übernehmen kann.
        return RenderShape.INVISIBLE;
    }
}