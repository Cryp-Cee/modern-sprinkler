package com.modern_sprinkler.screen;

import com.modern_sprinkler.ModernSprinkler;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ModernSprinkler.MOD_ID);

    public static final RegistryObject<MenuType<SprinklerMenu>> SPRINKLER_MENU =
            register(SprinklerMenu::fromNetwork, "sprinkler_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(
            IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
