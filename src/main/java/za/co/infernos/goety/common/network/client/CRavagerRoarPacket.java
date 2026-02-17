package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.common.entities.neutral.IRavager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CRavagerRoarPacket {
    public static void encode(CRavagerRoarPacket packet, FriendlyByteBuf buffer) {
    }

    public static CRavagerRoarPacket decode(FriendlyByteBuf buffer) {
        return new CRavagerRoarPacket();
    }

    public static void consume(CRavagerRoarPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                if (playerEntity.getVehicle() instanceof IRavager ravager){
                    ravager.forceRoar();
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


