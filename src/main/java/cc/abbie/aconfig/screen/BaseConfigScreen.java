package cc.abbie.aconfig.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.aconfig.widget.BooleanConfigButton;
import cc.abbie.aconfig.widget.ConfigButton;
import cc.abbie.aconfig.widget.EnumConfigButton;
import cc.abbie.aconfig.widget.SimpleButton;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
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

        GridLayout inner = new GridLayout();
        GridLayout.RowHelper innerRows = inner.createRowHelper(1);

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
            gui.fill(x, y, maxX, maxY, 0xa0000000);
        });
        layout.visitWidgets(this::addRenderableWidget);

    }
    
    protected void addConfigButtons(Consumer<LayoutElement> consumer) {
        for (ValueTreeNode node : nodes) {
            ConfigButton button = getButton(node);
            if (button == null) continue;
            consumer.accept(button);
        }
    }
    
    @Nullable
    private ConfigButton getButton(ValueTreeNode node) {
        Component name = createComponent(node);
        if (node instanceof ValueTreeNode.Section section) {
            return new ConfigButton(name, b -> minecraft.setScreen(new BaseConfigScreen(name, section, this)));
        } else if (node instanceof TrackedValue<?> trackedValue) {
            Object defaultValue = trackedValue.getDefaultValue();
            if (defaultValue instanceof Boolean) {
                return new BooleanConfigButton(name, (TrackedValue<Boolean>) trackedValue);
            } else if (defaultValue instanceof Enum<?>) {
                return new EnumConfigButton<>(name, (TrackedValue<Enum>) trackedValue);
            }
        }
        return null;
    }
    
    public static Component createComponent(ValueTreeNode node) {
        return Component.translatable(
                "config.aconfig."
                        + (node instanceof ValueTreeNode.Section ? "category" : "option")
                        + "." + String.join(".", node.key())
        );
    }
    
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (minecraft.level == null) {
            renderPanorama(guiGraphics, partialTick);
            renderBlurredBackground(partialTick);
            renderMenuBackground(guiGraphics);
        }
    }
    
    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }
}
