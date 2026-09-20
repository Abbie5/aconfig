package cc.abbie.aconfig.widget;

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
    public void onPress() {
        E oldValue = trackedValue.value();
        int ord = oldValue.ordinal();
        E newValue = values[(ord + 1) % values.length];
        trackedValue.setValue(newValue);
    }
}
