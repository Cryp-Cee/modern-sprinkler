package com.modern_sprinkler.screen;

import com.modern_sprinkler.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModWaterCoreSlot extends SlotItemHandler {
    public ModWaterCoreSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    /**
     * Prüft, ob ein ItemStack in diesen Slot gelegt werden darf.
     * @return true, wenn der Stack ein WATER_SPRINKLER_CORE ist, sonst false.
     */
    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(ModItems.WATER_SPRINKLER_CORE.get());
    }
}