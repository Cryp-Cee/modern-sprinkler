package com.modern_sprinkler.registry;

import com.modern_sprinkler.ModernSprinkler;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModernSprinkler.MOD_ID);

    public static final RegistryObject<Item> WATER_SPRINKLER_CORE =
            ITEMS.register("water_sprinkler_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPRINKLER_BOTTOM_ITEM =
            ITEMS.register("sprinkler_bottom", () -> new BlockItem(ModBlocks.SPRINKLER_BOTTOM.get(), new Item.Properties()));

    public static final RegistryObject<Item> IRON_SPRINKLER_HEAD_ITEM =
            ITEMS.register("iron_sprinkler_head_inactive", () -> new BlockItem(ModBlocks.IRON_SPRINKLER_HEAD_INACTIVE.get(), new Item.Properties()));

    public static final RegistryObject<Item> GOLD_SPRINKLER_HEAD_ITEM =
            ITEMS.register("gold_sprinkler_head_inactive", () -> new BlockItem(ModBlocks.GOLD_SPRINKLER_HEAD_INACTIVE.get(), new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_SPRINKLER_HEAD_ITEM =
            ITEMS.register("diamond_sprinkler_head_inactive", () -> new BlockItem(ModBlocks.DIAMOND_SPRINKLER_HEAD_INACTIVE.get(), new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_SPRINKLER_HEAD_ITEM =
            ITEMS.register("netherite_sprinkler_head_inactive", () -> new BlockItem(ModBlocks.NETHERITE_SPRINKLER_HEAD_INACTIVE.get(), new Item.Properties()));
}
