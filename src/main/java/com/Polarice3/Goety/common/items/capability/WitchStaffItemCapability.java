package com.Polarice3.Goety.common.items.capability;

import com.Polarice3.Goety.common.items.handler.WitchStaffItemHandler;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ICapabilitySerializable;
import net.neoforged.neoforge.common.util.LazyOptional;
import net.neoforged.neoforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WitchStaffItemCapability implements ICapabilitySerializable<Tag> {
    private final ItemStack stack;
    private final LazyOptional<IItemHandler> holder = LazyOptional.of(this::getHandler);
    private WitchStaffItemHandler handler;

    public WitchStaffItemCapability(ItemStack stack) {
        this.stack = stack;
    }

    @Nonnull
    private WitchStaffItemHandler getHandler() {
        if (handler == null) {
            handler = new WitchStaffItemHandler(stack);
        }
        return handler;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return Capabilities.ITEM_HANDLER.orEmpty(cap, holder);
    }

    public Tag serializeNBT() {
        return getHandler().serializeNBT();
    }

    public void deserializeNBT(Tag nbt) {
        this.getHandler().deserializeNBT((CompoundTag) nbt);
    }
}