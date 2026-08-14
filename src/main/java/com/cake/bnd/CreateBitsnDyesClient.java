package com.cake.bnd;

import com.cake.azimuth.Azimuth;
import com.cake.bnd.registry.client.BndPartialModels;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.cake.bnd.registry.core.BndConfigs;
import com.kipti.bnb.CreateBitsnBobs;
import net.createmod.catnip.config.ui.BaseConfigScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.function.Supplier;

@Mod(value = CreateBitsnDyes.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CreateBitsnDyes.MOD_ID, value = Dist.CLIENT)
public class CreateBitsnDyesClient {

    public CreateBitsnDyesClient(final ModContainer container) {
        BndSpriteShifts.register();
        BndPartialModels.register();
    }

    @SubscribeEvent
    static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(CreateBitsnDyesClient::clientInit);
    }

    private static void clientInit() {
        BaseConfigScreen.setDefaultActionFor(
                CreateBitsnDyes.MOD_ID, base -> base
                        .withButtonLabels(null, "Feature Settings", null)
                        .withSpecs(null, BndConfigs.common().specification, null)
        );
    }

    @EventBusSubscriber(Dist.CLIENT)
    private static class ModBusEvents {
        @SubscribeEvent
        public static void onLoadComplete(final FMLLoadCompleteEvent event) {
            final ModContainer modContainer = ModList.get()
                    .getModContainerById(CreateBitsnDyes.MOD_ID)
                    .orElseThrow(() -> new IllegalStateException("Bits n Dyes mod container missing on LoadComplete"));
            final Supplier<IConfigScreenFactory> configScreen = () -> (mc, previousScreen) -> new BaseConfigScreen(
                    previousScreen,
                    CreateBitsnDyes.MOD_ID
            );
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, configScreen);
        }
    }

}
