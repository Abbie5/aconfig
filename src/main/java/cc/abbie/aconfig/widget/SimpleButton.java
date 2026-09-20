package cc.abbie.aconfig.widget;

import net.minecraft.client.Minecraft;
//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else
//import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class SimpleButton extends Button {
    public SimpleButton(int x, int y, int width, int height, Component message, OnPress onPress, CreateNarration createNarration) {
        super(x, y, width, height, message, onPress, createNarration);
    }

    public SimpleButton(Component message) {
        this(message, b -> {});
    }

    public SimpleButton(Component message, OnPress onPress) {
        this(0, 0, 60, 14, message, onPress, DEFAULT_NARRATION);
    }

    public SimpleButton(Component message, int width) {
        this(message, width, 14);
    }

    public SimpleButton(Component message, int width, OnPress onPress) {
        this(message, 0, 0, width, 14, onPress);
    }

    public SimpleButton(Component message, int width, int height) {
        this(message, 0, 0, width, height);
    }

    public SimpleButton(Component message, int x, int y, int width, int height) {
        this(message, x, y, width, height, b -> {});
    }

    public SimpleButton(Component message, int x, int y, int width, int height, OnPress onPress) {
        this(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    @Override
    //? if >=26.1 {
    protected void extractContents(GuiGraphicsExtractor gui, int mouseX, int mouseY, float partialTick)
    //?} else if >=1.21.11 {
    /*protected void renderContents(GuiGraphics gui, int mouseX, int mouseY, float partialTick)
    *///?} else
    //protected void renderWidget(GuiGraphics gui, int mouseX, int mouseY, float partialTick)
    {
        int bgColor = this.isHovered() ? 0xc8666666 : 0xa0000000;
        int x = this.getX();
        int y = this.getY();
        int maxX = x + this.getWidth();
        int maxY = y + this.getHeight();
        gui.fill(x, y, maxX, maxY, bgColor);
        if (this.isFocused()) {
            //? if >=26.1 {
            gui.outline(this.getX(), this.getY(), this.getWidth(), this.getHeight(), -1);
            //?} else if 1.21.10 {
            /*gui.submitOutline(this.getX(), this.getY(), this.getWidth(), this.getHeight(), -1);
            *///?} else
            //gui.renderOutline(this.getX(), this.getY(), this.getWidth(), this.getHeight(), -1);
        }
        int textColor = this.active ? 0xffffff : 0xa0a0a0;
        //? if >=26.1 {
        this.extractDefaultLabel(gui.textRendererForWidget(this, GuiGraphicsExtractor.HoveredTextEffects.NONE));
        //?} else if >=1.21.11 {
        /*this.renderDefaultLabel(gui.textRendererForWidget(this, GuiGraphics.HoveredTextEffects.NONE));
        *///?} else
        //this.renderString(gui, Minecraft.getInstance().font, textColor | Mth.ceil(this.alpha * 255.0F) << 24);
    }
}
