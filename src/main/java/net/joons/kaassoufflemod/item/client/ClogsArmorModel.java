package net.joons.kaassoufflemod.item.client;

import software.bernie.geckolib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.item.custom.ClogsArmorItem;

public class ClogsArmorModel extends GeoModel<ClogsArmorItem> {
    @Override
    public Identifier getModelResource(ClogsArmorItem object) {
        return Identifier.of(Kaassoufflemod.MOD_ID, "geo/item/armor/clogs.geo.json");
    }

    @Override
    public Identifier getTextureResource(ClogsArmorItem object) {
        return Identifier.of(Kaassoufflemod.MOD_ID, "textures/item/armor/clogs_texture.png");
    }

    @Override
    public Identifier getAnimationResource(ClogsArmorItem animatable) {
        return Identifier.of(Kaassoufflemod.MOD_ID, "animations/clogs.animation.json");
    }
}