package com.Polarice3.Goety.common.magic.spells.wild;

import com.Polarice3.Goety.api.magic.SpellType;
import com.Polarice3.Goety.common.magic.BreathingSpell;
import com.Polarice3.Goety.config.SpellConfig;

public class SwarmSpell extends BreathingSpell {
    @Override
    public int defaultSoulCost() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.SwarmCost, 0);
    }

    @Override
    public int defaultCastUp() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.SwarmChargeUp, 0);
    }

    @Override
    public int shotsNumber() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.SwarmDuration, 0);
    }

    @Override
    public int defaultSpellCooldown() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.SwarmCoolDown, 0);
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.WILD;
    }
}
