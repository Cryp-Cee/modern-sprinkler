package com.modern_sprinkler.block;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModernSprinkler.MOD_ID);

    public static final RegistryObject<Block> SPRINKLER_BOTTOM = registerBlock("sprinkler_bottom",
            () -> new SprinklerBlock(BlockBehaviour.Properties.of()
                 .sound(SoundType.METAL)
                 .strength(2.0f).requiresCorrectToolForDrops().noOcclusion()));

    // HIER SIND DIE FEHLENDEN DEKLARATIONEN
    public static final RegistryObject<Block> IRON_SPRINKLER_HEAD = registerBlock("iron_sprinkler_head",
            () -> new SprinklerHeadBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> GOLD_SPRINKLER_HEAD = registerBlock("gold_sprinkler_head",
            () -> new SprinklerHeadBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> DIAMOND_SPRINKLER_HEAD = registerBlock("diamond_sprinkler_head",
            () -> new SprinklerHeadBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> NETHERITE_SPRINKLER_HEAD = registerBlock("netherite_sprinkler_head",
            () -> new SprinklerHeadBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}