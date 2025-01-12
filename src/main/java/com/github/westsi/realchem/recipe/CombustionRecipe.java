package com.github.westsi.realchem.recipe;

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

public record CombustionRecipe(Ingredient inputItem, ItemStack output) implements Recipe<CombustionRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(CombustionRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }
        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(CombustionRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
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
        return ModRecipes.COMBUSTION_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.COMBUSTION_TYPE;
    }

    public static class Serializer implements RecipeSerializer<CombustionRecipe> {
        public static final MapCodec<CombustionRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(CombustionRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(CombustionRecipe::output))
                .apply(inst, CombustionRecipe::new));

        public static final PacketCodec<RegistryByteBuf, CombustionRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, CombustionRecipe::inputItem,
                        ItemStack.PACKET_CODEC, CombustionRecipe::output,
                        CombustionRecipe::new);

        @Override
        public MapCodec<CombustionRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CombustionRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
