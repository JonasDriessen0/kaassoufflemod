package net.joons.kaassoufflemod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record PressRecipe(Ingredient topIngredient, Ingredient mainIngredient, Ingredient bottomIngredient, ItemStack output) implements Recipe<PressRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        if (!this.topIngredient.isEmpty()) list.add(this.topIngredient);
        if (!this.mainIngredient.isEmpty()) list.add(this.mainIngredient);
        if (!this.bottomIngredient.isEmpty()) list.add(this.bottomIngredient);
        return list;
    }

    @Override
    public boolean matches(PressRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        ItemStack topStack = input.getStackInSlot(0);
        ItemStack mainStack = input.getStackInSlot(1);
        ItemStack bottomStack = input.getStackInSlot(2);
        
        boolean topMatches = topIngredient.isEmpty()
                ? topStack.isEmpty()
                : topIngredient.test(topStack);

        boolean mainMatches = mainIngredient.isEmpty()
                ? mainStack.isEmpty()
                : mainIngredient.test(mainStack);

        boolean bottomMatches = bottomIngredient.isEmpty()
                ? bottomStack.isEmpty()
                : bottomIngredient.test(bottomStack);

        return topMatches && mainMatches && bottomMatches;
    }

    @Override
    public ItemStack craft(PressRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PRESS_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PRESS_SERIALIZER_TYPE;
    }

    public static class Serializer implements RecipeSerializer<PressRecipe> {
        public static final MapCodec<PressRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("top_ingredient", Ingredient.EMPTY).forGetter(PressRecipe::topIngredient),
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("main_ingredient", Ingredient.EMPTY).forGetter(PressRecipe::mainIngredient),
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("bottom_ingredient", Ingredient.EMPTY).forGetter(PressRecipe::bottomIngredient),
                ItemStack.CODEC.fieldOf("result").forGetter(PressRecipe::output)
        ).apply(inst, PressRecipe::new));

        public static final PacketCodec<RegistryByteBuf, PressRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, PressRecipe::topIngredient,
                        Ingredient.PACKET_CODEC, PressRecipe::mainIngredient,
                        Ingredient.PACKET_CODEC, PressRecipe::bottomIngredient,
                        ItemStack.PACKET_CODEC, PressRecipe::output,
                        PressRecipe::new);

        @Override
        public MapCodec<PressRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, PressRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}