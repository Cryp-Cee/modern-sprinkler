package com.modern_sprinkler.block;

import com.modern_sprinkler.blockentity.NetheriteSprinklerHeadBlockEntity;
import com.modern_sprinkler.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class NetheriteSprinklerHeadBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    private static final VoxelShape OUTLINE = box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    private final boolean activeVariant;

    public NetheriteSprinklerHeadBlock(boolean activeVariant) {
        super(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.NETHERITE_BLOCK).noOcclusion());
        this.activeVariant = activeVariant;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public boolean isActiveVariant() { return activeVariant; }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b){ b.add(FACING); }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.NETHERITE_SPRINKLER_HEAD_BE.get().create(pos, state);
    }

    @Override public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override public BlockState rotate(BlockState s, Rotation r){ return s.setValue(FACING, r.rotate(s.getValue(FACING))); }
    @Override public BlockState mirror(BlockState s, Mirror m){ return s.rotate(m.getRotation(s.getValue(FACING))); }

    @Override public VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c){ return OUTLINE; }
    @Override public VoxelShape getInteractionShape(BlockState s, BlockGetter l, BlockPos p){ return OUTLINE; }
    @Override public VoxelShape getCollisionShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c){ return Shapes.empty(); }

    @Override public boolean canSurvive(BlockState s, LevelReader l, BlockPos p){ return !l.getBlockState(p.below()).isAir(); }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level lvl, BlockState s, BlockEntityType<T> type) {
        return lvl.isClientSide
                ? createTickerHelper(type, ModBlockEntities.NETHERITE_SPRINKLER_HEAD_BE.get(), NetheriteSprinklerHeadBlockEntity::clientTick)
                : createTickerHelper(type, ModBlockEntities.NETHERITE_SPRINKLER_HEAD_BE.get(), NetheriteSprinklerHeadBlockEntity::serverTick);
    }
}
