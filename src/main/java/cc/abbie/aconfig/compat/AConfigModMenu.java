package cc.abbie.aconfig.compat;

import cc.abbie.aconfig.screen.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import folk.sisby.kaleido.lib.quiltconfig.api.Config;
import folk.sisby.kaleido.lib.quiltconfig.impl.util.ConfigsImpl;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AConfigModMenu implements ModMenuApi {
    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        Map<String, ConfigScreenFactory<?>> modConfigs = new HashMap<>();
        // i'm sorry for using implementation classes, the equivalent api class is
        // missing from kaleido
        // also most of this is copied from McQoy
        for (Config config : ConfigsImpl.getAll()) {
            String modId = config.family().isEmpty() ? config.id() : config.family();
            for (String s : Arrays.asList(
                    modId,
                    modId.replace("-", ""),
                    modId.replace("_", ""),
                    modId.replace("_", "-"),
                    modId.replace("-", "_")
            )) {
                if (FabricLoader.getInstance().isModLoaded(s)) {
                    modConfigs.put(s, parent -> new ConfigScreen(parent, config));
                    break;
                }
            }
        }
        return modConfigs;
    }
}
