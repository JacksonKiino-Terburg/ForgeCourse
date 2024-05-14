package net.berlitz.mccourse.datagen;

import net.berlitz.mccourse.MCCourseMod;
import net.berlitz.mccourse.block.ModBlocks;
import net.berlitz.mccourse.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(ModItems.RAW_ALEXANDRITE.get(),
            ModBlocks.ALEXANDRITE_ORE.get(), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
            ModBlocks.END_STONE_ALEXANDRITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput){
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModItems.ALEXANDRITE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite_block", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        //storage recipe shortcut
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                "mccourse:raw_alexandrite", "alexandrite", "mccourse:raw_alexandrite_block", "alexandrite");
        oreSmelting(pWriter, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 200, "alexandrite");
        oreBlasting(pWriter, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 100, "alexandrite");



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_STAIRS.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_SLAB.get(), 4)
                .pattern("AAA")
                .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_FENCE.get(), 6)
                .pattern("AIA")
                .pattern("AIA")
                .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                .define('I', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_FENCE_GATE.get(), 1)
                .pattern("IAI")
                .pattern("IAI")
                .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                .define('I', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_WALL.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_DOOR.get(), 3)
                .pattern("AA ")
                .pattern("AA ")
                .pattern("AA ")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_TRAPDOOR.get(), 3)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy("has_alexandrite", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
                .save(pWriter);

    }


    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer,
                    MCCourseMod.MOD_ID + ":" + (pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
