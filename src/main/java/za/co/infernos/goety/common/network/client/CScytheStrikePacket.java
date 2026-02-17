package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.common.items.equipment.DeathScytheItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CScytheStrikePacket {
    public static void encode(CScytheStrikePacket packet, FriendlyByteBuf buffer) {
    }

    public static CScytheStrikePacket decode(FriendlyByteBuf buffer) {
        return new CScytheStrikePacket();
    }

    public static void consume(CScytheStrikePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                DeathScytheItem.strike(playerEntity.level(), playerEntity);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


