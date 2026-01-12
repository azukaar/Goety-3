package top.theillusivec4.curios.api.type.capability;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

/**
 * Minimal stub for Curios API to allow compilation when Curios is not present.
 * This is NOT a functional replacement.
 */
public interface ICurioItem {
    default boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    default boolean makesPiglinsNeutral(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    default Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        return HashMultimap.create();
    }
}

