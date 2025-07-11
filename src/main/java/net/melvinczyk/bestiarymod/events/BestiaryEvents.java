package net.melvinczyk.bestiarymod.events;

import net.melvinczyk.bestiarymod.BestiaryMod;
import net.melvinczyk.bestiarymod.capability.BestiaryCapability;
import net.melvinczyk.bestiarymod.network.BestiarySyncPacket;
import net.melvinczyk.bestiarymod.network.NetworkHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BestiaryMod.MODID)
public class BestiaryEvents {

    // 🧠 Handles player death/cloning, retains bestiary progress
    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(BestiaryCapability.BESTIARY).ifPresent(oldCap -> {
            event.getEntity().getCapability(BestiaryCapability.BESTIARY).ifPresent(newCap -> {
                newCap.copyFrom(oldCap);
            });
        });
    }

    // 📡 Sends bestiary data to client on login
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
