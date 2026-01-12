package top.theillusivec4.curios.api;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * Minimal stub for Curios API to allow compilation when Curios is not present.
 * This is NOT a functional replacement.
 */
public record SlotContext(String identifier, LivingEntity entity, int index, ItemStack stack) {
}

