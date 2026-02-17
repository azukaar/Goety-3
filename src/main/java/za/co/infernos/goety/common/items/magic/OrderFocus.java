package za.co.infernos.goety.common.items.magic;

import za.co.infernos.goety.common.magic.spells.utility.CommandSpell;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class OrderFocus extends MagicFocus {
    public static String SERVANT_LIST = "servantList";
    public static String SERVANT_CLIENT_LIST = "servantClientList";

    public OrderFocus() {
        super(new CommandSpell());
    }

    public static List<LivingEntity> getServantsClient(Level level, ItemStack stack) {
        return new ArrayList<>();
    }
}
