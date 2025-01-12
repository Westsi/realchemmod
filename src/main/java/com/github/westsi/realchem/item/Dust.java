package com.github.westsi.realchem.item;

import com.github.westsi.realchem.RealChemistry;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class Dust {
    public static final ArrayList<Item> DUSTS = new ArrayList<>();

    private static Item createDust(String name, int color) {
        Item item = new Item(new Item.Settings().component(ModDataComponentTypes.COMPOUND_COLOR, color));
        Registry.register(Registries.ITEM, Identifier.of(RealChemistry.MOD_ID, name), item);
        DUSTS.add(item);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CHEMICALS_ITEM_GROUP_KEY).register(entries -> {
            entries.add(item);
        });
        return item;
    }

    public static void registerDusts() {
        createDust("sulphur_dust", 0xfff1dd38);
        createDust("carbon_dust", 0xff303030);
    }

    public static void generateDustModels(ItemModelGenerator itemModelGenerator) {
        for (Item i : DUSTS) {
            itemModelGenerator.register(i, ModItems.BASE_DUST, Models.GENERATED);
        }
    }
    // TODO: make a block entity to do reactions in
    //  create the output of the reaction on the fly - register, inject model json, add tinting
}
