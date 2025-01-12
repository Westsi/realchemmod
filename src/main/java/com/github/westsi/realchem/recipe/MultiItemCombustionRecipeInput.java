package com.github.westsi.realchem.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.collection.DefaultedList;

import java.util.Arrays;

public class MultiItemCombustionRecipeInput implements RecipeInput {
    private final DefaultedList<ItemStack> inputs = DefaultedList.of();

    public MultiItemCombustionRecipeInput(ItemStack... input1) {
        inputs.addAll(Arrays.asList(input1));
    }
    @Override
    public ItemStack getStackInSlot(int slot) {
        return inputs.get(slot);
    }

    @Override
    public int getSize() {
        return inputs.size();
    }
}
