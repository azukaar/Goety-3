package za.co.infernos.goety.common.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FlameCaptureItem extends Item {

    public FlameCaptureItem() {
        super(new Properties().stacksTo(1));
    }

    public static boolean hasEntity(ItemStack itemStack) {
        return false;
    }
}
