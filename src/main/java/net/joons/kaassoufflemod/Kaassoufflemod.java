package net.joons.kaassoufflemod;

import net.fabricmc.api.ModInitializer;

import net.joons.kaassoufflemod.item.ModItemGroups;
import net.joons.kaassoufflemod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kaassoufflemod implements ModInitializer {
	public static final String MOD_ID = "kaassoufflemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
	}
}