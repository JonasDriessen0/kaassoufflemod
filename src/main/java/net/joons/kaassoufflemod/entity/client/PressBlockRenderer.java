package net.joons.kaassoufflemod.entity.client;

import net.joons.kaassoufflemod.entity.PressBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PressBlockRenderer extends GeoBlockRenderer<PressBlockEntity> {
    public PressBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new PressBlockModel());
    }
}
