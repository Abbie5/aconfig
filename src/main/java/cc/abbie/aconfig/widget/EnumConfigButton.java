package cc.abbie.aconfig.widget;

//? if >=1.21.10
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public class EnumConfigButton<E extends Enum<E>> extends AbstractTrackedValueConfigButton<E> {
    private final E[] values;
    
    public EnumConfigButton(TrackedValue<E> trackedValue) {
        super(trackedValue);
        
        this.values = (E[]) trackedValue.value().getClass().getEnumConstants();
    }

    @Override
    protected Component getText() {
        return Component.literal(trackedValue.value().name());
    }

    @Override
    //? if >=1.21.10 {
    public void onPress(InputWithModifiers inputWithModifiers)
    //?} else
    //public void onPress()
    {
        E oldValue = trackedValue.value();
        int ord = oldValue.ordinal();
        E newValue = values[(ord + 1) % values.length];
        trackedValue.setValue(newValue);
    }
}
