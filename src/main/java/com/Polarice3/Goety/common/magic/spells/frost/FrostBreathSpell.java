package com.Polarice3.Goety.common.magic.spells.frost;

import com.Polarice3.Goety.api.magic.SpellType;
import com.Polarice3.Goety.common.magic.BreathingSpell;
import com.Polarice3.Goety.config.SpellConfig;

public class FrostBreathSpell extends BreathingSpell {
    @Override
    public int defaultSoulCost() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.FrostBreathCost, 0);
    }

    @Override
    public int defaultCastUp() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.FrostBreathChargeUp, 0);
    }

    @Override
    public int shotsNumber() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.FrostBreathDuration, 0);
    }

    @Override
    public int defaultSpellCooldown() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.FrostBreathCoolDown, 0);
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.FROST;
    }
}
