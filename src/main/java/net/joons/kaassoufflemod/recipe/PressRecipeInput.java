package net.joons.kaassoufflemod.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record PressRecipeInput(ItemStack topInput, ItemStack mainInput, ItemStack bottomInput, ItemStack output) implements RecipeInput {

    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> this.topInput;
            case 1 -> this.mainInput;
            case 2 -> this.bottomInput;
            case 3 -> this.output;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + slot);
        };
    }

    @Override
    public int getSize() {
        return 4;
    }
}
