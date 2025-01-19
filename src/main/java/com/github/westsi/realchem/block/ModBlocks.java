package com.github.westsi.realchem.block;

import com.github.westsi.realchem.RealChemistry;
import com.github.westsi.realchem.block.custom.CombustionChamberBlock;
import com.github.westsi.realchem.block.custom.LabBenchBlock;
import com.github.westsi.realchem.item.ModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block METAL_BLOCK = registerBlock("metal_block",
            new Block(Block.Settings.create().sounds(BlockSoundGroup.METAL).hardness(5f)));

    public static final Block LAB_BENCH_BLOCK = registerBlock("lab_bench",
            new LabBenchBlock(Block.Settings.create().strength(4f).requiresTool().nonOpaque()));

    public static final Block COMBUSTION_CHAMBER_BLOCK = registerBlock("combustion_chamber",
            new CombustionChamberBlock(Block.Settings.create().strength(4f).requiresTool().nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(RealChemistry.MOD_ID, name), new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, Identifier.of(RealChemistry.MOD_ID, name), block);
    }

    public static void registerModBlocks(){
        RealChemistry.LOGGER.info("Registering blocks for " + RealChemistry.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CHEMICALS_ITEM_GROUP_KEY).register(entries -> {
            entries.add(METAL_BLOCK);
        });
    }
}
