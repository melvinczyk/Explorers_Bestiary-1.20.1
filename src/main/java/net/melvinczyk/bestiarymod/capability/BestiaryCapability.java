package net.melvinczyk.bestiarymod.capability;

import net.melvinczyk.bestiarymod.BestiaryMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BestiaryMod.MODID)
public class BestiaryCapability {

    // ✅ Correct capability reference
    public static final Capability<BestiaryData> BESTIARY =
            CapabilityManager.get(new net.minecraftforge.common.capabilities.CapabilityToken<>() {});

    // Resource ID for the capability
    public static final ResourceLocation ID = new ResourceLocation(BestiaryMod.MODID, "bestiary");

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BestiaryData.class);
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<?> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ID, new BestiaryProvider());
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(BESTIARY).ifPresent(oldData -> {
            event.getEntity().getCapability(BESTIARY).ifPresent(newData -> {
                newData.loadNBT(oldData.saveNBT());
            });
        });
    }
}
