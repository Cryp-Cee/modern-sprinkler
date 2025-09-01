package com.modernsprinkler.renderer;

import com.modernsprinkler.block.entity.SprinklerHeadBlockEntity;
import com.modernsprinkler.model.SprinklerHeadModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SprinklerHeadRenderer extends GeoBlockRenderer<SprinklerHeadBlockEntity> {
    public SprinklerHeadRenderer(BlockEntityRendererProvider.Context context) {
        super(new SprinklerHeadModel());
    }
}