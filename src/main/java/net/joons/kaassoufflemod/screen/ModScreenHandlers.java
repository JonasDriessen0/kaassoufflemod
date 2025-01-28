package net.joons.kaassoufflemod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.block.entity.PressData;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<PressScreenHandler> PRESS_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Kaassoufflemod.MOD_ID, "press_screen"),
                    new ExtendedScreenHandlerType<>(PressScreenHandler::new, PressData.PACKET_CODEC));

    public static void registerScreenHandlers() {
        Kaassoufflemod.LOGGER.info("Registering Screen Handlers for " + Kaassoufflemod.MOD_ID);
    }
}
