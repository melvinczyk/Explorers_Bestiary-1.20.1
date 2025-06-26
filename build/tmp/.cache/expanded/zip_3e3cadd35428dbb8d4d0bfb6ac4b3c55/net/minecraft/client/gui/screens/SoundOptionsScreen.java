package net.minecraft.client.gui.screens;

import java.util.Arrays;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoundOptionsScreen extends OptionsSubScreen {
   private OptionsList list;

   private static OptionInstance<?>[] buttonOptions(Options p_250217_) {
      return new OptionInstance[]{p_250217_.showSubtitles(), p_250217_.directionalAudio()};
   }

   public SoundOptionsScreen(Screen pLastScreen, Options pOptions) {
      super(pLastScreen, pOptions, Component.translatable("options.sounds.title"));
   }

   protected void init() {
      this.list = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
      this.list.addBig(this.options.getSoundSourceOptionInstance(SoundSource.MASTER));
      this.list.addSmall(this.getAllSoundOptionsExceptMaster());
      this.list.addBig(this.options.soundDevice());
      this.list.addSmall(buttonOptions(this.options));
      this.addWidget(this.list);
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_280829_) -> {
         this.minecraft.options.save();
         this.minecraft.setScreen(this.lastScreen);
      }).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build());
   }

   private OptionInstance<?>[] getAllSoundOptionsExceptMaster() {
      return Arrays.stream(SoundSource.values()).filter((p_247780_) -> {
         return p_247780_ != SoundSource.MASTER;
      }).map((p_247779_) -> {
         return this.options.getSoundSourceOptionInstance(p_247779_);
      }).toArray((p_247778_) -> {
         return new OptionInstance[p_247778_];
      });
   }

   public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
      this.basicListRender(pGuiGraphics, this.list, pMouseX, pMouseY, pPartialTick);
   }
}