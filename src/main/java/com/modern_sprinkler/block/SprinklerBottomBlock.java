package com.modern_sprinkler.block;

import com.modern_sprinkler.blockentity.SprinklerBottomBlockEntity;
import com.modern_sprinkler.registry.ModBlockEntities;
import com.modern_sprinkler.screen.ModMenuTypes;
import com.modern_sprinkler.screen.SprinklerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class SprinklerBottomBlock extends BaseEntityBlock {
    public SprinklerBottomBlock(Properties props) { super(props); }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.SPRINKLER_BOTTOM_BE.get().create(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MenuProvider) {
                NetworkHooks.openScreen((net.minecraft.server.level.ServerPlayer) player, (MenuProvider) be, pos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
