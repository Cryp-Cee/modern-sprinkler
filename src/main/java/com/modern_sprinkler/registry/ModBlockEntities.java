package com.modern_sprinkler.registry;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.blockentity.IronSprinklerHeadBlockEntity;
import com.modern_sprinkler.blockentity.SprinklerPumpBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModernSprinkler.MOD_ID);

    // Sprinkler Pump BE
    public static final RegistryObject<BlockEntityType<SprinklerPumpBlockEntity>> SPRINKLER_PUMP_BE =
            BLOCK_ENTITIES.register("sprinkler_pump",
                    () -> BlockEntityType.Builder.of(SprinklerPumpBlockEntity::new,
                            ModBlocks.SPRINKLER_PUMP.get()).build(null));

    // Iron Sprinkler Head BE (für beide Varianten: active & inactive)
    public static final RegistryObject<BlockEntityType<IronSprinklerHeadBlockEntity>> IRON_SPRINKLER_HEAD_BE =
            BLOCK_ENTITIES.register("iron_sprinkler_head",
                    () -> BlockEntityType.Builder.of(IronSprinklerHeadBlockEntity::new,
                            ModBlocks.IRON_SPRINKLER_HEAD_INACTIVE.get(),
                            ModBlocks.IRON_SPRINKLER_HEAD_ACTIVE.get()
                    ).build(null));
}
