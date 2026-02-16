package com.Polarice3.Goety.common.magic.spells.void_spells;

import com.Polarice3.Goety.api.magic.SpellType;
import com.Polarice3.Goety.common.magic.TouchSpell;
import com.Polarice3.Goety.config.SpellConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class BanishSpell extends TouchSpell {
    @Override
    public int defaultSoulCost() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.BanishCost, 0);
    }

    @Override
    public int defaultSpellCooldown() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.BanishCoolDown, 0);
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.VOID;
    }

    @Override
    public void touchResult(ServerLevel worldIn, LivingEntity caster, LivingEntity target, ItemStack staff, com.Polarice3.Goety.common.magic.SpellStat spellStat) {
        // Temporary migration-safe implementation.
    }
}
