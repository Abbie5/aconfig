package cc.abbie.aconfig.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
//? if >=1.21.10
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
//? if >=26.3 {
import org.lwjgl.sdl.SDLKeycode;
//?} else
//import org.lwjgl.glfw.GLFW;

import java.util.Optional;

public class IntegerConfigButton extends EditBox {
	private final TrackedValue<Integer> trackedValue;
	private boolean error = false;
	
	public IntegerConfigButton(TrackedValue<Integer> trackedValue) {
		//? if >=1.20.2 {
		super(Minecraft.getInstance().font, 54, 10, Component.empty());
		//?} else
		//super(Minecraft.getInstance().font, 0, 0, 54, 10, Component.empty());
		
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
	//? if >=1.21.10 {
	public boolean keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.key();
	//?} else {
	/*public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		*///?}
		//? if >=26.3 {
		if (this.isActive() && this.isFocused() && (keyCode == SDLKeycode.SDLK_RETURN || keyCode == SDLKeycode.SDLK_KP_ENTER))
		//?} else
		//if (this.isActive() && this.isFocused() && (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER))
		{
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
		//? if >=1.21.10 {
		return super.keyPressed(keyEvent);
		//?} else
		//return super.keyPressed(keyCode, scanCode, modifiers);
	}
}
