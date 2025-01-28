package net.joons.kaassoufflemod.block.entity.client;

import net.joons.kaassoufflemod.block.entity.PressBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PressBlockRenderer extends GeoBlockRenderer<PressBlockEntity> {
    public PressBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new PressBlockModel());
    }
}
