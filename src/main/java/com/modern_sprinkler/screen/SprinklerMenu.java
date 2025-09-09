package com.modern_sprinkler.screen;

import com.modern_sprinkler.blockentity.SprinklerBottomBlockEntity;
import com.modern_sprinkler.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SprinklerMenu extends AbstractContainerMenu {
    private final SprinklerBottomBlockEntity be;
    private final Level level;

    // Server: direkt mit BE
    public SprinklerMenu(int id, Inventory playerInv, SprinklerBottomBlockEntity be) {
        super(ModMenuTypes.SPRINKLER_MENU.get(), id);
        this.be = be;
        this.level = playerInv.player.level();

        // 1 Slot für den Core (nur WATER_SPRINKLER_CORE zulassen)
        this.addSlot(new Slot(be.getContainer(), 0, 80, 35) {
            @Override public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.WATER_SPRINKLER_CORE.get());
            }
        });

        addPlayerInv(playerInv);
        addPlayerHotbar(playerInv);
    }

    // Client: Position aus dem Netzwerk
    public static SprinklerMenu fromNetwork(int id, Inventory playerInv, FriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity e = playerInv.player.level().getBlockEntity(pos);
        if (e instanceof SprinklerBottomBlockEntity be) {
            return new SprinklerMenu(id, playerInv, be);
        }
        // Fallback Dummy (sollte nicht passieren)
        return new SprinklerMenu(id, playerInv, new SprinklerBottomBlockEntity(pos,
                playerInv.player.level().getBlockState(pos)));
    }

    @Override public boolean stillValid(Player player) {
        return player.distanceToSqr(be.getBlockPos().getX()+0.5, be.getBlockPos().getY()+0.5, be.getBlockPos().getZ()+0.5) <= 64.0;
    }

    // Shift-Click Transfer
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copy = stack.copy();
            final int BE_SLOT_COUNT = 1;
            final int INV_START = BE_SLOT_COUNT;
            final int INV_END = INV_START + 27;   // Player inv
            final int HOTBAR_START = INV_END;
            final int HOTBAR_END = HOTBAR_START + 9;

            if (index < BE_SLOT_COUNT) {
                if (!this.moveItemStackTo(stack, INV_START, HOTBAR_END, true)) return ItemStack.EMPTY;
            } else {
                if (stack.is(ModItems.WATER_SPRINKLER_CORE.get())) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                } else if (index < HOTBAR_START) {
                    if (!this.moveItemStackTo(stack, HOTBAR_START, HOTBAR_END, false)) return ItemStack.EMPTY;
                } else if (!this.moveItemStackTo(stack, INV_START, HOTBAR_START, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return copy;
    }

    private void addPlayerInv(Inventory inv) {
        for (int row = 0; row < 3; ++row)
            for (int col = 0; col < 9; ++col)
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; ++col)
            this.addSlot(new Slot(inv, col, 8 + col * 18, 142));
    }
}
