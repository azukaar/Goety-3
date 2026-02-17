package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.config.MainConfig;
import za.co.infernos.goety.utils.LichdomHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CSetLichNightVisionMode {

    public static void encode(CSetLichNightVisionMode packet, FriendlyByteBuf buffer) {
    }

    public static CSetLichNightVisionMode decode(FriendlyByteBuf buffer) {
        return new CSetLichNightVisionMode();
    }

    public static void consume(CSetLichNightVisionMode packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);
            if (playerEntity != null && LichdomHelper.isLich(playerEntity) && za.co.infernos.goety.utils.ConfigHelper.getBoolean(MainConfig.LichNightVision, false)) {
                LichdomHelper.setNightVision(playerEntity, !LichdomHelper.nightVision(playerEntity));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


