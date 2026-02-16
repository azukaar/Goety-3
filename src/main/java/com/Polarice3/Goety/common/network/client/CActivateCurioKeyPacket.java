package com.Polarice3.Goety.common.network.client;

import com.Polarice3.Goety.api.items.curios.IActivatable;
import com.Polarice3.Goety.utils.CuriosFinder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import com.Polarice3.Goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CActivateCurioKeyPacket {
    public static void encode(CActivateCurioKeyPacket packet, FriendlyByteBuf buffer) {
    }

    public static CActivateCurioKeyPacket decode(FriendlyByteBuf buffer) {
        return new CActivateCurioKeyPacket();
    }

    public static void consume(CActivateCurioKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = com.Polarice3.Goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

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


