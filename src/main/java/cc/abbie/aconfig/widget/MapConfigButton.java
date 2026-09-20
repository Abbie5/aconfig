package cc.abbie.aconfig.widget;

import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueMap;

public class MapConfigButton extends AbstractTrackedValueConfigButton<ValueMap<?>> {
	public MapConfigButton(TrackedValue<ValueMap<?>> trackedValue) {
		super(trackedValue);
	}

}
