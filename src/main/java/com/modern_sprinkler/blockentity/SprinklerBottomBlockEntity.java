package com.modern_sprinkler.blockentity;

import com.modern_sprinkler.registry.ModBlockEntities;
import com.modern_sprinkler.registry.ModBlocks;
import com.modern_sprinkler.registry.ModItems;
import com.modern_sprinkler.screen.SprinklerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SprinklerBottomBlockEntity extends BlockEntity implements MenuProvider {
    private final SimpleContainer inv = new SimpleContainer(1);

    public SprinklerBottomBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPRINKLER_BOTTOM_BE.get(), pos, state);
    }

    @Override
    public net.minecraft.network.chat.Component getDisplayName() {
        return net.minecraft.network.chat.Component.translatable("block.modern_sprinkler.sprinkler_bottom");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new SprinklerMenu(id, playerInv, this);
    }

    public boolean hasCore() {
        ItemStack s = inv.getItem(0);
        return !s.isEmpty() && s.is(ModItems.WATER_SPRINKLER_CORE.get());
    }

    public SimpleContainer getContainer() { return inv; }

    public void setItem(int slot, ItemStack stack) {
        inv.setItem(slot, stack);
        setChanged();
        updateHeadActiveState();
    }

    @Override
    protected void saveAdditional(net.minecraft.nbt.CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, inv.getItems());
    }

    @Override
    public void load(net.minecraft.nbt.CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, inv.getItems());
    }

    public void updateHeadActiveState() {
        if (level == null || level.isClientSide) return;
        boolean active = hasCore();
        var headPos = worldPosition.above();
        var head = level.getBlockState(headPos);
        if (head.isAir()) return;

        // Iron
        if (head.is(ModBlocks.IRON_SPRINKLER_HEAD_INACTIVE.get()) && active) {
            level.setBlock(headPos, ModBlocks.IRON_SPRINKLER_HEAD_ACTIVE.get().withPropertiesOf(head), 3);
        } else if (head.is(ModBlocks.IRON_SPRINKLER_HEAD_ACTIVE.get()) && !active) {
            level.setBlock(headPos, ModBlocks.IRON_SPRINKLER_HEAD_INACTIVE.get().withPropertiesOf(head), 3);
        }

        // Gold
        if (head.is(ModBlocks.GOLD_SPRINKLER_HEAD_INACTIVE.get()) && active) {
            level.setBlock(headPos, ModBlocks.GOLD_SPRINKLER_HEAD_ACTIVE.get().withPropertiesOf(head), 3);
        } else if (head.is(ModBlocks.GOLD_SPRINKLER_HEAD_ACTIVE.get()) && !active) {
            level.setBlock(headPos, ModBlocks.GOLD_SPRINKLER_HEAD_INACTIVE.get().withPropertiesOf(head), 3);
        }

        // Diamond
        if (head.is(ModBlocks.DIAMOND_SPRINKLER_HEAD_INACTIVE.get()) && active) {
            level.setBlock(headPos, ModBlocks.DIAMOND_SPRINKLER_HEAD_ACTIVE.get().withPropertiesOf(head), 3);
        } else if (head.is(ModBlocks.DIAMOND_SPRINKLER_HEAD_ACTIVE.get()) && !active) {
            level.setBlock(headPos, ModBlocks.DIAMOND_SPRINKLER_HEAD_INACTIVE.get().withPropertiesOf(head), 3);
        }

        // Netherite
        if (head.is(ModBlocks.NETHERITE_SPRINKLER_HEAD_INACTIVE.get()) && active) {
            level.setBlock(headPos, ModBlocks.NETHERITE_SPRINKLER_HEAD_ACTIVE.get().withPropertiesOf(head), 3);
        } else if (head.is(ModBlocks.NETHERITE_SPRINKLER_HEAD_ACTIVE.get()) && !active) {
            level.setBlock(headPos, ModBlocks.NETHERITE_SPRINKLER_HEAD_INACTIVE.get().withPropertiesOf(head), 3);
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SprinklerBottomBlockEntity be) {
        // optional: be.updateHeadActiveState();
    }
}
