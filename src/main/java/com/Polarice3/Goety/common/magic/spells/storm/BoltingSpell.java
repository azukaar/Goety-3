package com.Polarice3.Goety.common.magic.spells.storm;

import com.Polarice3.Goety.api.magic.SpellType;
import com.Polarice3.Goety.common.magic.Spell;
import com.Polarice3.Goety.common.magic.SpellStat;
import com.Polarice3.Goety.config.SpellConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class BoltingSpell extends Spell {
    @Override
    public int defaultSoulCost() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.BoltingCost, 0);
    }

    @Override
    public int defaultCastDuration() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.BoltingDuration, 0);
    }

    @Override
    public int defaultSpellCooldown() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.BoltingCoolDown, 0);
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.STORM;
    }

    @Override
    public void SpellResult(ServerLevel worldIn, LivingEntity caster, ItemStack staff, SpellStat spellStat) {
        // Temporary migration-safe implementation.
    }
}
