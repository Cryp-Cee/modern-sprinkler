package com.modern_sprinkler;

import com.modern_sprinkler.registry.ModBlockEntities;
import com.modern_sprinkler.registry.ModBlocks;
import com.modern_sprinkler.registry.ModItems;
import com.modern_sprinkler.screen.ModMenuTypes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(ModernSprinkler.MOD_ID)
public class ModernSprinkler {
    public static final String MOD_ID = "modern_sprinkler";

    public ModernSprinkler() {
        GeckoLib.initialize();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModMenuTypes.register(bus); // <— wichtig für GUI
    }
}
