package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.utils.LichdomHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CSetLichMode {

    public static void encode(CSetLichMode packet, FriendlyByteBuf buffer) {
    }

    public static CSetLichMode decode(FriendlyByteBuf buffer) {
        return new CSetLichMode();
    }

    public static void consume(CSetLichMode packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);
            if (playerEntity != null && LichdomHelper.isLich(playerEntity)) {
                LichdomHelper.setLichMode(playerEntity, !LichdomHelper.isInLichMode(playerEntity));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


