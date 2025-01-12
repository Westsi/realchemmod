package com.github.westsi.realchem.datagen;

import com.github.westsi.realchem.block.ModBlocks;
import com.github.westsi.realchem.item.ModItems;
import com.github.westsi.realchem.item.Dust;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.METAL_BLOCK);
        blockStateModelGenerator.registerSingleton(ModBlocks.METAL_BLOCK, TexturedModel.LEAVES);
        blockStateModelGenerator.registerCooker(ModBlocks.COMBUSTION_CHAMBER_BLOCK, TexturedModel.ORIENTABLE);
//        blockStateModelGenerator.register();
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BASE_DUST, Models.GENERATED);
        Dust.generateDustModels(itemModelGenerator);
    }
}
