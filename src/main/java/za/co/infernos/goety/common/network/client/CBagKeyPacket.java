package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.client.inventory.container.FocusBagContainer;
import za.co.infernos.goety.client.inventory.container.FocusPackContainer;
import za.co.infernos.goety.common.items.handler.FocusBagItemHandler;
import za.co.infernos.goety.common.items.magic.FocusPack;
import za.co.infernos.goety.utils.TotemFinder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;
import java.util.function.Supplier;

public class CBagKeyPacket {

    public static void encode(CBagKeyPacket packet, FriendlyByteBuf buffer) {
    }

    public static CBagKeyPacket decode(FriendlyByteBuf buffer) {
        return new CBagKeyPacket();
    }

    public static void consume(CBagKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                ItemStack stack = TotemFinder.findBag(playerEntity);

                if (!stack.isEmpty()){
                    SimpleMenuProvider provider = new SimpleMenuProvider(
                            (id, inventory, player) -> new FocusBagContainer(id, inventory, FocusBagItemHandler.get(stack), stack), Component.translatable(stack.getDescriptionId()));
                    if (stack.getItem() instanceof FocusPack){
                        provider = new SimpleMenuProvider(
                                (id, inventory, player) -> new FocusPackContainer(id, inventory, FocusBagItemHandler.get(stack), stack), Component.translatable(stack.getDescriptionId()));
                    }
                    playerEntity.openMenu(provider);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


