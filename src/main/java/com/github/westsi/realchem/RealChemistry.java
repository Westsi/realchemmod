package com.github.westsi.realchem;

import com.github.westsi.realchem.block.ModBlocks;
import com.github.westsi.realchem.block.entity.ModBlockEntities;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import com.github.westsi.realchem.item.ModItemGroups;
import com.github.westsi.realchem.item.ModItems;
import com.github.westsi.realchem.recipe.ModRecipes;
import com.github.westsi.realchem.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealChemistry implements ModInitializer {
	public static final String MOD_ID = "realchem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModDataComponentTypes.registerDataComponentTypes();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerModItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();
	}
}