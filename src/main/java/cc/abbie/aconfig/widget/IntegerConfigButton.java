package cc.abbie.aconfig.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.Constraint;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.Optional;

public class IntegerConfigButton extends EditBox {
	private final TrackedValue<Integer> trackedValue;
	private boolean error = false;
	
	public IntegerConfigButton(TrackedValue<Integer> trackedValue) {
		super(Minecraft.getInstance().font, 54, 10, Component.empty());
		
		this.trackedValue = trackedValue;
		
		this.setValue(trackedValue.value().toString());
	}
	
	private Optional<Integer> validate(String s) {
		try {
			int value = Integer.parseInt(s);
			if (trackedValue.checkForFailingConstraints(value).isEmpty()) {
				return Optional.of(value);
			}
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
		return Optional.empty();
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (this.isActive() && this.isFocused() && (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER)) {
			// validate & submit value
			String s = this.getValue();
			Optional<Integer> o = this.validate(s);
			if (o.isPresent()) {
				trackedValue.setValue(o.orElseThrow());
				this.setTextColor(0x00ff00);
			} else {
				this.setTextColor(0xff0000);
			}
			return true;
		}
		return super.keyPressed(keyCode, scanCode, modifiers);
	}
}
