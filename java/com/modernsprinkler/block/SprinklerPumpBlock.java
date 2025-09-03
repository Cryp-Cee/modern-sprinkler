package com.modernsprinkler.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class SprinklerPumpBlock extends Block {
    public SprinklerPumpBlock() {
        // Hier definieren wir die Eigenschaften des Blocks
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL)      // Farbe auf der In-Game-Karte
            .strength(2.0f, 6.0f)        // Härte & Explosionsresistenz (wie Eisen)
            .requiresCorrectToolForDrops() // Benötigt eine Spitzhacke zum Abbauen
            .sound(SoundType.METAL));       // Macht Metall-Geräusche
    }
}