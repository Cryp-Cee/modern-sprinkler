package com.modern_sprinkler.registry;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.block.IronSprinklerHeadBlock;
import com.modern_sprinkler.block.SprinklerPumpBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModernSprinkler.MOD_ID);

    // Sprinkler Pump (unterer Block)
    public static final RegistryObject<Block> SPRINKLER_PUMP = BLOCKS.register("sprinkler_pump",
            () -> new SprinklerPumpBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5.0F, 6.0F) // "wie Eisenblock"
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
            ));

    // Iron Head (inactive/active)
    public static final RegistryObject<Block> IRON_SPRINKLER_HEAD_INACTIVE =
            BLOCKS.register("iron_sprinkler_head_inactive", () -> new IronSprinklerHeadBlock(false));

    public static final RegistryObject<Block> IRON_SPRINKLER_HEAD_ACTIVE =
            BLOCKS.register("iron_sprinkler_head_active", () -> new IronSprinklerHeadBlock(true));

    // --- Platzhalter für weitere Tiers (Gold/Diamond/Netherite) ---
    // Du kannst später Gold/… analog mit eigenen Blockklassen + BEs erstellen.
}
