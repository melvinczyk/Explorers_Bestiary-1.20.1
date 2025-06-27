package net.melvinczyk.bestiarymod.capability;

import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BestiaryData {
    private final Set<String> seenMobs = new HashSet<>();
    private final Map<String, Integer> killCounts = new HashMap<>();

    public void markSeen(String id) {
        seenMobs.add(id);
    }

    public void addKill(String id) {
        killCounts.put(id, getKillCount(id) + 1);
    }

    public boolean hasSeen(String id) {
        return seenMobs.contains(id);
    }

    public int getKillCount(String id) {
        return killCounts.getOrDefault(id, 0);
    }

    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();

        CompoundTag seenTag = new CompoundTag();
        seenMobs.forEach(id -> seenTag.putBoolean(id, true));
        tag.put("Seen", seenTag);

        CompoundTag killsTag = new CompoundTag();
        killCounts.forEach(killsTag::putInt);
        tag.put("Kills", killsTag);

        return tag;
    }

    public void loadNBT(CompoundTag tag) {
        seenMobs.clear();
        killCounts.clear();

        CompoundTag seenTag = tag.getCompound("Seen");
        for (String id : seenTag.getAllKeys()) {
            seenMobs.add(id);
        }

        CompoundTag killsTag = tag.getCompound("Kills");
        for (String id : killsTag.getAllKeys()) {
            killCounts.put(id, killsTag.getInt(id));
        }
    }

    public void copyFrom(BestiaryData oldData) {
        // Assuming BestiaryData uses a CompoundTag internally to store data, or has fields to copy

        // If you have a tag-based save/load:
        this.loadNBT(oldData.saveNBT());

        // Or, if you have fields, copy them explicitly like:
        // this.someField = oldData.someField;
        // this.anotherField = oldData.anotherField;
    }

}
