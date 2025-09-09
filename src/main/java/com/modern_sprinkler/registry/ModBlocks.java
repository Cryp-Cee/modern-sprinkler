package com.modern_sprinkler.registry;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.block.IronSprinklerHeadBlock;
import com.modern_sprinkler.block.SprinklerBottomBlock;
import com.modern_sprinkler.block.GoldSprinklerHeadBlock;
import com.modern_sprinkler.block.DiamondSprinklerHeadBlock;
import com.modern_sprinkler.block.NetheriteSprinklerHeadBlock;
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

    // Bottom / Pump
    public static final RegistryObject<Block> SPRINKLER_BOTTOM = BLOCKS.register("sprinkler_bottom",
            () -> new SprinklerBottomBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
            ));

    // Iron (bereits vorhanden)
    public static final RegistryObject<Block> IRON_SPRINKLER_HEAD_INACTIVE =
            BLOCKS.register("iron_sprinkler_head_inactive", () -> new IronSprinklerHeadBlock(false));
    public static final RegistryObject<Block> IRON_SPRINKLER_HEAD_ACTIVE =
            BLOCKS.register("iron_sprinkler_head_active", () -> new IronSprinklerHeadBlock(true));

    // Gold
    public static final RegistryObject<Block> GOLD_SPRINKLER_HEAD_INACTIVE =
            BLOCKS.register("gold_sprinkler_head_inactive", () -> new GoldSprinklerHeadBlock(false));
    public static final RegistryObject<Block> GOLD_SPRINKLER_HEAD_ACTIVE =
            BLOCKS.register("gold_sprinkler_head_active", () -> new GoldSprinklerHeadBlock(true));

    // Diamond
    public static final RegistryObject<Block> DIAMOND_SPRINKLER_HEAD_INACTIVE =
            BLOCKS.register("diamond_sprinkler_head_inactive", () -> new DiamondSprinklerHeadBlock(false));
    public static final RegistryObject<Block> DIAMOND_SPRINKLER_HEAD_ACTIVE =
            BLOCKS.register("diamond_sprinkler_head_active", () -> new DiamondSprinklerHeadBlock(true));

    // Netherite
    public static final RegistryObject<Block> NETHERITE_SPRINKLER_HEAD_INACTIVE =
            BLOCKS.register("netherite_sprinkler_head_inactive", () -> new NetheriteSprinklerHeadBlock(false));
    public static final RegistryObject<Block> NETHERITE_SPRINKLER_HEAD_ACTIVE =
            BLOCKS.register("netherite_sprinkler_head_active", () -> new NetheriteSprinklerHeadBlock(true));
}
