package cc.abbie.aconfig.compat;

//? if fabric {
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
//?} else if forge {
/*import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
*///?} else if neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
*///?}

import net.minecraft.client.gui.screens.Screen;
import cc.abbie.aconfig.screen.ConfigScreen;
import folk.sisby.kaleido.lib.quiltconfig.api.Config;
import folk.sisby.kaleido.lib.quiltconfig.impl.util.ConfigsImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

//? if neoforge || forge
//@Mod("aconfig")
public class AConfigModMenu
//? if fabric
        implements ModMenuApi 
{
    //? if neoforge || forge {
    /*public AConfigModMenu(
            //? if neoforge
            //ModContainer mod
    ) {
        //? if neoforge{
        /^IEventBus modBus = mod.getEventBus();
        ^///?} else {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        //?}
        modBus.addListener((FMLLoadCompleteEvent event) -> innerScreenFactories().forEach((id, factory) ->
                ModList.get()
                        .getModContainerById(id)
                        .ifPresent(c -> c.registerExtensionPoint(
                                //? if neoforge {
                                /^IConfigScreenFactory.class,
                                (mc1, parent) -> factory.apply(parent)
                                ^///?} else {
                                ConfigScreenHandler.ConfigScreenFactory.class,
                                () -> new ConfigScreenHandler.ConfigScreenFactory(factory)
                                //?}
                        ))
        ));
    }
    *///?} else if fabric {
    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return innerScreenFactories()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, 
                        e -> (ConfigScreenFactory<?>) e.getValue()
                ));
    }
    //?}
    
    private static Map<String, UnaryOperator<Screen>> innerScreenFactories() {
        Map<String, UnaryOperator<Screen>> modConfigs = new HashMap<>();
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
                if (
                    //? if fabric {
                    FabricLoader.getInstance().isModLoaded(s)
                    //?} else if neoforge || forge {
                    /*ModList.get().isLoaded(s)
                    *///?}
                ) {
                    modConfigs.put(s, parent -> new ConfigScreen(parent, config));
                    break;
                }
            }
        }
        return modConfigs;
    }
}
