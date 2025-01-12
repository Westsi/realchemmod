package com.github.westsi.realchem.screen;

import com.github.westsi.realchem.RealChemistry;
import com.github.westsi.realchem.screen.custom.CombustionChamberScreenHandler;
import com.github.westsi.realchem.screen.custom.LabBenchScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<LabBenchScreenHandler> LAB_BENCH_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(RealChemistry.MOD_ID, "lab_bench_screen_handler"),
                    new ExtendedScreenHandlerType<>(LabBenchScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<CombustionChamberScreenHandler> COMBUSTION_CHAMBER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(RealChemistry.MOD_ID, "combustion_chamber_screen_handler"),
                    new ExtendedScreenHandlerType<>(CombustionChamberScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        RealChemistry.LOGGER.info("Registering Screen Handlers for " + RealChemistry.MOD_ID);
    }
}
