package cc.abbie.aconfig.widget;

//? if >=1.21.10
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public class BooleanConfigButton extends AbstractTrackedValueConfigButton<Boolean> {
    public BooleanConfigButton(TrackedValue<Boolean> trackedValue) {
        super(trackedValue);
    }

    @Override
    protected Component getText() {
        return trackedValue.value() ? Component.translatable("button.aconfig.toggle.enabled") : Component.translatable("button.aconfig.toggle.disabled");
    }

    @Override
    protected int getColor() {
        return trackedValue.value() ? 0xa000ff00 : 0xa0ff0000;
    }

    @Override
    //? if >=1.21.10 {
    public void onPress(InputWithModifiers inputWithModifiers)
    //?} else
    //public void onPress()
    {
        trackedValue.setValue(!trackedValue.value());
    }
}
