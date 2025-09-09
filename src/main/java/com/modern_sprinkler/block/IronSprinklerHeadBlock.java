package com.modern_sprinkler.block;

import com.modern_sprinkler.blockentity.IronSprinklerHeadBlockEntity;
import com.modern_sprinkler.registry.ModBlockEntities;
import com.modern_sprinkler.registry.ModBlocks;
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

import javax.annotation.Nullable;

public class IronSprinklerHeadBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    private static final VoxelShape OUTLINE = box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    private final boolean activeVariant; // true = _active (mit Farmland-Tag), false = _inactive

    public IronSprinklerHeadBlock(boolean activeVariant) {
        super(BlockBehaviour.Properties.of()
                .strength(2.0F)
                .sound(SoundType.METAL)
                .noOcclusion()); // wichtig gegen schwarze Optik/Lighting
        this.activeVariant = activeVariant;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public boolean isActiveVariant() { return activeVariant; }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.IRON_SPRINKLER_HEAD_BE.get().create(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mir) {
        return state.rotate(mir.getRotation(state.getValue(FACING)));
    }

    // Schlanke Outline/Interaction
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return OUTLINE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return OUTLINE;
    }

    // Keine harte Kollision, damit man nicht „hängenbleibt“
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    // optional: nur über fester Basis (z. B. Pumpe) erlauben
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        // Wenn du exakt nur auf der Pumpe platzieren willst:
        // return below.is(ModBlocks.SPRINKLER_PUMP.get());
        return !below.isAir();
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide
                ? createTickerHelper(type, ModBlockEntities.IRON_SPRINKLER_HEAD_BE.get(),
                    IronSprinklerHeadBlockEntity::clientTick)
                : createTickerHelper(type, ModBlockEntities.IRON_SPRINKLER_HEAD_BE.get(),
                    IronSprinklerHeadBlockEntity::serverTick);
    }
}
