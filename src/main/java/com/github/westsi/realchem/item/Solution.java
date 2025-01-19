package com.github.westsi.realchem.item;

import com.github.westsi.realchem.RealChemistry;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Optional;

public class Solution {
    public static final ArrayList<Item> SOLUTIONS = new ArrayList<>();
    private static Item createSolution(String name, int color) {
        Item item = new Item(new Item.Settings().component(ModDataComponentTypes.COMPOUND_COLOR, color).maxCount(4));
        Registry.register(Registries.ITEM, Identifier.of(RealChemistry.MOD_ID, name), item);
        SOLUTIONS.add(item);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CHEMICALS_ITEM_GROUP_KEY).register(entries -> {
            entries.add(item);
        });
        return item;
    }

    public static void registerSolutions() {
        createSolution("sulphuric_acid", 0xddf0e062);
        createSolution("copper_sulphate_sol", 0xdd1c52fb);
    }

    public static void generateSolutionModels(ItemModelGenerator itemModelGenerator) {
        Model model = new Model(Optional.of(Identifier.of(RealChemistry.MOD_ID, "item/beaker")), Optional.empty());
        for (Item i : SOLUTIONS) {
            itemModelGenerator.register(i, model);
        }
    }
}
