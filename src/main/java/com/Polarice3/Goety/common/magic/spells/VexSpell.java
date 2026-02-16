package com.Polarice3.Goety.common.magic.spells;

import com.Polarice3.Goety.api.magic.SpellType;
import com.Polarice3.Goety.common.magic.Spell;
import com.Polarice3.Goety.config.SpellConfig;

public class VexSpell extends Spell {
    @Override
    public int defaultSoulCost() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.VexCost, 0);
    }

    @Override
    public int defaultCastDuration() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.VexDuration, 0);
    }

    @Override
    public int defaultSpellCooldown() {
        return com.Polarice3.Goety.utils.ConfigHelper.getInt(SpellConfig.VexCoolDown, 0);
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.ILL;
    }
}
