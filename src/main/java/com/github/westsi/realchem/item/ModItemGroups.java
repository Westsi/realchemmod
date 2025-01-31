package com.github.westsi.realchem.item;

import com.github.westsi.realchem.RealChemistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> CHEMICALS_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(RealChemistry.MOD_ID, "chemicals"));
    public static final ItemGroup CHEMICALS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Dust.DUSTS.getFirst()))
            .displayName(Text.translatable("itemGroup.chemicals"))
            .build();
    public static void registerModItemGroups() {
        RealChemistry.LOGGER.info("Registering Item Groups for " + RealChemistry.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, CHEMICALS_ITEM_GROUP_KEY, CHEMICALS_ITEM_GROUP);
    }
}
