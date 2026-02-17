package com.Polarice3.Goety.common.items.armor;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.api.items.ISoulRepair;
import com.Polarice3.Goety.api.items.armor.ISoulDiscount;
import com.Polarice3.Goety.client.render.ModModelLayer;
import com.Polarice3.Goety.client.render.model.DarkArmorModel;
import com.Polarice3.Goety.common.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class DarkArmor extends ArmorItem implements ISoulRepair, ISoulDiscount {
    public DarkArmor(ArmorItem.Type p_40387_) {
        super(ModArmorMaterials.getDARK(), p_40387_, ModItems.baseProperties());
    }



    public int getSoulDiscount(EquipmentSlot equipmentSlot, ItemStack itemStack){
        return 5;
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
           public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
               EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();
               ModelPart root = modelSet.bakeLayer(equipmentSlot == EquipmentSlot.LEGS ? ModModelLayer.DARK_ARMOR_INNER : ModModelLayer.DARK_ARMOR_OUTER);
               DarkArmorModel model = new DarkArmorModel(root).animate(livingEntity);
               model.hat.visible = equipmentSlot == EquipmentSlot.HEAD;
               model.body.visible = equipmentSlot == EquipmentSlot.CHEST;
               model.rightArm.visible = equipmentSlot == EquipmentSlot.CHEST;
               model.leftArm.visible = equipmentSlot == EquipmentSlot.CHEST;
               model.bottom.visible = equipmentSlot == EquipmentSlot.LEGS;
               model.rightLeg.visible = equipmentSlot == EquipmentSlot.FEET;
               model.leftLeg.visible = equipmentSlot == EquipmentSlot.FEET;

               if (livingEntity instanceof AbstractClientPlayer player){
                   // FIXME: Cape logic needs update for 1.21
                   /*
                   if (player.isCapeLoaded() && player.isModelPartShown(PlayerModelPart.CAPE) && player.getCloakTextureLocation() != null){
                       model.cape.visible = false;
                   }
                   */
               }

               model.young = original.young;
               model.crouching = original.crouching;
               model.riding = original.riding;
               model.rightArmPose = original.rightArmPose;
               model.leftArmPose = original.leftArmPose;

               return model;
           }

            public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
               if (slot == EquipmentSlot.LEGS) {
                   return Goety.location("textures/models/armor/dark_armor_layer.png").toString();
               } else {
                   return Goety.location("textures/models/armor/dark_armor.png").toString();
               }
           }
           // End of anonymous class
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, context, tooltip, flagIn);
        int discount = this.getSoulDiscount(stack.getEquipmentSlot(), stack);
        if (discount > 0) {
            tooltip.add(this.soulDiscountTooltip(stack));
        }
    }
}
