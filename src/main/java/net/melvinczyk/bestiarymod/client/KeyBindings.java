package net.melvinczyk.bestiarymod.client;

import net.melvinczyk.bestiarymod.client.screen.BestiaryScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.melvinczyk.bestiarymod.BestiaryMod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = BestiaryMod.MODID, value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class KeyBindings {
    public static final KeyMapping OPEN_BESTIARY = new KeyMapping(
            "key.bestiarymod.open_bestiary",
            GLFW.GLFW_KEY_B,
            "key.categories.bestiarymod"
    );

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_BESTIARY);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (OPEN_BESTIARY.consumeClick()) {
            Minecraft.getInstance().setScreen(new BestiaryScreen());
        }
    }
}
