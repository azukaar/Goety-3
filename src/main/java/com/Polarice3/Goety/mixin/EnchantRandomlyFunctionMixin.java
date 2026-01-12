package com.Polarice3.Goety.mixin;

import com.Polarice3.Goety.Goety;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(EnchantRandomlyFunction.class)
public class EnchantRandomlyFunctionMixin {

    @ModifyVariable(method = "run", at = @At("STORE"))
    private List<Enchantment> filterEnchants(List<Enchantment> enchantments) {
        // 1.21+ enchantments are data-driven records (final), so we filter by registry id instead of instanceof.
        enchantments.removeIf(enchantment -> {
            ResourceLocation id = BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
            return id != null && Goety.MOD_ID.equals(id.getNamespace());
        });
        return enchantments;
    }
}