package com.modern_sprinkler.blockentity;

import com.modern_sprinkler.registry.ModBlockEntities;
import com.modern_sprinkler.registry.ModBlocks;
import com.modern_sprinkler.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SprinklerPumpBlockEntity extends BlockEntity {
    private final SimpleContainer inv = new SimpleContainer(1); // Slot 0: Core

    public SprinklerPumpBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPRINKLER_PUMP_BE.get(), pos, state);
    }

    public boolean hasCore() {
        ItemStack s = inv.getItem(0);
        return !s.isEmpty() && s.is(ModItems.WATER_SPRINKLER_CORE.get());
    }

    public void setItem(int slot, ItemStack stack) {
        inv.setItem(slot, stack);
        setChanged();
        updateHeadActiveState();
    }

    public ItemStack getItem(int slot) { return inv.getItem(slot); }

    // z.B. aus Menu/Screen aufrufen, wenn der Slot sich ändert:
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

    // optional: Inventar-Persistenz
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

    // optional permissions
    public boolean stillValid(Player player) {
        return player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5) <= 64.0;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SprinklerPumpBlockEntity be) {
        // falls du periodisch prüfen willst (z. B. falls Menu das Event nicht triggert)
        // be.updateHeadActiveState();
    }
}
