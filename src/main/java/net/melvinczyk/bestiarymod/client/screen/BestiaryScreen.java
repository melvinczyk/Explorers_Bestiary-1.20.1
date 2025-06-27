package net.melvinczyk.bestiarymod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.melvinczyk.bestiarymod.BestiaryMod;

public class BestiaryScreen extends Screen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BestiaryMod.MODID, "textures/gui/bestiary_placeholder.png");
    private final int imageWidth = 176;
    private final int imageHeight = 166;

    public BestiaryScreen() {
        super(Component.literal("Explorer's Bestiary"));
    }

    @Override
    protected void init() {
        super.init();
        // TODO: Add buttons, categories, and entries here later
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        RenderSystem.setShaderTexture(0, TEXTURE);
        graphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        graphics.drawCenteredString(font, title.getString(), width / 2, y + 10, 0xFFFFFF);

        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
