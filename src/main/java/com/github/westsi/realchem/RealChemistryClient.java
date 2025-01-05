package com.github.westsi.realchem;

import com.github.westsi.realchem.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Items;

public class RealChemistryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        RealChemistry.LOGGER.warn("INITIALIZING CLIENT");
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xFF0000, ModItems.BASE_DUST);
    }
}
