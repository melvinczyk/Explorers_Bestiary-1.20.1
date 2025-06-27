package net.melvinczyk.bestiarymod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BestiaryProvider implements ICapabilitySerializable<CompoundTag> {
    private final BestiaryData data = new BestiaryData();
    private final LazyOptional<BestiaryData> optional = LazyOptional.of(() -> data);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == BestiaryCapability.BESTIARY ? optional.cast() : LazyOptional.empty();
    }


    @Override
    public CompoundTag serializeNBT() {
        return data.saveNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        data.loadNBT(nbt);
    }
}
