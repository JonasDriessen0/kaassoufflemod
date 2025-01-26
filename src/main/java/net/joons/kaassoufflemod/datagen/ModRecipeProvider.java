package net.joons.kaassoufflemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.joons.kaassoufflemod.Kaassoufflemod;
import net.joons.kaassoufflemod.block.ModBlocks;
import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> URANIUM_SMELTABLES = List.of(ModBlocks.URANIUM_ORE, ModBlocks.URANIUM_DEEPSLATE_ORE);
        List<ItemConvertible> LEAD_SMELTABLES = List.of(ModItems.RAW_LEAD, ModBlocks.LEAD_ORE, ModBlocks.LEAD_DEEPSLATE_ORE);

        offerSmelting(recipeExporter, URANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.RAW_URANIUM, 0.25f, 200, "uranium");
        offerBlasting(recipeExporter, URANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.RAW_URANIUM, 0.25f, 200, "uranium");

        offerSmelting(recipeExporter, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT, 0.25f, 200, "lead");
        offerBlasting(recipeExporter, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT, 0.25f, 200, "lead");

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.RAW_KAASSOUFFLE),RecipeCategory.FOOD,ModItems.KAASSOUFFLE, 0.25f,100)
                .criterion(hasItem(ModItems.RAW_KAASSOUFFLE),conditionsFromItem(ModItems.RAW_KAASSOUFFLE))
                .offerTo(recipeExporter, Identifier.of(Kaassoufflemod.MOD_ID, "raw_kaassouffle_from_smelting"));

        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(ModItems.RAW_KAASSOUFFLE),RecipeCategory.FOOD,ModItems.KAASSOUFFLE, 0.25f,100)
                .criterion(hasItem(ModItems.RAW_KAASSOUFFLE),conditionsFromItem(ModItems.RAW_KAASSOUFFLE))
                .offerTo(recipeExporter, Identifier.of(Kaassoufflemod.MOD_ID, "raw_kaassouffle_from_smoking"));

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(Items.IRON_INGOT),RecipeCategory.MISC,ModItems.STEEL_INGOT, 0.25f,100)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, Identifier.of(Kaassoufflemod.MOD_ID, "steel_ingot_from_blasting"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHEESE_SLICER)
                .pattern(" ##")
                .pattern(" ##")
                .pattern("X  ")
                .input('#', Items.IRON_NUGGET)
                .input('X', Items.STICK)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPOON)
                .pattern("  #")
                .pattern(" X ")
                .pattern("X  ")
                .input('#', Items.IRON_INGOT)
                .input('X', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PAINTBRUSH)
                .pattern("  #")
                .pattern(" B ")
                .pattern("X  ")
                .input('#', Items.FEATHER)
                .input('X', Items.STICK)
                .input('B', ItemTags.PLANKS)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CLOGS)
                .input(ModBlocks.CLOG_BLOCK)
                .input(ModBlocks.CLOG_BLOCK)
                .criterion(hasItem(ModBlocks.CLOG_BLOCK), conditionsFromItem(ModBlocks.CLOG_BLOCK))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TWO_CHEESE_SLICES)
                .input(ModItems.CHEESE_SLICE)
                .input(ModItems.CHEESE_SLICE)
                .criterion(hasItem(ModItems.CHEESE_SLICE), conditionsFromItem(ModItems.CHEESE_SLICE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BREADCRUMBS)
                .input(Items.BREAD)
                .criterion(hasItem(Items.BREAD), conditionsFromItem(Items.BREAD))
                .offerTo(recipeExporter);
    }
}
