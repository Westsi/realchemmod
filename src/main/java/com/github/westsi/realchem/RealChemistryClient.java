package com.github.westsi.realchem;

import com.github.westsi.realchem.block.ModBlocks;
import com.github.westsi.realchem.block.entity.ModBlockEntities;
import com.github.westsi.realchem.block.entity.renderer.LabBenchBlockEntityRenderer;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import com.github.westsi.realchem.item.ModItems;
import com.github.westsi.realchem.item.Dust;
import com.github.westsi.realchem.item.Solution;
import com.github.westsi.realchem.screen.ModScreenHandlers;
import com.github.westsi.realchem.screen.custom.CombustionChamberScreen;
import com.github.westsi.realchem.screen.custom.LabBenchScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.ItemConvertible;

public class RealChemistryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        RealChemistry.LOGGER.warn("INITIALIZING CLIENT");
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0xffff0000, ModBlocks.METAL_BLOCK);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xffff0000, ModBlocks.METAL_BLOCK);
//        ColorProviderRegistry.ITEM.register(
//                (stack, tintIndex) -> stack.getOrDefault(ModDataComponentTypes.COMPOUND_COLOR, 0xffff00ff),
//                Dust.DUSTS.toArray(ItemConvertible[]::new));
        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> 0xff000000 | stack.getOrDefault(ModDataComponentTypes.COMPOUND_COLOR, 0xffff00ff),
                ModItems.DUST);

//        ColorProviderRegistry.ITEM.register(
//                (stack, tintIndex) -> {
//                    if (tintIndex == 1) {
//                        return stack.getOrDefault(ModDataComponentTypes.COMPOUND_COLOR, 0xffff00ff);
//                    }
//                    return -1;
//                },
//                Solution.SOLUTIONS.toArray(ItemConvertible[]::new));

        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> {
                    if (tintIndex == 1) {
                        return stack.getOrDefault(ModDataComponentTypes.COMPOUND_COLOR, 0xffff00ff);
                    }
                    return -1;
                },
                ModItems.BEAKER);

        BlockEntityRendererFactories.register(ModBlockEntities.LAB_BENCH_BE, LabBenchBlockEntityRenderer::new);

        HandledScreens.register(ModScreenHandlers.LAB_BENCH_SCREEN_HANDLER, LabBenchScreen::new);
        HandledScreens.register(ModScreenHandlers.COMBUSTION_CHAMBER_SCREEN_HANDLER, CombustionChamberScreen::new);
    }
}