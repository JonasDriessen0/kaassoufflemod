package net.joons.kaassoufflemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.block.ModBlocks;
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
                        entries.add(ModItems.RAW_KAASSOUFFLE);
                        entries.add(ModBlocks.CHEESE_WHEEL);
                        entries.add(ModItems.CHEESE_SLICER);
                        entries.add(ModItems.CHEESE_SLICE);
                        entries.add(ModItems.TWO_CHEESE_SLICES);
                        entries.add(ModItems.BUCKET_OF_RAW_CHEESE);
                        entries.add(ModItems.BREADCRUMBS);
                        entries.add(ModItems.THERMONUCLEAR_KAASSOUFFLE);
                        entries.add(ModItems.CLOGS);
                        entries.add(ModBlocks.UNPAINTED_CLOG);
                        entries.add(ModItems.SPOON);
                        entries.add(ModItems.PAINTBRUSH);
                        entries.add(ModItems.COMICALLY_LARGE_SPOON);
                        entries.add(ModBlocks.CARVABLE_WOOD);
                        entries.add(ModBlocks.URANIUM_ORE);
                        entries.add(ModBlocks.URANIUM_DEEPSLATE_ORE);
                        entries.add(ModBlocks.LEAD_ORE);
                        entries.add(ModBlocks.LEAD_DEEPSLATE_ORE);
                        entries.add(ModItems.RAW_LEAD);
                        entries.add(ModItems.LEAD_INGOT);
                        entries.add(ModItems.OBSIDIAN_DUST);
                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModItems.STICKY_PASTE);
                        entries.add(ModItems.RUBBER);
                        entries.add(ModItems.CIRCUIT_BOARD);
                        entries.add(ModItems.REDSTONE_COMPONENTS);
                        entries.add(ModItems.LEAD_PLATE);
                        entries.add(ModItems.OBSIDIAN_INFUSED_INGOT);
                        entries.add(ModItems.REINFORCED_INGOT);
                    }).build());


    public static void registerItemGroups(){
        Kaassoufflemod.LOGGER.info("Registering Item Groups for " + Kaassoufflemod.MOD_ID);
    }
}
