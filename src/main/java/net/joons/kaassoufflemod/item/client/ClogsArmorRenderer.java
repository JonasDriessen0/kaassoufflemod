package net.joons.kaassoufflemod.item.client;

import net.joons.kaassoufflemod.Kaassoufflemod;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import net.joons.kaassoufflemod.item.custom.ClogsArmorItem;

public class ClogsArmorRenderer extends GeoArmorRenderer<ClogsArmorItem> {
    public ClogsArmorRenderer() {
        super(new ClogsArmorModel()); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}