package com.modern_sprinkler.item;

import com.modern_sprinkler.ModernSprinkler;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModernSprinkler.MOD_ID);

    public static final RegistryObject<Item> WATER_SPRINKLER_CORE = ITEMS.register("water_sprinkler_core",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}