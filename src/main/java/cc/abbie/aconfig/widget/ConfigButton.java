package cc.abbie.aconfig.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else if >=1.20.1 {
/*import net.minecraft.client.gui.GuiGraphics;
*///?} else
//import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ConfigButton extends Button {

    public ConfigButton(OnPress onPress) {
        super(0, 0, 54, 10, Component.empty(), onPress, DEFAULT_NARRATION);
    }

    public ConfigButton() {
        this(b -> {});
    }
    
    protected Component getText() {
        return Component.literal("-->");
    }
    
    protected int getColor() {
        return 0xa0404040;
    }

    @Override
    //? if >=26.1 {
    protected void extractContents(GuiGraphicsExtractor gui, int mouseX, int mouseY, float partialTick)
    //? } else if >=1.21.11 {
    /*protected void renderContents(GuiGraphics gui, int mouseX, int mouseY, float partialTick)
    *///?} else if >=1.20.1 {
    /*protected void renderWidget(GuiGraphics gui, int mouseX, int mouseY, float partialTick)
    *///?} else
    //public void renderWidget(PoseStack gui, int mouseX, int mouseY, float partialTick)
    {
        int textColor = this.isHovered() ? -1 : 0xffc0c0c0;
        int bgColor = this.isHovered() ? 0x66ffffff : getColor();

        if (this.isFocused()) {
            //? if >=26.1 {
            gui.outline(this.getX()-1, this.getY()-1, this.getWidth()+2, this.getHeight()+1, -1);
            //?} else if 1.21.10 {
            /*gui.submitOutline(this.getX()-1, this.getY()-1, this.getWidth()+2, this.getHeight()+1, -1);
            *///?} else if >=1.20.1 {
            /*gui.renderOutline(this.getX()-1, this.getY()-1, this.getWidth()+2, this.getHeight()+1, -1);
            *///?} else
            //renderOutline(gui, this.getX()-1, this.getY()-1, this.getWidth()+2, this.getHeight()+1, -1);
        }

        Font font = Minecraft.getInstance().font;
        //? if >=26.1 {
        gui.text(font, getMessage(), this.getX(), this.getY()+1, textColor);
        //?} else if >=1.20.1 {
        /*gui.drawString(font, getMessage(), this.getX(), this.getY()+1, textColor);
        *///?} else
        //drawString(gui, font, getMessage(), this.getX(), this.getY()+1, textColor);

        int maxX = this.getX() + this.getWidth();
        int maxY = this.getY() + this.getHeight();

        int valueX = maxX - 54;
        int valueY = this.getY();

        //? if >=1.20.1 {
        gui.fill(valueX, valueY, maxX, maxY-1, bgColor);
        //?} else
        //fill(gui, valueX, valueY, maxX, maxY-1, bgColor);
        
        //? if >=26.1 {
        this.extractDefaultLabel(gui.textRendererForWidget(this, GuiGraphicsExtractor.HoveredTextEffects.NONE));
        //?} else if >=1.21.11 {
        /*this.renderDefaultLabel(gui.textRendererForWidget(this, GuiGraphics.HoveredTextEffects.NONE));
        *///?} else
        //renderScrollingString(gui, font, getText(), valueX, valueY, maxX, maxY-1, -1);
    }
}
