package net.melvinczyk.bestiarymod.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.melvinczyk.bestiarymod.BestiaryMod;

import static net.melvinczyk.bestiarymod.BestiaryMod.MODID;

@Mod.EventBusSubscriber(modid = BestiaryMod.MODID)
public class BestiaryCapability {
    public static final Capability<IBestiary> BESTIARY = net.minecraftforge.common.capabilities.CapabilityManager.get(new CapabilityToken<>() {});
    public static final ResourceLocation ID =  ResourceLocation.fromNamespaceAndPath(MODID, "bestiary");

    @SubscribeEvent
    public static void attach(AttachCapabilitiesEvent<?> event) {
        if (event.getObject() instanceof net.minecraft.world.entity.player.Player) {
            event.addCapability(ID, new BestiaryProvider());
        }
    }

    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(BESTIARY).ifPresent(oldStore -> {
            event.getEntity().getCapability(BESTIARY).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
            });
        });
    }
}
