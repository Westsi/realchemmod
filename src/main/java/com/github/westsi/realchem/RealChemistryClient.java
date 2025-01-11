package com.github.westsi.realchem;

import com.github.westsi.realchem.block.ModBlocks;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import com.github.westsi.realchem.item.ModItems;
import com.github.westsi.realchem.item.Dust;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.ItemConvertible;

public class RealChemistryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        RealChemistry.LOGGER.warn("INITIALIZING CLIENT");
//        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> (tintIndex == 1 ? 15582019 : -1), ModItems.BASE_DUST);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0xffff0000, ModBlocks.METAL_BLOCK);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xffff0000, ModBlocks.METAL_BLOCK);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xffff0000, ModItems.BASE_DUST);
        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> stack.getOrDefault(ModDataComponentTypes.COMPOUND_COLOR, 0xffff00ff),
                Dust.DUSTS.toArray(ItemConvertible[]::new));
    }
}