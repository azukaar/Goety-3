package com.Polarice3.Goety.common.items.magic;

import com.Polarice3.Goety.api.items.magic.ITotem;
import com.Polarice3.Goety.config.ItemConfig;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class FullSpentTotem extends TotemOfSouls{

    public FullSpentTotem(int maxSouls) {
        super(maxSouls);
    }

    @Nonnull
    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (ITotem.currentSouls(container) > com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.CraftingSouls, 0)) {
            ITotem.decreaseSouls(container, com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.CraftingSouls, 0));
            return container;
        }
        return ItemStack.EMPTY;
    }
}