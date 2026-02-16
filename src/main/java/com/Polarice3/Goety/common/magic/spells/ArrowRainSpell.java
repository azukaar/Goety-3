package com.Polarice3.Goety.common.magic.spells;

import com.Polarice3.Goety.common.magic.EverChargeSpell;
import com.Polarice3.Goety.config.SpellConfig;

public class ArrowRainSpell extends EverChargeSpell {
    @Override
    public int defaultSoulCost() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.ArrowRainCost, 0);
    }

    @Override
    public int defaultCastUp() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.ArrowRainChargeUp, 0);
    }

    @Override
    public int shotsNumber() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.ArrowRainDuration, 0);
    }

    @Override
    public int defaultSpellCooldown() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.ArrowRainCoolDown, 0);
    }
}
