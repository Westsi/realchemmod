package com.github.westsi.realchem.recipe;

import com.github.westsi.realchem.RealChemistry;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeSerializer<CombustionRecipe> COMBUSTION_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(RealChemistry.MOD_ID, "combusting"), new CombustionRecipe.Serializer());

    public static final RecipeType<CombustionRecipe> COMBUSTION_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(RealChemistry.MOD_ID, "combusting"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "combusting";
                }
            });

    public static final RecipeSerializer<MultiItemCombustionRecipe> MULTI_COMBUSTION_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(RealChemistry.MOD_ID, "multi_combusting"), new MultiItemCombustionRecipe.Serializer());

    public static final RecipeType<MultiItemCombustionRecipe> MULTI_COMBUSTION_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(RealChemistry.MOD_ID, "multi_combusting"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "multi_combusting";
                }
            }
    );

    public static void registerRecipes() {
        RealChemistry.LOGGER.info("Registering Custom Recipes for " + RealChemistry.MOD_ID);
    }
}
