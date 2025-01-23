package net.joons.kaassoufflemod.util;

import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.component.ModDataComponentTypes;
import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

import java.util.Stack;

public class ModModelPredicates {
    public static void registerModelPredicates(){
        ModelPredicateProviderRegistry.register(ModItems.PAINTBRUSH, Identifier.of(Kaassoufflemod.MOD_ID, "has_paint"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.TEXTURE_TYPE) != null ? 1f : 0f);
    }
}
