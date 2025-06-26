package net.melvinczyk.bestiarymod.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> MOB_BLACKLIST;
    public static final ForgeConfigSpec.BooleanValue AUTO_FILL;
    public static final ForgeConfigSpec.BooleanValue DISABLE_QUESTS;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CATEGORY_OVERRIDES;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CUSTOM_QUESTS;

    static {
        MOB_BLACKLIST = BUILDER
                .comment("Blacklist specific mobs from appearing in the bestiary (e.g., 'minecraft:enderman')")
                .defineList("mob_blacklist", List.of(), o -> o instanceof String);

        AUTO_FILL = BUILDER
                .comment("Automatically fill all bestiary entries (no progression)")
                .define("auto_fill", false);

        DISABLE_QUESTS = BUILDER
                .comment("Disables kill-based quests and buffs")
                .define("disable_quests", false);

        CATEGORY_OVERRIDES = BUILDER
                .comment("Override mob categories. Format: 'minecraft:zombie=Undead'")
                .defineList("category_overrides", List.of(), o -> o instanceof String);

        CUSTOM_QUESTS = BUILDER
                .comment("Add custom quests. Format: 'minecraft:skeleton=100:+2_damage'")
                .defineList("custom_quests", List.of(), o -> o instanceof String);

        SPEC = BUILDER.build();
    }
}
