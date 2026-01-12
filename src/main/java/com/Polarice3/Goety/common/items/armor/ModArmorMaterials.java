package com.Polarice3.Goety.common.items.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;

/**
 * TODO(1.21): Armor materials are now data-driven records registered in {@code Registries.ARMOR_MATERIAL}.
 * This is a temporary compile-time shim until Goety's armor materials are migrated properly.
 */
public final class ModArmorMaterials {
    private ModArmorMaterials() {
    }

    public static final Holder<ArmorMaterial> CURSED_KNIGHT = null;
    public static final Holder<ArmorMaterial> CURSED_PALADIN = null;
    public static final Holder<ArmorMaterial> BLACK_IRON = null;
    public static final Holder<ArmorMaterial> DARK = null;
}