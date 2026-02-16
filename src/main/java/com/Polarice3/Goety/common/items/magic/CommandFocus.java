package com.Polarice3.Goety.common.items.magic;

import com.Polarice3.Goety.common.magic.spells.utility.CommandSpell;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;

public class CommandFocus extends MagicFocus {
    public static final String TAG_ENTITY = "Servant";
    public static final String TAG_ENTITY_CLIENT = "ServantClient";

    public CommandFocus() {
        super(new CommandSpell());
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return hasServant(p_41453_);
    }

    public static boolean hasServant(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return tag.contains(TAG_ENTITY) || tag.contains(TAG_ENTITY_CLIENT);
    }

    public static void setServant(CompoundTag compoundTag, LivingEntity livingEntity) {
        if (compoundTag != null && livingEntity != null) {
            compoundTag.putUUID(TAG_ENTITY, livingEntity.getUUID());
            compoundTag.putInt(TAG_ENTITY_CLIENT, livingEntity.getId());
        }
    }

    public static LivingEntity getServant(ItemStack stack) {
        // Server lookup by UUID is not available from ItemStack alone in 1.21 APIs.
        return null;
    }

    public static LivingEntity getServant(CompoundTag compoundTag) {
        // Server lookup by UUID requires world/server context.
        return null;
    }

    public static LivingEntity getServantClient(Level level, ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return getServantClient(level, tag);
    }

    public static LivingEntity getServantClient(Level level, CompoundTag compoundTag) {
        if (level == null || compoundTag == null || !compoundTag.contains(TAG_ENTITY_CLIENT)) {
            return null;
        }
        return level.getEntity(compoundTag.getInt(TAG_ENTITY_CLIENT)) instanceof LivingEntity livingEntity ? livingEntity : null;
    }

    public static void addCommandText(Level level, ItemStack stack, List<Component> tooltip) {
        LivingEntity servant = getServantClient(level, stack);
        if (servant != null) {
            tooltip.add(Component.translatable("info.goety.focus.target").append(": ").append(servant.getDisplayName()));
        }
    }
}

