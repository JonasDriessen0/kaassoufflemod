package net.joons.kaassoufflemod;

import net.fabricmc.api.ClientModInitializer;
import net.joons.kaassoufflemod.block.entity.ModBlockEntityTypes;
import net.joons.kaassoufflemod.block.entity.client.PressBlockRenderer;
import net.joons.kaassoufflemod.screen.ModScreenHandlers;
import net.joons.kaassoufflemod.screen.PressScreen;
import net.joons.kaassoufflemod.screen.PressScreenHandler;
import net.joons.kaassoufflemod.util.ModModelPredicates;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class KaassouffleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
        BlockEntityRendererFactories.register(ModBlockEntityTypes.PRESS, PressBlockRenderer::new);

        HandledScreens.register(ModScreenHandlers.PRESS_SCREEN_HANDLER, PressScreen::new);
    }
}
