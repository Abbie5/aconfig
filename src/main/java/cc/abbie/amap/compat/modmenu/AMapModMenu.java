package cc.abbie.amap.compat.modmenu;

import cc.abbie.amap.client.AMapClient;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class AMapModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return AMapClient::createConfigScreen;
    }
}
