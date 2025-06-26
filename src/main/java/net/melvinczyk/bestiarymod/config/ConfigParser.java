package net.melvinczyk.bestiarymod.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigParser {

    public static Map<String, String> getCategoryOverrides() {
        Map<String, String> result = new HashMap<>();
        for (String entry : CommonConfig.CATEGORY_OVERRIDES.get()) {
            String[] parts = entry.split("=");
            if (parts.length == 2) {
                result.put(parts[0], parts[1]);
            }
        }
        return result;
    }

    public static Map<String, CustomQuest> getCustomQuests() {
        Map<String, CustomQuest> quests = new HashMap<>();
        for (String line : CommonConfig.CUSTOM_QUESTS.get()) {
            String[] parts = line.split("=");
            if (parts.length == 2) {
                String[] questParts = parts[1].split(":");
                if (questParts.length == 2) {
                    try {
                        int amount = Integer.parseInt(questParts[0]);
                        String reward = questParts[1];
                        quests.put(parts[0], new CustomQuest(amount, reward));
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        return quests;
    }

    public record CustomQuest(int amount, String reward) {}
}
