package net.joons.kaassoufflemod.entity.client;

import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.entity.PressBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PressBlockModel extends GeoModel<PressBlockEntity> {
    @Override
    public Identifier getModelResource(PressBlockEntity pressBlockEntity) {
        return Identifier.of(Kaassoufflemod.MOD_ID, "geo/block/press.geo.json");
    }

    @Override
    public Identifier getTextureResource(PressBlockEntity pressBlockEntity) {
        return Identifier.of(Kaassoufflemod.MOD_ID, "textures/block/press.png");
    }

    @Override
    public Identifier getAnimationResource(PressBlockEntity pressBlockEntity) {
        return Identifier.of(Kaassoufflemod.MOD_ID, "animations/press.animation.json");
    }
}
