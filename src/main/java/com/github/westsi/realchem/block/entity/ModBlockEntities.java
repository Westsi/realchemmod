package com.github.westsi.realchem.block.entity;

import com.github.westsi.realchem.RealChemistry;
import com.github.westsi.realchem.block.ModBlocks;
import com.github.westsi.realchem.block.entity.custom.LabBenchBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<LabBenchBlockEntity> LAB_BENCH_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(RealChemistry.MOD_ID, "lab_bench_be"),
                    BlockEntityType.Builder.create(LabBenchBlockEntity::new, ModBlocks.LAB_BENCH_BLOCK).build());

    public static void registerBlockEntities() {
        RealChemistry.LOGGER.info("Registering Block Entities for " + RealChemistry.MOD_ID);
    }
}
