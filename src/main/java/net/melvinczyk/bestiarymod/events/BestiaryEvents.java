package net.melvinczyk.bestiarymod.events;

import net.melvinczyk.bestiarymod.BestiaryMod;
import net.melvinczyk.bestiarymod.capability.BestiaryCapability;
import net.melvinczyk.bestiarymod.capability.BestiaryProvider;
import net.melvinczyk.bestiarymod.capability.IBestiary;
import net.melvinczyk.bestiarymod.network.BestiarySyncPacket;
import net.melvinczyk.bestiarymod.network.NetworkHandler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.melvinczyk.bestiarymod.BestiaryMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class BestiaryEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Object> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ResourceLocation.fromNamespaceAndPath(MODID, "key")
                    , new BestiaryProvider());
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(BestiaryCapability.BESTIARY).ifPresent(oldCap -> {
            event.getEntity().getCapability(BestiaryCapability.BESTIARY).ifPresent(newCap -> {
                newCap.copyFrom(oldCap);
            });
        });
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            serverPlayer.getCapability(BestiaryCapability.BESTIARY).ifPresent(bestiary -> {
                CompoundTag tag = bestiary.saveNBT();
                NetworkHandler.sendToClient(new BestiarySyncPacket(tag), serverPlayer);
            });
        }
    }
}
