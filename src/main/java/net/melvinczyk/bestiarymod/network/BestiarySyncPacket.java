package net.melvinczyk.bestiarymod.network;

import net.melvinczyk.bestiarymod.capability.BestiaryCapability;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BestiarySyncPacket {
    private final CompoundTag tag;

    public BestiarySyncPacket(CompoundTag tag) {
        this.tag = tag;
    }

    public static void encode(BestiarySyncPacket packet, FriendlyByteBuf buf) {
        buf.writeNbt(packet.tag);
    }

    public static BestiarySyncPacket decode(FriendlyByteBuf buf) {
        return new BestiarySyncPacket(buf.readNbt());
    }

    public static void handle(BestiarySyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.getCapability(BestiaryCapability.BESTIARY).ifPresent(cap -> cap.loadNBT(packet.tag));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
