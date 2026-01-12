package net.minecraft.world.item.alchemy;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Compatibility shim for older code targeting MC &lt;= 1.20 that relied on {@code PotionUtils}.
 * <p>
 * In 1.21+, potion data is stored in {@link PotionContents} via {@link DataComponents#POTION_CONTENTS}.
 */
public final class PotionUtils {
    private PotionUtils() {}

    private static PotionContents getContents(ItemStack stack) {
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        return contents != null ? contents : PotionContents.EMPTY;
    }

    public static int getColor(ItemStack stack) {
        PotionContents contents = getContents(stack);
        if (contents != PotionContents.EMPTY) {
            return contents.getColor();
        }
        // Reasonable fallback for "no contents" (vanilla potion contents color logic would typically be absent anyway)
        return 0;
    }

    public static int getColor(Iterable<MobEffectInstance> effects) {
        return PotionContents.getColor(effects);
    }

    public static Holder<Potion> getPotion(ItemStack stack) {
        PotionContents contents = getContents(stack);
        return contents.potion().orElse(Potions.WATER);
    }

    public static ItemStack setPotion(ItemStack stack, Holder<Potion> potion) {
        PotionContents contents = getContents(stack).withPotion(potion);
        stack.set(DataComponents.POTION_CONTENTS, contents);
        return stack;
    }

    public static List<MobEffectInstance> getMobEffects(ItemStack stack) {
        PotionContents contents = getContents(stack);
        List<MobEffectInstance> out = new ArrayList<>();
        for (MobEffectInstance e : contents.getAllEffects()) {
            out.add(e);
        }
        return out;
    }

    public static List<MobEffectInstance> getCustomEffects(ItemStack stack) {
        return getContents(stack).customEffects();
    }

    public static void setCustomEffects(ItemStack stack, Collection<MobEffectInstance> effects) {
        PotionContents contents = getContents(stack);
        Optional<Holder<Potion>> potion = contents.potion();
        Optional<Integer> color = contents.customColor();
        PotionContents updated = new PotionContents(potion, color, List.copyOf(effects));
        stack.set(DataComponents.POTION_CONTENTS, updated);
    }

    public static ItemStack addPotionToItemStack(ItemStack stack, Holder<Potion> potion) {
        return setPotion(stack, potion);
    }

    public static ItemStack createPotionStack(Item item, Holder<Potion> potion) {
        return PotionContents.createItemStack(item, potion);
    }
}

