package com.Polarice3.Goety.common.items;

import com.Polarice3.Goety.config.ItemConfig;
import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    SPECIAL(() -> getSpecialMiningLevel(),
            () -> getSpecialDurability(),
            () -> getSpecialBreakSpeed(),
            () -> getSpecialDamage(),
            () -> getSpecialEnchantability(), () -> {
        return Ingredient.of(ModItems.CURSED_METAL_INGOT.get());
    }),
    DARK(() -> getDarkMiningLevel(),
            () -> getDarkDurability(),
            () -> getDarkBreakSpeed(),
            () -> getDarkDamage(),
            () -> getDarkEnchantability(), () -> {
        return Ingredient.of(ModItems.DARK_ALLOY_INGOT.get());
    }),
    VOID(4,
            2031,
            0.0F,
            0.0F,
            15, () -> {
        return Ingredient.of(Items.ENDER_PEARL);
    }),
    DEATH(() -> 4,
            () -> getDeathScytheDurability(),
            () -> 12.0F,
            () -> getDeathScytheDamage(),
            () -> getDeathScytheEnchantability(), () -> {
        return Ingredient.of(Items.BONE);
    });

    private final Supplier<Integer> levelSupplier;
    private final Supplier<Integer> usesSupplier;
    private final Supplier<Float> speedSupplier;
    private final Supplier<Float> damageSupplier;
    private final Supplier<Integer> enchantmentValueSupplier;
    private final Supplier<Ingredient> repairIngredient;
    
    // Cached values (lazy evaluation)
    private Integer levelCache;
    private Integer usesCache;
    private Float speedCache;
    private Float damageCache;
    private Integer enchantmentValueCache;

    // Constructor for lazy config values
    ModTiers(Supplier<Integer> pLevel, Supplier<Integer> pUses, Supplier<Float> pSpeed, Supplier<Float> pDamage, Supplier<Integer> pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.levelSupplier = pLevel;
        this.usesSupplier = pUses;
        this.speedSupplier = pSpeed;
        this.damageSupplier = pDamage;
        this.enchantmentValueSupplier = pEnchantmentValue;
        this.repairIngredient = Suppliers.memoize(pRepairIngredient::get);
    }
    
    // Constructor for constant values (VOID tier)
    ModTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.levelSupplier = () -> pLevel;
        this.usesSupplier = () -> pUses;
        this.speedSupplier = () -> pSpeed;
        this.damageSupplier = () -> pDamage;
        this.enchantmentValueSupplier = () -> pEnchantmentValue;
        this.repairIngredient = Suppliers.memoize(pRepairIngredient::get);
        // Pre-cache constant values
        this.levelCache = pLevel;
        this.usesCache = pUses;
        this.speedCache = pSpeed;
        this.damageCache = pDamage;
        this.enchantmentValueCache = pEnchantmentValue;
    }
    
    // Lazy getters for config values
    private static int getSpecialMiningLevel() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.SpecialToolsMiningLevel, 0);
        } catch (IllegalStateException e) {
            return 2; // Default value
        }
    }
    
    private static int getSpecialDurability() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.SpecialToolsDurability, 0);
        } catch (IllegalStateException e) {
            return 500; // Default value
        }
    }
    
    private static float getSpecialBreakSpeed() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getFloat(ItemConfig.SpecialToolsBreakSpeed, 1.0F);
        } catch (IllegalStateException e) {
            return 6.0F; // Default value
        }
    }
    
    private static float getSpecialDamage() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getFloat(ItemConfig.SpecialToolsDamage, 1.0F);
        } catch (IllegalStateException e) {
            return 2.0F; // Default value
        }
    }
    
    private static int getSpecialEnchantability() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.SpecialToolsEnchantability, 0);
        } catch (IllegalStateException e) {
            return 14; // Default value
        }
    }
    
    private static int getDarkMiningLevel() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.DarkToolsMiningLevel, 0);
        } catch (IllegalStateException e) {
            return 3; // Default value
        }
    }
    
    private static int getDarkDurability() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.DarkToolsDurability, 0);
        } catch (IllegalStateException e) {
            return 1000; // Default value
        }
    }
    
    private static float getDarkBreakSpeed() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getFloat(ItemConfig.DarkToolsBreakSpeed, 1.0F);
        } catch (IllegalStateException e) {
            return 8.0F; // Default value
        }
    }
    
    private static float getDarkDamage() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getFloat(ItemConfig.DarkToolsDamage, 1.0F);
        } catch (IllegalStateException e) {
            return 3.0F; // Default value
        }
    }
    
    private static int getDarkEnchantability() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.DarkToolsEnchantability, 0);
        } catch (IllegalStateException e) {
            return 15; // Default value
        }
    }
    
    private static int getDeathScytheDurability() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.DeathScytheDurability, 0);
        } catch (IllegalStateException e) {
            return 2031; // Default value
        }
    }
    
    private static float getDeathScytheDamage() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getFloat(ItemConfig.DeathScytheDamage, 1.0F);
        } catch (IllegalStateException e) {
            return 4.0F; // Default value
        }
    }
    
    private static int getDeathScytheEnchantability() {
        try {
            return com.Polarice3.Goety.utils.ConfigHelper.getInt(ItemConfig.DeathScytheEnchantability, 0);
        } catch (IllegalStateException e) {
            return 15; // Default value
        }
    }

    public int getUses() {
        if (usesCache == null) {
            usesCache = usesSupplier.get();
        }
        return usesCache;
    }

    public float getSpeed() {
        if (speedCache == null) {
            speedCache = speedSupplier.get();
        }
        return speedCache;
    }

    public float getAttackDamageBonus() {
        if (damageCache == null) {
            damageCache = damageSupplier.get();
        }
        return damageCache;
    }

    public int getLevel() {
        if (levelCache == null) {
            levelCache = levelSupplier.get();
        }
        return levelCache;
    }

    public int getEnchantmentValue() {
        if (enchantmentValueCache == null) {
            enchantmentValueCache = enchantmentValueSupplier.get();
        }
        return enchantmentValueCache;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return BlockTags.INCORRECT_FOR_IRON_TOOL;
    }

}
