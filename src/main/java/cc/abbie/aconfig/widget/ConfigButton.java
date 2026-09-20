package cc.abbie.aconfig.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ConfigButton extends Button {

    public ConfigButton(Component message, OnPress onPress) {
        super(0, 0, 174, 10, message, onPress, DEFAULT_NARRATION);
    }

    public ConfigButton(Component message) {
        this(message, b -> {});
    }
    
    protected Component getText() {
        return Component.literal("-->");
    }
    
    protected int getColor() {
        return 0xa0404040;
    }

    @Override
    protected void renderWidget(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        int textColor = this.isHovered() ? -1 : 0xffc0c0c0;
        int bgColor = this.isHovered() ? 0x66ffffff : getColor();

        if (this.isFocused()) {
            gui.renderOutline(this.getX()-1, this.getY()-1, this.getWidth()+2, this.getHeight()+1, -1);
        }

        Font font = Minecraft.getInstance().font;
        gui.drawString(font, getMessage(), this.getX(), this.getY()+1, textColor);

        int maxX = this.getX() + this.getWidth();
        int maxY = this.getY() + this.getHeight();

        int valueX = maxX - 54;
        int valueY = this.getY();

        gui.fill(valueX, valueY, maxX, maxY-1, bgColor);
        renderScrollingString(gui, font, getText(), valueX, valueY, maxX, maxY-1, -1);
    }
}
