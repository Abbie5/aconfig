package cc.abbie.aconfig.screen;

import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.aconfig.widget.SimpleButton;
import folk.sisby.kaleido.lib.quiltconfig.api.Config;

public class ConfigScreen extends BaseConfigScreen {
    public ConfigScreen(Component title, @Nullable Screen parent, Config config) {
        super(title, config.nodes(), parent);
    }

    public ConfigScreen(@Nullable Screen parent, Config config) {
        this(Component.translatable("screen.aconfig.title", config.family(), config.id()), parent, config);
    }

    private static int boopCounter = 0;

    @Override
    protected void init() {
        GridLayout layout = new GridLayout().columnSpacing(5).rowSpacing(6);
        layout.defaultCellSetting().alignHorizontallyCenter();
        GridLayout.RowHelper rows = layout.createRowHelper(3);

        int titleWidth = font.width(title);
        rows.addChild(new SimpleButton(title, titleWidth + 4, b -> {
            boopCounter++;
            if (boopCounter > 10) {
                b.active = false;
                b.setMessage(Component.literal(">:("));
            }
        }), 3);

        GridLayout innerContainer = new GridLayout();
        innerContainer.defaultCellSetting().paddingHorizontal(2).paddingTop(2).paddingBottom(1);

        GridLayout inner = new GridLayout();
        GridLayout.RowHelper innerRows = inner.createRowHelper(2);

        addConfigButtons(innerRows::addChild);

        innerContainer.addChild(inner, 0, 0);

        rows.addChild(innerContainer, 3);

        rows.addChild(new SimpleButton(Component.translatable("config.aconfig.option.exit"), b -> onClose()));

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
}
