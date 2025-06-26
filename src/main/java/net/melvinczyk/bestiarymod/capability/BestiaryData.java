package net.melvinczyk.bestiarymod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BestiaryData implements IBestiary {
    private final Set<String> seen = new HashSet<>();
    private final Map<String, Integer> kills = new HashMap<>();
    private final Set<String> rewarded = new HashSet<>();

    @Override
    public void markSeen(String mobId) {
        seen.add(mobId);
    }

    @Override
    public void incrementKills(String mobId) {
        kills.put(mobId, getKills(mobId) + 1);
    }

    @Override
    public void applyReward(String mobId, String reward) {
        rewarded.add(mobId + ":" + reward);
    }

    @Override
    public boolean hasSeen(String mobId) {
        return seen.contains(mobId);
    }

    @Override
    public int getKills(String mobId) {
        return kills.getOrDefault(mobId, 0);
    }

    @Override
    public boolean hasReward(String mobId) {
        return rewarded.stream().anyMatch(s -> s.startsWith(mobId + ":"));
    }

    @Override
    public void copyFrom(IBestiary other) {
        this.seen.clear();
        this.seen.addAll(((BestiaryData) other).seen);
        this.kills.clear();
        this.kills.putAll(((BestiaryData) other).kills);
        this.rewarded.clear();
        this.rewarded.addAll(((BestiaryData) other).rewarded);
    }

    @Override
    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();

        ListTag seenTag = new ListTag();
        for (String s : seen) seenTag.add(StringTag.valueOf(s));
        tag.put("Seen", seenTag);

        CompoundTag killsTag = new CompoundTag();
        for (Map.Entry<String, Integer> entry : kills.entrySet()) {
            killsTag.putInt(entry.getKey(), entry.getValue());
        }
        tag.put("Kills", killsTag);

        ListTag rewardsTag = new ListTag();
        for (String s : rewarded) rewardsTag.add(StringTag.valueOf(s));
        tag.put("Rewards", rewardsTag);

        return tag;
    }

    @Override
    public void loadNBT(CompoundTag tag) {
        seen.clear();
        for (Tag t : tag.getList("Seen", Tag.TAG_STRING)) {
            seen.add(t.getAsString());
        }

        kills.clear();
        CompoundTag killsTag = tag.getCompound("Kills");
        for (String key : killsTag.getAllKeys()) {
            kills.put(key, killsTag.getInt(key));
        }

        rewarded.clear();
        for (Tag t : tag.getList("Rewards", Tag.TAG_STRING)) {
            rewarded.add(t.getAsString());
        }
    }
}
