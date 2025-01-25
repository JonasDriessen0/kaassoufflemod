package net.joons.kaassoufflemod.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.joons.kaassoufflemod.block.ModBlocks;
import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CHEESE_WHEEL);
        addDrop(ModBlocks.CLOG_BLOCK);
        addDrop(ModBlocks.UNPAINTED_CLOG);

        addDrop(ModBlocks.URANIUM_ORE, multipleOreDrops(ModBlocks.URANIUM_ORE, ModItems.RAW_URANIUM, 1, 2));
        addDrop(ModBlocks.URANIUM_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.URANIUM_DEEPSLATE_ORE, ModItems.RAW_URANIUM, 1, 2));

        addDrop(ModBlocks.LEAD_ORE, multipleOreDrops(ModBlocks.LEAD_ORE, ModItems.RAW_LEAD, 2, 8));
        addDrop(ModBlocks.LEAD_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.LEAD_DEEPSLATE_ORE, ModItems.RAW_LEAD, 2, 8));

    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
