package za.co.infernos.goety.common.network.client.brew;

import za.co.infernos.goety.client.inventory.container.BrewBagContainer;
import za.co.infernos.goety.common.items.handler.BrewBagItemHandler;
import za.co.infernos.goety.utils.CuriosFinder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;
import java.util.function.Supplier;

public class CBrewBagKeyPacket {

    public static void encode(CBrewBagKeyPacket packet, FriendlyByteBuf buffer) {
    }

    public static CBrewBagKeyPacket decode(FriendlyByteBuf buffer) {
        return new CBrewBagKeyPacket();
    }

    public static void consume(CBrewBagKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                ItemStack stack = CuriosFinder.findBrewBag(playerEntity);

                if (!stack.isEmpty()){
                    SimpleMenuProvider provider = new SimpleMenuProvider(
                            (id, inventory, player) -> new BrewBagContainer(id, inventory, BrewBagItemHandler.get(stack), stack), Component.translatable(stack.getDescriptionId()));
                    playerEntity.openMenu(provider);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


