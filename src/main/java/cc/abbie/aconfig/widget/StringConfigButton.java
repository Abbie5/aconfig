package cc.abbie.aconfig.widget;

import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public class StringConfigButton extends AbstractTrackedValueConfigButton<String> {
	public StringConfigButton(TrackedValue<String> trackedValue) {
		super(trackedValue);
	}

	@Override
	protected Component getText() {
		return Component.literal(trackedValue.value());
	}
}
