package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.api.items.magic.IWand;
import za.co.infernos.goety.client.inventory.container.SoulItemContainer;
import za.co.infernos.goety.common.items.handler.SoulUsingItemHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;
import java.util.function.Supplier;

public class CWandKeyPacket {

    public static void encode(CWandKeyPacket packet, FriendlyByteBuf buffer) {
    }

    public static CWandKeyPacket decode(FriendlyByteBuf buffer) {
        return new CWandKeyPacket();
    }

    public static void consume(CWandKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                ItemStack stack = playerEntity.getMainHandItem();
                ItemStack stack2 = playerEntity.getOffhandItem();

                if (!stack.isEmpty() && stack.getItem() instanceof IWand) {
                    SimpleMenuProvider provider = new SimpleMenuProvider(
                            (id, inventory, player) -> new SoulItemContainer(id, inventory, SoulUsingItemHandler.get(stack), stack, playerEntity.getUsedItemHand()), Component.translatable(stack.getDescriptionId()));
                    playerEntity.openMenu(provider);
                } else if (!stack2.isEmpty() && stack2.getItem() instanceof IWand){
                    SimpleMenuProvider provider = new SimpleMenuProvider(
                            (id, inventory, player) -> new SoulItemContainer(id, inventory, SoulUsingItemHandler.get(stack2), stack2, playerEntity.getUsedItemHand()), Component.translatable(stack2.getDescriptionId()));
                    playerEntity.openMenu(provider);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


