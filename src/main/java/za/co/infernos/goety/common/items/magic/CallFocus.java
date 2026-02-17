package za.co.infernos.goety.common.items.magic;

import za.co.infernos.goety.common.magic.spells.void_spells.CallSpell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CallFocus extends MagicFocus {
    public static final String TAG_ENTITY = "Summoned";

    public CallFocus() {
        super(new CallSpell());
    }

    public static void call(ServerPlayer player, ItemStack stack) {
        // Temporarily no-op during 1.21 migration.
    }

    public static boolean hasSummon(ItemStack stack) {
        return false;
    }

    public static void setSummon(CompoundTag compoundTag, LivingEntity livingEntity) {
        if (compoundTag != null && livingEntity != null) {
            compoundTag.putUUID(TAG_ENTITY, livingEntity.getUUID());
        }
    }

    public static LivingEntity getSummon(ItemStack stack) {
        return null;
    }

    public static LivingEntity getSummon(CompoundTag compoundTag) {
        return null;
    }

    public static void addCallText(ItemStack stack, List<Component> tooltip) {
        // Temporarily no-op during 1.21 migration.
    }
}


