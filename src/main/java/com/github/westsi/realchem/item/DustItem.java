package com.github.westsi.realchem.item;

import com.github.westsi.realchem.chemistry.color.CustomFormatting;
import com.github.westsi.realchem.chemistry.formula.FormulaResolver;
import com.github.westsi.realchem.component.ModDataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Objects;

public class DustItem extends Item {
    public DustItem(Settings settings) {
        super(settings);
        settings.component(ModDataComponentTypes.COMPOUND_COLOR, 0xffffffff)
                .component(ModDataComponentTypes.COMPOUND_SMILES, "")
                .component(ModDataComponentTypes.COMPOUND_NAME, "");
    }


    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        String smiles = stack.getOrDefault(ModDataComponentTypes.COMPOUND_SMILES, "");
        String name = stack.getOrDefault(ModDataComponentTypes.COMPOUND_NAME, "");
        if (Objects.equals(name, "")) {
            name = FormulaResolver.getChemicalNameFromSMILES(smiles);
        }
        Integer color = stack.getOrDefault(ModDataComponentTypes.COMPOUND_COLOR, 0xffffff);
        tooltip.add(MutableText.of(Text.of(name).getContent()).formatted(CustomFormatting.getClosestFormattingToHex(color)));
        tooltip.add(MutableText.of(Text.of((FormulaResolver.getFormulaFromSMILES(smiles))).getContent()).formatted(CustomFormatting.getClosestFormattingToHex(color)));
    }
}
