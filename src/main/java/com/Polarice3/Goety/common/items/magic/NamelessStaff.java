package com.Polarice3.Goety.common.items.magic;

import com.Polarice3.Goety.api.magic.SpellType;
import com.Polarice3.Goety.client.render.item.CustomItemsRenderer;
import com.Polarice3.Goety.config.ItemConfig;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class NamelessStaff extends DarkStaff{
    public NamelessStaff() {
        // Lazy evaluation to avoid accessing config before it's loaded
        super(getNamelessStaffDamage(), SpellType.NECROMANCY);
    }
    
    private static double getNamelessStaffDamage() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getDouble(ItemConfig.NamelessStaffDamage, 20.0D);
        } catch (IllegalStateException e) {
            // Config not loaded yet, use default
            return 4.0D;
        }
    }


}