package com.modernsprinkler.core.init;

import com.modernsprinkler.ModernSprinkler;
import com.modernsprinkler.block.SprinklerHeadBlock;
import com.modernsprinkler.block.SprinklerPumpBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModernSprinkler.MOD_ID);

    // Der Pumpen-Block
    public static final RegistryObject<Block> SPRINKLER_PUMP = registerBlock("sprinkler_pump",
            SprinklerPumpBlock::new);

    // Die vier Sprinkler-Köpfe
    public static final RegistryObject<Block> IRON_SPRINKLER_HEAD = registerBlock("iron_sprinkler_head",
            SprinklerHeadBlock::new);
    public static final RegistryObject<Block> GOLD_SPRINKLER_HEAD = registerBlock("gold_sprinkler_head",
            SprinklerHeadBlock::new);
    public static final RegistryObject<Block> DIAMOND_SPRINKLER_HEAD = registerBlock("diamond_sprinkler_head",
            SprinklerHeadBlock::new);
    public static final RegistryObject<Block> NETHERITE_SPRINKLER_HEAD = registerBlock("netherite_sprinkler_head",
            SprinklerHeadBlock::new);


    // --- Helfer-Methoden ---
    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ItemInit.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    // --- Registrierungs-Methode ---
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}