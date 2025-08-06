package net.joons.kaassoufflemod.recipe;

import net.joons.kaassoufflemod.Kaassoufflemod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<PressRecipe> PRESS_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Kaassoufflemod.MOD_ID, "press"),
                new PressRecipe.Serializer());
    public static final RecipeType<PressRecipe> PRESS_SERIALIZER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Kaassoufflemod.MOD_ID, "press"), new RecipeType<PressRecipe>() {
                @Override
                public String toString() {
                    return "press";
                }
            });

    public static void registerRecipes(){
        Kaassoufflemod.LOGGER.info("Registering custom recipes for " + Kaassoufflemod.MOD_ID);
    }
}
