package za.co.infernos.goety.common.items.armor;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;

/**
 * TODO(1.21): Armor materials are now data-driven records registered in {@code Registries.ARMOR_MATERIAL}.
 * This is a temporary compile-time shim until Goety's armor materials are migrated properly.
 * Using LEATHER as a fallback until proper materials are registered.
 */
public final class ModArmorMaterials {
    private ModArmorMaterials() {
    }

    // Lazy initialization to avoid accessing BuiltInRegistries during static initialization
    private static Holder<ArmorMaterial> fallback = null;
    
    private static Holder<ArmorMaterial> getFallback() {
        if (fallback == null) {
            try {
                fallback = BuiltInRegistries.ARMOR_MATERIAL.getHolderOrThrow(
                        ResourceKey.create(Registries.ARMOR_MATERIAL, ResourceLocation.withDefaultNamespace("leather")));
            } catch (Exception e) {
                // If registries aren't available yet, return null and handle it in usage
                return null;
            }
        }
        return fallback;
    }

    // Lazy getters that initialize on first access
    public static Holder<ArmorMaterial> getCURSED_KNIGHT() {
        Holder<ArmorMaterial> result = getFallback();
        if (CURSED_KNIGHT == null && result != null) {
            CURSED_KNIGHT = result;
        }
        return result;
    }

    public static Holder<ArmorMaterial> getCURSED_PALADIN() {
        Holder<ArmorMaterial> result = getFallback();
        if (CURSED_PALADIN == null && result != null) {
            CURSED_PALADIN = result;
        }
        return result;
    }

    public static Holder<ArmorMaterial> getBLACK_IRON() {
        Holder<ArmorMaterial> result = getFallback();
        if (BLACK_IRON == null && result != null) {
            BLACK_IRON = result;
        }
        return result;
    }

    public static Holder<ArmorMaterial> getDARK() {
        Holder<ArmorMaterial> result = getFallback();
        if (DARK == null && result != null) {
            DARK = result;
        }
        return result;
    }
    
    // Static fields for backward compatibility - initialized lazily on first access
    public static Holder<ArmorMaterial> CURSED_KNIGHT = null;
    public static Holder<ArmorMaterial> CURSED_PALADIN = null;
    public static Holder<ArmorMaterial> BLACK_IRON = null;
    public static Holder<ArmorMaterial> DARK = null;
}