package net.melvinczyk.bestiarymod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BestiaryProvider implements ICapabilityProvider {
    private final BestiaryData data = new BestiaryData();
    private final LazyOptional<IBestiary> optional = LazyOptional.of(() -> data);

    @SuppressWarnings("ConstantConditions")
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == BestiaryCapability.BESTIARY ? optional.cast() : LazyOptional.empty();
    }

    public void copyFrom(BestiaryProvider other) {
        this.data.copyFrom(other.data);
    }

    public CompoundTag serializeNBT() {
        return data.saveNBT();
    }

    public void deserializeNBT(CompoundTag tag) {
        data.loadNBT(tag);
    }
}
