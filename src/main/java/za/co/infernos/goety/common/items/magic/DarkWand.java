package za.co.infernos.goety.common.items.magic;

import za.co.infernos.goety.api.items.magic.IWand;
import za.co.infernos.goety.api.magic.SpellType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class DarkWand extends Item implements IWand {
    private final SpellType spellType;

    public DarkWand(Properties properties, SpellType spellType) {
        super(properties);
        this.spellType = spellType;
    }

    public DarkWand(SpellType spellType) {
        this(new Item.Properties().stacksTo(1), spellType);
    }

    public DarkWand() {
        this(SpellType.NONE);
    }

    public static Item.Properties wandProperties() {
        return new Item.Properties().stacksTo(1);
    }

    @Override
    public SpellType getSpellType() {
        return this.spellType;
    }

    public static class DarkWandClient implements IClientItemExtensions {
    }
}
