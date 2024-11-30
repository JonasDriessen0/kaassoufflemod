package net.joons.kaassoufflemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.item.custom.CheeseBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item KAASSOUFFLE = registerItem("kaassouffle", new Item(new Item.Settings().food(ModFoodComponents.KAASSOUFFLE)));

    public static final Item CHEESE_WHEEL = registerItem("cheese_wheel", new Item(new Item.Settings()));

    public static final Item BUCKET_OF_RAW_CHEESE = registerItem("bucket_of_raw_cheese", new CheeseBucketItem(new Item.Settings().maxCount(1)));

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
