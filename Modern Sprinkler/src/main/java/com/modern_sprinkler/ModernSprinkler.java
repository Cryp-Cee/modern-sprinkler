package com.modern_sprinkler;

import com.modern_sprinkler.block.ModBlocks;
import com.modern_sprinkler.block.entity.ModBlockEntities;
import com.modern_sprinkler.item.ModItems;
import com.modern_sprinkler.screen.ModMenuTypes;
import com.modern_sprinkler.screen.SprinklerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModernSprinkler.MOD_ID)
public class ModernSprinkler {
    public static final String MOD_ID = "modern_sprinkler";

    public ModernSprinkler() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Hier kommt später serverseitige Logik hin
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.SPRINKLER_BOTTOM);
            event.accept(ModItems.WATER_SPRINKLER_CORE);
            event.accept(ModBlocks.IRON_SPRINKLER_HEAD);
            event.accept(ModBlocks.GOLD_SPRINKLER_HEAD);
            event.accept(ModBlocks.DIAMOND_SPRINKLER_HEAD);
            event.accept(ModBlocks.NETHERITE_SPRINKLER_HEAD);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.SPRINKLER_MENU.get(), SprinklerScreen::new);
    }
}