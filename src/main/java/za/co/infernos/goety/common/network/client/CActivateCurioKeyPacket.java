package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.api.items.curios.IActivatable;
import za.co.infernos.goety.utils.CuriosFinder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CActivateCurioKeyPacket {
    public static void encode(CActivateCurioKeyPacket packet, FriendlyByteBuf buffer) {
    }

    public static CActivateCurioKeyPacket decode(FriendlyByteBuf buffer) {
        return new CActivateCurioKeyPacket();
    }

    public static void consume(CActivateCurioKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                ItemStack stack = CuriosFinder.findCurio(playerEntity, itemStack -> itemStack.getItem() instanceof IActivatable);

                if (!stack.isEmpty() && stack.getItem() instanceof IActivatable activatable){
                    activatable.activate(playerEntity.level(), playerEntity, stack);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


