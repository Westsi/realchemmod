package com.github.westsi.realchem;

import com.github.westsi.realchem.block.ModBlocks;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import com.github.westsi.realchem.item.ModItemGroups;
import com.github.westsi.realchem.item.ModItems;
import com.github.westsi.realchem.item.Dust;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealChemistry implements ModInitializer {
	public static final String MOD_ID = "realchem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		Dust.registerDusts();
		ModItemGroups.registerModItemGroups();
		ModDataComponentTypes.registerDataComponentTypes();
	}
}