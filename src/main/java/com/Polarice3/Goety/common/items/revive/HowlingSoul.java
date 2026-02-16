package com.Polarice3.Goety.common.items.revive;

import net.minecraft.world.item.Rarity;

public class HowlingSoul extends ReviveServantItem {
    public HowlingSoul() {
        super(new Properties().rarity(Rarity.UNCOMMON).setNoRepair().stacksTo(1));
    }
}
