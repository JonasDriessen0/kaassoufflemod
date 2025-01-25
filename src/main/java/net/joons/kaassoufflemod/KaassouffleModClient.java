package net.joons.kaassoufflemod;

import net.fabricmc.api.ClientModInitializer;
import net.joons.kaassoufflemod.util.ModModelPredicates;

public class KaassouffleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
    }
}
