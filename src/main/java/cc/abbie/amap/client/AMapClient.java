package cc.abbie.amap.client;

import net.minecraft.SharedConstants;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.aconfig.screen.ConfigScreen;
import cc.abbie.amap.AMap;
import cc.abbie.amap.client.minimap.MinimapHud;
import cc.abbie.amap.client.minimap.config.MinimapConfig;
import folk.sisby.surveyor.WorldSummary;
import folk.sisby.surveyor.client.SurveyorClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class AMapClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new MinimapHud());
        WorldRenderEvents.AFTER_ENTITIES.register(new AMapWorldRenderer());

        WorldSummary.enableTerrain();
        WorldSummary.enableLandmarks();

        SurveyorClientEvents.Register.worldLoad(AMap.id("world_load"), MapStorage.INSTANCE);
        SurveyorClientEvents.Register.terrainUpdated(AMap.id("terrain_updated"), MapStorage.INSTANCE);
        SurveyorClientEvents.Register.landmarksAdded(AMap.id("landmarks_added"), MapStorage.INSTANCE);
        SurveyorClientEvents.Register.landmarksRemoved(AMap.id("landmarks_removed"), MapStorage.INSTANCE);

        AMapKeybinds.register();
    }
    
    public static Screen createConfigScreen(@Nullable Screen parent) {
        return new ConfigScreen(
                Component.literal(String.format("%s v%s [%s]", AMap.MOD_NAME, AMap.MOD_VERSION, SharedConstants.getCurrentVersion().getName())),
                parent, 
                MinimapConfig.INSTANCE
        );
    }
}
