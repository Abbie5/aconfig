package cc.abbie.aconfig.widget;

import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public class BooleanConfigButton extends ConfigButton {
    private final TrackedValue<Boolean> trackedValue;
    
    public BooleanConfigButton(Component message, TrackedValue<Boolean> trackedValue) {
        super(message);
        
        this.trackedValue = trackedValue;
    }

    @Override
    protected Component getText() {
        return trackedValue.value() ? Component.translatable("button.amap.toggle.enabled") : Component.translatable("button.amap.toggle.disabled");
    }

    @Override
    protected int getColor() {
        return trackedValue.value() ? 0xa000ff00 : 0xa0ff0000;
    }

    @Override
    public void onPress() {
        trackedValue.setValue(!trackedValue.value());
    }
}
