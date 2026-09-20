package cc.abbie.amap.client;

import cc.abbie.amap.client.minimap.MinimapHud;
import cc.abbie.amap.client.worldmap.WorldMapScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class AMapKeybinds implements ClientTickEvents.EndTick {

    private static final String WORLD_MAP_CATEGORY = "key.categories.amap.worldmap";
    public static final KeyMapping OPEN_WORLDMAP = new KeyMapping("key.amap.worldmap.open", GLFW.GLFW_KEY_M, WORLD_MAP_CATEGORY);

    private static final String MINIMAP_CATEGORY = "key.categories.amap.minimap";
    private static final KeyMapping ZOOM_OUT_MINIMAP = new KeyMapping("key.amap.minimap.zoom_out", GLFW.GLFW_KEY_MINUS, MINIMAP_CATEGORY);
    private static final KeyMapping ZOOM_IN_MINIMAP = new KeyMapping("key.amap.minimap.zoom_in", GLFW.GLFW_KEY_EQUAL, MINIMAP_CATEGORY);
    private static final KeyMapping TOGGLE_ROTATION = new KeyMapping("key.amap.minimap.toggle_rotation", GLFW.GLFW_KEY_R, MINIMAP_CATEGORY);
    public static final KeyMapping OPEN_MINIMAP_CONFIG = new KeyMapping("key.amap.minimap.config", GLFW.GLFW_KEY_PERIOD, MINIMAP_CATEGORY);

    public static void register() {
        KeyBindingHelper.registerKeyBinding(OPEN_WORLDMAP);
        KeyBindingHelper.registerKeyBinding(ZOOM_OUT_MINIMAP);
        KeyBindingHelper.registerKeyBinding(ZOOM_IN_MINIMAP);
        KeyBindingHelper.registerKeyBinding(TOGGLE_ROTATION);
        KeyBindingHelper.registerKeyBinding(OPEN_MINIMAP_CONFIG);

        ClientTickEvents.END_CLIENT_TICK.register(new AMapKeybinds());
    }


    @Override
    public void onEndTick(Minecraft client) {
        if (ZOOM_OUT_MINIMAP.consumeClick()) {
            MinimapHud.zoomOut();
        } else if (ZOOM_IN_MINIMAP.consumeClick()) {
            MinimapHud.zoomIn();
        } else if (OPEN_WORLDMAP.consumeClick()) {
            client.setScreen(new WorldMapScreen());
        } else if (TOGGLE_ROTATION.consumeClick()) {
            MinimapHud.rotate = !MinimapHud.rotate;
        } else if (OPEN_MINIMAP_CONFIG.consumeClick()) {
            client.setScreen(AMapClient.createConfigScreen(null));
        }
    }
}
