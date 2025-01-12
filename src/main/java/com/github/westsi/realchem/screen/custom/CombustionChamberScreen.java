package com.github.westsi.realchem.screen.custom;

import com.github.westsi.realchem.RealChemistry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CombustionChamberScreen extends HandledScreen<CombustionChamberScreenHandler> {
    public static final Identifier GUI_TEXTURE = Identifier.of(RealChemistry.MOD_ID, "textures/gui/combustion_chamber/combustion_chamber_gui.png");
    public static final Identifier ARROW_TEXTURE = Identifier.of(RealChemistry.MOD_ID, "textures/gui/combustion_chamber/arrow_progress.png");
    public static final Identifier OXIDE_TEXTURE = Identifier.of("textures/block/amethyst_cluster.png");

    public CombustionChamberScreen(CombustionChamberScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        // Get rid of titles
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
        renderProgressOxide(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 73, y + 35, 0, 0,
                    handler.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    private void renderProgressOxide(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(OXIDE_TEXTURE,x + 104, y + 13 + 16 - handler.getScaledOxideProgress(), 0,
                    16 - handler.getScaledOxideProgress(), 16, handler.getScaledOxideProgress(),16, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
