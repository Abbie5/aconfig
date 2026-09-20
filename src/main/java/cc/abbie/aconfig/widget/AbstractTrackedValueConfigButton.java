package cc.abbie.aconfig.widget;

import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public abstract class AbstractTrackedValueConfigButton<T> extends ConfigButton {
	protected final TrackedValue<T> trackedValue;
	
	public AbstractTrackedValueConfigButton(TrackedValue<T> trackedValue) {
		super();
		
		this.trackedValue = trackedValue;
	}
}
