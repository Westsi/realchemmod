package com.github.westsi.realchem.block;

import com.github.westsi.realchem.RealChemistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
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

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(RealChemistry.MOD_ID, name), new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, Identifier.of(RealChemistry.MOD_ID, name), block);
    }

    public static void registerModBlocks(){
        RealChemistry.LOGGER.info("Registering blocks for " + RealChemistry.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(METAL_BLOCK);
        });
    }
}
