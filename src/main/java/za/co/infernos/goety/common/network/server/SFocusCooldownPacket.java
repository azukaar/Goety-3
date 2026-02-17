package za.co.infernos.goety.common.network.server;

import za.co.infernos.goety.Goety;
import za.co.infernos.goety.utils.SEHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class SFocusCooldownPacket {
    private final Item item;
    private final int duration;

    public SFocusCooldownPacket(Item p_132000_, int p_132001_) {
        this.item = p_132000_;
        this.duration = p_132001_;
    }

    public static void encode(SFocusCooldownPacket packet, FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(BuiltInRegistries.ITEM.getKey(packet.item));
        buffer.writeVarInt(packet.duration);
    }

    public static SFocusCooldownPacket decode(FriendlyByteBuf buffer) {
        return new SFocusCooldownPacket(BuiltInRegistries.ITEM.get(buffer.readResourceLocation()), buffer.readVarInt());
    }

    public static void consume(SFocusCooldownPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Goety.PROXY.getPlayer();
            if (player != null) {
                if (packet.duration == 0) {
                    SEHelper.getFocusCoolDown(player).removeCooldown(player, player.level(), packet.item);
                } else {
                    SEHelper.addCooldown(player, packet.item, packet.duration);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
