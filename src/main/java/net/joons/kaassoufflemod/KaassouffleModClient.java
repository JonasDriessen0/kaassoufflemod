package net.joons.kaassoufflemod;

import net.fabricmc.api.ClientModInitializer;
import net.joons.kaassoufflemod.entity.ModBlockEntityTypes;
import net.joons.kaassoufflemod.entity.client.PressBlockRenderer;
import net.joons.kaassoufflemod.util.ModModelPredicates;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class KaassouffleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
        BlockEntityRendererFactories.register(ModBlockEntityTypes.PRESS, PressBlockRenderer::new);
    }
}
