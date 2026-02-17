package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.common.magic.cantrips.MagnetCantrip;
import za.co.infernos.goety.utils.LichdomHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CMagnetPacket {
    public static void encode(CMagnetPacket packet, FriendlyByteBuf buffer) {
    }

    public static CMagnetPacket decode(FriendlyByteBuf buffer) {
        return new CMagnetPacket();
    }

    public static void consume(CMagnetPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                if (LichdomHelper.isLich(playerEntity)) {
                    new MagnetCantrip().callItems(playerEntity);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


