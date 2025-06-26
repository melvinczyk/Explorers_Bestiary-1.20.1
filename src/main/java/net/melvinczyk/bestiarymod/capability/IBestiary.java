package net.melvinczyk.bestiarymod.capability;

import net.minecraft.nbt.CompoundTag;

import java.util.Map;

public interface IBestiary {
    void markSeen(String mobId);
    void incrementKills(String mobId);
    void applyReward(String mobId, String reward);

    boolean hasSeen(String mobId);
    int getKills(String mobId);
    boolean hasReward(String mobId);

    void copyFrom(IBestiary other);
    CompoundTag saveNBT();
    void loadNBT(CompoundTag tag);
}
