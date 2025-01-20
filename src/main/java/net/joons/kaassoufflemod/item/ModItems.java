package net.joons.kaassoufflemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.item.custom.CheeseBucketItem;
import net.joons.kaassoufflemod.item.custom.ClogsArmorItem;
import net.joons.kaassoufflemod.item.custom.SpoonItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item KAASSOUFFLE = registerItem("kaassouffle", new Item(new Item.Settings().food(ModFoodComponents.KAASSOUFFLE)));
    public static final Item RAW_KAASSOUFFLE = registerItem("raw_kaassouffle", new Item(new Item.Settings()));
    public static final Item THERMONUCLEAR_KAASSOUFFLE = registerItem("thermonuclear_kaassouffle", new Item(new Item.Settings()));

    public static final Item BUCKET_OF_RAW_CHEESE = registerItem("bucket_of_raw_cheese", new CheeseBucketItem(new Item.Settings().maxCount(1)));

    public static final Item CHEESE_SLICER = registerItem("cheese_slicer", new SwordItem(ToolMaterials.WOOD, new Item.Settings().
            attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 3, -2.4f))));

    public static final Item SPOON = registerItem("spoon", new SpoonItem(ToolMaterials.WOOD, new Item.Settings().
            attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 3, -2.4f))));

    public static final Item CHEESE_SLICE = registerItem("cheese_slice", new Item(new Item.Settings()));
    public static final Item TWO_CHEESE_SLICES = registerItem("two_cheese_slices", new Item(new Item.Settings()));
    public static final Item BREADCRUMBS = registerItem("breadcrumbs", new Item(new Item.Settings()));

    public static final Item CLOGS = registerItem("clogs",
            new ClogsArmorItem(ModArmorMaterials.CLOGS_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Kaassoufflemod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Kaassoufflemod.LOGGER.info("Registering Mod Items for " + Kaassoufflemod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(KAASSOUFFLE);
        });
    }

}
