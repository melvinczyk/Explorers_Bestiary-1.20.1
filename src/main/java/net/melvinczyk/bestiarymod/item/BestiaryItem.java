package net.melvinczyk.bestiarymod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.melvinczyk.bestiarymod.BestiaryMod;

public class BestiaryItem extends Item {
    public BestiaryItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            BestiaryMod.LOGGER.info("Bestiary opened by player: " + player.getName().getString());
            // TODO: Open GUI later
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}
