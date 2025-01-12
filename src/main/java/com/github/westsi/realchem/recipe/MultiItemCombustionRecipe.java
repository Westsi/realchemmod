package com.github.westsi.realchem.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class MultiItemCombustionRecipe implements Recipe<MultiItemCombustionRecipeInput> {
    private final DefaultedList<Ingredient> ingredients;
    private final ItemStack output;

    public MultiItemCombustionRecipe(ItemStack output, DefaultedList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.output = output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean matches(MultiItemCombustionRecipeInput input, World world) {
        if (input.getSize() != this.ingredients.size()) {
            return false;
        }
        DefaultedList<Ingredient> copy = this.ingredients;
        for (int i=0; i< input.getSize();i++) {
            ItemStack in = input.getStackInSlot(i);
            boolean is = false;
            for (Ingredient ing : copy) {
                if (ing.test(in)) {
                    copy.remove(ing);
                    is = true;
                    break;
                }
            }
            if (!is) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(MultiItemCombustionRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
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
        return ModRecipes.MULTI_COMBUSTION_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.MULTI_COMBUSTION_TYPE;
    }

    public static class Serializer implements RecipeSerializer<MultiItemCombustionRecipe> {

        private static final MapCodec<MultiItemCombustionRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
                                Ingredient.DISALLOW_EMPTY_CODEC
                                        .listOf()
                                        .fieldOf("ingredients")
                                        .flatXmap(
                                                ingredients -> {
                                                    Ingredient[] ingredients2 = (Ingredient[])ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                                                    if (ingredients2.length == 0) {
                                                        return DataResult.error(() -> "No ingredients for shapeless recipe");
                                                    } else {
                                                        return ingredients2.length > 9
                                                                ? DataResult.error(() -> "Too many ingredients for shapeless recipe")
                                                                : DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredients2));
                                                    }
                                                },
                                                DataResult::success
                                        )
                                        .forGetter(recipe -> recipe.ingredients)
                        )
                        .apply(instance, MultiItemCombustionRecipe::new)
        );

        public static final PacketCodec<RegistryByteBuf, MultiItemCombustionRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                MultiItemCombustionRecipe.Serializer::write, MultiItemCombustionRecipe.Serializer::read
        );

        @Override
        public MapCodec<MultiItemCombustionRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, MultiItemCombustionRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static MultiItemCombustionRecipe read(RegistryByteBuf buf) {
            int i = buf.readVarInt();
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i, Ingredient.EMPTY);
            defaultedList.replaceAll(empty -> Ingredient.PACKET_CODEC.decode(buf));
            ItemStack itemStack = ItemStack.PACKET_CODEC.decode(buf);
            return new MultiItemCombustionRecipe(itemStack, defaultedList);
        }

        private static void write(RegistryByteBuf buf, MultiItemCombustionRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }

    }
}
