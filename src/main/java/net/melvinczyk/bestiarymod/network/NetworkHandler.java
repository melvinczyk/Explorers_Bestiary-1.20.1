package net.melvinczyk.bestiarymod.network;

import net.melvinczyk.bestiarymod.BestiaryMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static net.melvinczyk.bestiarymod.BestiaryMod.MODID;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(MODID, "key")
            ,
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        INSTANCE.registerMessage(packetId++, BestiarySyncPacket.class, BestiarySyncPacket::encode, BestiarySyncPacket::decode, BestiarySyncPacket::handle);
    }

    public static void sendToClient(Object msg, Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), msg);
        }
    }
}
