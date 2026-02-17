package com.Polarice3.Goety.common.items.armor;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.api.items.IPersist;
import com.Polarice3.Goety.api.items.armor.ISoulDiscount;
import com.Polarice3.Goety.client.render.ModModelLayer;
import com.Polarice3.Goety.client.render.model.BlackIronArmorModel;
import com.Polarice3.Goety.common.items.ModItems;
import com.Polarice3.Goety.config.ItemConfig;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class BlackIronArmor extends ArmorItem implements ISoulDiscount, IPersist {
    public BlackIronArmor(ArmorItem.Type p_40387_) {
        super(ModArmorMaterials.getBLACK_IRON(), p_40387_, ModItems.baseProperties());
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        if (slot == EquipmentSlot.LEGS) {
            return Goety.location("textures/models/armor/black_iron_armor_layer.png").toString();
        } else {
            return Goety.location("textures/models/armor/black_iron_armor.png").toString();
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return this.isDamaged(stack);
    }

    public int getBarColor(ItemStack stack) {
        if (this.isBroken(stack)) {
            return 0x800000;
        }
        return super.getBarColor(stack);
    }

    @Override
    public int getBarWidth(ItemStack stack){
        if (this.isBroken(stack)) {
            return 13;
        }
        return super.getBarWidth(stack);
    }

    public int getSoulDiscount(EquipmentSlot equipmentSlot, ItemStack stack){
        if (this.isNotBroken(stack)) {
            if (equipmentSlot == EquipmentSlot.CHEST) {
                return 2;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean isBroken(ItemStack stack) {
        return IPersist.super.isBroken(stack) && com.Polarice3.Goety.utils.ConfigHelper.getBoolean(ItemConfig.BlackIronPersist, false);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<Item> onBroken) {
        if (com.Polarice3.Goety.utils.ConfigHelper.getBoolean(ItemConfig.BlackIronPersist, false)) {
            if (stack.getDamageValue() + amount >= stack.getMaxDamage()) {
                if (stack.getDamageValue() != stack.getMaxDamage() - 1) {
                    stack.setDamageValue(stack.getMaxDamage() - 1);
                    onBroken.accept(stack.getItem());
                }
                return 0;
            }
        }
        return super.damageItem(stack, amount, entity, onBroken);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
           public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
               EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();
               ModelPart root = modelSet.bakeLayer(equipmentSlot == EquipmentSlot.LEGS ? ModModelLayer.BLACK_IRON_ARMOR_INNER : ModModelLayer.BLACK_IRON_ARMOR_OUTER);
               BlackIronArmorModel model = new BlackIronArmorModel(root);
               model.hat.visible = equipmentSlot == EquipmentSlot.HEAD;
               model.body.visible = equipmentSlot == EquipmentSlot.CHEST;
               model.rightArm.visible = equipmentSlot == EquipmentSlot.CHEST;
               model.leftArm.visible = equipmentSlot == EquipmentSlot.CHEST;
               model.rightLeg.visible = equipmentSlot == EquipmentSlot.FEET;
               model.leftLeg.visible = equipmentSlot == EquipmentSlot.FEET;

               model.young = original.young;
               model.crouching = original.crouching;
               model.riding = original.riding;
               model.rightArmPose = original.rightArmPose;
               model.leftArmPose = original.leftArmPose;

               return model;
           }
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, context, tooltip, flagIn);
        int discount = this.getSoulDiscount(stack.getEquipmentSlot(), stack);
        if (discount > 0) {
            tooltip.add(this.soulDiscountTooltip(stack));
        }
        if (com.Polarice3.Goety.utils.ConfigHelper.getBoolean(ItemConfig.BlackIronPersist, false) && this.isBroken(stack)) {
            tooltip.add(Component.translatable("info.goety.armor.broken").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
