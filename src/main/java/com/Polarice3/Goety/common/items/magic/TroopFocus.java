package com.Polarice3.Goety.common.items.magic;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public class TroopFocus extends Item {
    private static final String TAG_SUMMON_TYPE = "TroopSummonType";

    public TroopFocus() {
        super(new Item.Properties().stacksTo(1));
    }

    public static boolean hasSummonType(ItemStack stack) {
        return stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().contains(TAG_SUMMON_TYPE);
    }

    public static void call(Player player, ItemStack stack) {
        // Placeholder migration implementation to keep call sites valid.
    }
}
