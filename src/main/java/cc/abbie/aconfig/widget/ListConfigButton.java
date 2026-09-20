package cc.abbie.aconfig.widget;

import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueList;

public class ListConfigButton extends AbstractTrackedValueConfigButton<ValueList<?>> {
	public ListConfigButton(TrackedValue<ValueList<?>> trackedValue) {
		super(trackedValue);
	}

	@Override
	public void onPress() {
		
	}
}
