package com.github.westsi.realchem.component;

import com.github.westsi.realchem.RealChemistry;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<Integer> COMPOUND_COLOR =
            register("compound_color", builder -> builder.codec(Codec.INT));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(RealChemistry.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }
    public static void registerDataComponentTypes() {
        RealChemistry.LOGGER.info("Registering Data Component Types for " + RealChemistry.MOD_ID);
    }
}
