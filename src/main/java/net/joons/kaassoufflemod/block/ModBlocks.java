package net.joons.kaassoufflemod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.block.custom.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CakeBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block RAW_CHEESE = registerBlock("raw_cheese",
            new RawCheeseBlock(AbstractBlock.Settings.create().strength(1f)
                    .sounds(BlockSoundGroup.WET_SPONGE)));

    public static final Block UNPAINTED_CLOG = registerBlock("unpainted_clog",
            new ClogBlock(AbstractBlock.Settings.create().strength(1f)
                    .sounds(BlockSoundGroup.WOOD)));

    public static final Block CLOG_BLOCK = registerBlock("clog_block",
            new PaintedClogBlock(AbstractBlock.Settings.create().strength(1f)
                    .sounds(BlockSoundGroup.WOOD)));

    public static final Block CHEESE_WHEEL = registerBlock("cheese_wheel",
            new CheeseWheelBlock(AbstractBlock.Settings.create().strength(1f)
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block CARVABLE_WOOD = registerBlock("carvable_wood_block",
            new CarvableWoodBlock(AbstractBlock.Settings.create().strength(2f)
                    .sounds(BlockSoundGroup.WOOD)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Kaassoufflemod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(Kaassoufflemod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){
        Kaassoufflemod.LOGGER.info("Registering Mod Blocks for " + Kaassoufflemod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(RAW_CHEESE);});
    }
}