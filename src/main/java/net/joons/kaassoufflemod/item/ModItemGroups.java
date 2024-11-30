package net.joons.kaassoufflemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup KAASSOUFFLE_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Kaassoufflemod.MOD_ID, "kaassouffle_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.KAASSOUFFLE))
                    .displayName(Text.translatable("itemgroup.kaassoufflemod.kaassouffle_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.KAASSOUFFLE);
                        entries.add(ModItems.CHEESE_WHEEL);
                        entries.add(ModItems.BUCKET_OF_RAW_CHEESE);
                    }).build());


    public static void registerItemGroups(){
        Kaassoufflemod.LOGGER.info("Registering Item Groups for " + Kaassoufflemod.MOD_ID);
    }
}
