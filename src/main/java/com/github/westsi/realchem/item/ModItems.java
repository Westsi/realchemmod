package com.github.westsi.realchem.item;

import com.github.westsi.realchem.RealChemistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BASE_DUST = registerItem("base_dust", new Item(new Item.Settings()));
    public static final Item CARBON_DUST = registerItem("carbon_dust", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(RealChemistry.MOD_ID, name), item);
    }

    public static void registerModItems(){
        RealChemistry.LOGGER.info("Registering items for " + RealChemistry.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CARBON_DUST);
            entries.add(BASE_DUST);
        });
    }
}
