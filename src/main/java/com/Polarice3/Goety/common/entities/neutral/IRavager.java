package com.Polarice3.Goety.common.entities.neutral;

import com.Polarice3.Goety.config.MobsConfig;
import com.Polarice3.Goety.utils.MathHelper;
import net.minecraft.world.item.ItemStack;

public interface IRavager {
    ItemStack getArmor();

    default int getRoarCool(){
        return 0;
    }

    default int getRoarCoolMax(){
        return MathHelper.secondsToTicks(com.Polarice3.Goety.utils.ConfigHelper.getInt(MobsConfig.RavagerRoarCooldown, 0));
    }

    default void forceRoar(){
    }
}