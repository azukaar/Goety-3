package com.Polarice3.Goety.common.items.handler;

import com.Polarice3.Goety.api.items.magic.IFocus;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.minecraft.world.item.component.CustomData;

import javax.annotation.Nonnull;

public class SoulUsingItemHandler extends ItemStackHandler {
    private final ItemStack itemStack;
    private int slot;

    public SoulUsingItemHandler(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack extractItem() {
        return extractItem(slot, 1, false);
    }

    public ItemStack insertItem(ItemStack insert) {
        return insertItem(slot, insert, false);
    }

    public ItemStack getSlot() {
        return getStackInSlot(slot);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack)
    {
        return stack.getItem() instanceof IFocus;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    public NonNullList<ItemStack> getContents(){
        return stacks;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = super.serializeNBT(provider);
        nbt.putInt("slot", slot);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        super.deserializeNBT(provider, nbt);
        if (nbt.contains("slot")) {
            slot = nbt.getInt("slot");
        }
        onLoad();
    }

    @Override
    protected void onContentsChanged(int slot) {
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, nbt -> nbt.putBoolean("goety-dirty", !nbt.getBoolean("goety-dirty")));
    }

    public static SoulUsingItemHandler get(ItemStack stack) {
        IItemHandler handler = stack.getCapability(Capabilities.ItemHandler.ITEM);
        if (!(handler instanceof SoulUsingItemHandler soulHandler)) {
            throw new IllegalArgumentException("ItemStack is missing SoulUsingItemHandler item capability");
        }
        return soulHandler;
    }
}