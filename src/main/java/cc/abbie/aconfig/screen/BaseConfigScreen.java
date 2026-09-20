package cc.abbie.aconfig.screen;

import net.minecraft.client.Minecraft;
//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else if >=1.20.1
//import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.aconfig.widget.BooleanConfigButton;
import cc.abbie.aconfig.widget.ConfigButton;
import cc.abbie.aconfig.widget.EnumConfigButton;
import cc.abbie.aconfig.widget.IntegerConfigButton;
import cc.abbie.aconfig.widget.ListConfigButton;
import cc.abbie.aconfig.widget.MapConfigButton;
import cc.abbie.aconfig.widget.SimpleButton;
import cc.abbie.aconfig.widget.StringConfigButton;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueList;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueMap;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueTreeNode;

import java.util.function.Consumer;

public class BaseConfigScreen extends Screen {
    private final Screen parent;
    private final Iterable<ValueTreeNode> nodes;
    
    protected BaseConfigScreen(Component title, Iterable<ValueTreeNode> nodes, @Nullable Screen parent) {
        super(title);
        
        this.parent = parent;
        this.nodes = nodes;
    }

    @Override
    protected void init() {
        GridLayout layout = new GridLayout().columnSpacing(5).rowSpacing(6);
        layout.defaultCellSetting().alignHorizontallyCenter();
        GridLayout.RowHelper rows = layout.createRowHelper(1);

        int titleWidth = font.width(title);
        rows.addChild(new SimpleButton(title, titleWidth + 4));

        GridLayout innerContainer = new GridLayout();
        innerContainer.defaultCellSetting().paddingHorizontal(2).paddingTop(2).paddingBottom(1);

        GridLayout inner = new GridLayout().columnSpacing(2);
        GridLayout.RowHelper innerRows = inner.createRowHelper(2);

        addConfigButtons(innerRows::addChild);
        
        innerContainer.addChild(inner, 0, 0);

        rows.addChild(innerContainer);

        rows.addChild(new SimpleButton(Component.translatable("config.aconfig.category.back"), b -> onClose()));

        layout.arrangeElements();
        FrameLayout.alignInRectangle(layout, 0, 0, this.width, this.height, 0.5f, 0.5f);
        this.addRenderableOnly((gui, mouseX, mouseY, partialTick) -> {
            int x = innerContainer.getX();
            int y = innerContainer.getY();
            int maxX = x + innerContainer.getWidth();
            int maxY = y + innerContainer.getHeight();
            //? if >=1.20.1 {
            gui.fill(x, y, maxX, maxY, 0xa0000000);
            //?} else
            //fill(gui, x, y, maxX, maxY, 0xa0000000);
        });
        layout.visitWidgets(this::addRenderableWidget);

    }
    
    protected void addConfigButtons(Consumer<LayoutElement> consumer) {
        for (ValueTreeNode node : nodes) {
            LayoutElement button = getButton(node);
            if (button == null) continue;
            consumer.accept(new StringWidget(createComponent(node), Minecraft.getInstance().font));
            consumer.accept(button);
        }
    }
    
    @Nullable
    private LayoutElement getButton(ValueTreeNode node) {
        Component name = createComponent(node);
        if (node instanceof ValueTreeNode.Section section) {
            return new ConfigButton(b -> {
                //? if >=26.2 {
                minecraft.setScreenAndShow(new BaseConfigScreen(name, section, this));
                //?} else
                //minecraft.setScreen(new BaseConfigScreen(name, section, this));
            });
        } else if (node instanceof TrackedValue<?> trackedValue) {
            Object defaultValue = trackedValue.getDefaultValue();
            if (defaultValue instanceof Boolean) {
                return new BooleanConfigButton((TrackedValue<Boolean>) trackedValue);
            } else if (defaultValue instanceof Enum<?>) {
                return new EnumConfigButton<>((TrackedValue<Enum>) trackedValue);
            } else if (defaultValue instanceof Integer) {
                return new IntegerConfigButton((TrackedValue<Integer>) trackedValue);
            } else if (defaultValue instanceof String) {
                return new StringConfigButton((TrackedValue<String>) trackedValue);
            } else if (defaultValue instanceof ValueList<?>) {
                return new ListConfigButton((TrackedValue<ValueList<?>>) trackedValue);
            } else if (defaultValue instanceof ValueMap<?>) {
                return new MapConfigButton((TrackedValue<ValueMap<?>>) trackedValue);
            }
        }
        return null;
    }
    
    public static Component createComponent(ValueTreeNode node) {
        return Component.literal(node.key().getLastComponent());
    }
    
//    @Override
//    //? if >=26.1 {
//    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick)
//    //? } else
//    //public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
//    {
//        if (minecraft.level == null) {
//            //? if >=26.1 {
//            extractPanorama(guiGraphics, partialTick);
//            extractBlurredBackground(guiGraphics);
//            extractMenuBackground(guiGraphics);
//            //? } else {
//            /*renderPanorama(guiGraphics, partialTick);
//            //? if >=1.21.8 {
//            renderBlurredBackground(guiGraphics);
//            //?} else if >=1.21.3 {
//            /^renderBlurredBackground();
//            ^///?} else
//            //renderBlurredBackground(partialTick);
//            renderMenuBackground(guiGraphics);
//            *///?}
//        }
//    }
    
    @Override
    public void onClose() {
        //? if >=26.2 {
        minecraft.setScreenAndShow(parent);
        //?} else
        //minecraft.setScreen(parent);
    }
}
