package com.Polarice3.Goety.common.items.curios;

import com.Polarice3.Goety.compat.iron.IronAttributes;
import com.Polarice3.Goety.compat.iron.IronLoaded;
import com.Polarice3.Goety.config.MainConfig;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import com.Polarice3.Goety.Goety;
import java.util.UUID;

public class AbyssRobeItem extends SingleStackItem {

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext,
                                                                        UUID uuid, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> map = HashMultimap.create();
        map.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(Goety.location("abyss_robe_buff"), 0.5F, AttributeModifier.Operation.ADD_VALUE));
        if (IronLoaded.IRON_SPELLBOOKS.isLoaded()){
            if (com.Polarice3.Goety.utils.ConfigHelper.getBoolean(MainConfig.RobesIronResist, false)) {
                map.put(IronAttributes.LIGHTNING_MAGIC_RESIST, new AttributeModifier(Goety.location("robes_iron_spell_resist"), -0.25F, AttributeModifier.Operation.ADD_VALUE));
            }
        }
        return map;
    }
}