package net.joons.kaassoufflemod.component;

import com.mojang.serialization.Codec;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<Boolean> TEXTURE_TYPE = register("texture_type", builder -> builder.codec(Codec.BOOL));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Kaassoufflemod.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        Kaassoufflemod.LOGGER.info("Registering Data Component Types for " + Kaassoufflemod.MOD_ID);
    }
}
