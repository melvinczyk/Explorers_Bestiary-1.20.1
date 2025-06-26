package net.melvinczyk.bestiarymod;

import com.mojang.logging.LogUtils;
import net.melvinczyk.bestiarymod.config.CommonConfig;
import net.melvinczyk.bestiarymod.item.ModItems;
import net.melvinczyk.bestiarymod.network.NetworkHandler;

import net.minecraft.world.item.CreativeModeTabs;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

@Mod(BestiaryMod.MODID)
public class BestiaryMod {
    public static final String MODID = "explorers_bestiary";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BestiaryMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::commonSetup);
        modBus.addListener(this::addCreative);
        ModItems.register(modBus);
        modBus.addListener(ModItems::addToCreativeTab);

        MinecraftForge.EVENT_BUS.register(this);

        NetworkHandler.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("BestiaryMod common setup complete.");
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server is starting - BestiaryMod hook active.");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Register client GUIs, keybinds etc. here
        }
    }
}
