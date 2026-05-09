package za.co.infernos.goety.common.items.magic;

import za.co.infernos.goety.api.items.magic.ITotem;
import za.co.infernos.goety.common.items.ModItems;
import za.co.infernos.goety.config.ItemConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class TotemOfSouls extends Item implements ITotem, ICurioItem {
    public int maxSouls;

    public TotemOfSouls(int maxSouls) {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
        this.maxSouls = maxSouls;
    }

    public int getMaxSouls() {
        return this.maxSouls;
    }

    public static boolean isActivated(ItemStack itemStack) {
        return !itemStack.getOrDefault(net.minecraft.core.component.DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.EMPTY).isEmpty();
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (ITotem.currentSouls(container) > za.co.infernos.goety.utils.ConfigHelper.getInt(ItemConfig.CraftingSouls, 0)) {
            ITotem.decreaseSouls(container, za.co.infernos.goety.utils.ConfigHelper.getInt(ItemConfig.CraftingSouls, 0));
            return container;
        }
        return new ItemStack(ModItems.SPENT_TOTEM.get());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("info.goety.totem_of_souls.souls", ITotem.currentSouls(stack), ITotem.maximumSouls(stack)));
    }
}
