package net.joons.kaassoufflemod;

import net.fabricmc.api.ModInitializer;

import net.joons.kaassoufflemod.block.ModBlocks;
import net.joons.kaassoufflemod.component.ModDataComponentTypes;
import net.joons.kaassoufflemod.entity.ModBlockEntityTypes;
import net.joons.kaassoufflemod.item.ModItemGroups;
import net.joons.kaassoufflemod.item.ModItems;
import net.joons.kaassoufflemod.event.BlockInteractionHandler;
import net.joons.kaassoufflemod.util.ModModelPredicates;
import net.joons.kaassoufflemod.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kaassoufflemod implements ModInitializer {
	public static final String MOD_ID = "kaassoufflemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		BlockInteractionHandler.register();

		ModDataComponentTypes.registerDataComponentTypes();

		ModWorldGeneration.generateModWorldGen();

		ModBlockEntityTypes.initialize();
	}
}