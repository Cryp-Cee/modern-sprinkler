package com.modern_sprinkler.client;

import com.modern_sprinkler.blockentity.NetheriteSprinklerHeadBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;

public class NetheriteSprinklerHeadRenderer extends GeoBlockRenderer<NetheriteSprinklerHeadBlockEntity> {
    public NetheriteSprinklerHeadRenderer(){ super(new NetheriteSprinklerHeadModel()); }
    @Override public RenderType getRenderType(NetheriteSprinklerHeadBlockEntity a, ResourceLocation tex, @Nullable MultiBufferSource buf, float pt){
        return RenderType.entityCutoutNoCull(tex);
    }
    @Override public void preRender(PoseStack ps, NetheriteSprinklerHeadBlockEntity a, BakedGeoModel m, @Nullable MultiBufferSource bs,
                                    @Nullable VertexConsumer b, boolean rr, float pt, int pl, int po, float r, float g, float bl, float al) {
        super.preRender(ps, a, m, bs, b, rr, pt, pl, po, r, g, bl, al);
    }
}
