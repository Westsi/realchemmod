package com.github.westsi.realchem.item;

import com.github.westsi.realchem.RealChemistry;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
//    public static final Item DUST = registerItem("dust", new Item(
//            new Item.Settings()
//                    .component(ModDataComponentTypes.COMPOUND_COLOR, 0xffffffff)
//                    .component(ModDataComponentTypes.COMPOUND_SMILES, "")
//                    .component(ModDataComponentTypes.COMPOUND_NAME, "")));
//    public static final Item BEAKER = registerItem("beaker", new Item(
//            new Item.Settings()
//                    .component(ModDataComponentTypes.COMPOUND_COLOR, 0x00000000)
//                    .component(ModDataComponentTypes.COMPOUND_SMILES, "")
//                    .component(ModDataComponentTypes.COMPOUND_NAME, "")));

    public static final Item DUST = registerItem("dust", new DustItem(new Item.Settings()));
    public static final Item BEAKER = registerItem("beaker", new BeakerItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(RealChemistry.MOD_ID, name), item);
    }

    public static void registerModItems(){
        RealChemistry.LOGGER.info("Registering items for " + RealChemistry.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CHEMICALS_ITEM_GROUP_KEY).register(entries -> {
            entries.add(DUST);
            entries.add(BEAKER);
        });
    }
}
