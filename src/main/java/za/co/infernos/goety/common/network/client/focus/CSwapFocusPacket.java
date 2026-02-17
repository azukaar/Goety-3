package za.co.infernos.goety.common.network.client.focus;

import za.co.infernos.goety.common.items.handler.FocusBagItemHandler;
import za.co.infernos.goety.common.items.handler.SoulUsingItemHandler;
import za.co.infernos.goety.init.ModSounds;
import za.co.infernos.goety.utils.TotemFinder;
import za.co.infernos.goety.utils.WandUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CSwapFocusPacket {
    public int swapWith;

    public CSwapFocusPacket(int swapWith){
        this.swapWith = swapWith;
    }

    public static void encode(CSwapFocusPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.swapWith);
    }

    public static CSwapFocusPacket decode(FriendlyByteBuf buffer) {
        return new CSwapFocusPacket(buffer.readInt());
    }

    public static void consume(CSwapFocusPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);
            if (player != null) {
                swapFocus(packet.swapWith, player);
            }
        });
    }

    public static void swapFocus(int swapSlot, Player player) {
        ItemStack stack = TotemFinder.findBag(player);
        if (stack.getCount() <= 0) {
            return;
        }

        ItemStack wand = WandUtil.findWand(player);

        FocusBagItemHandler bagHandler = FocusBagItemHandler.get(stack);
        SoulUsingItemHandler wandHandler = SoulUsingItemHandler.get(wand);

        ItemStack wandFocus = wandHandler.getSlot();

        ItemStack bagFocus = bagHandler.getStackInSlot(swapSlot);
        bagHandler.setStackInSlot(swapSlot, wandFocus);
        wandHandler.extractItem();
        wandHandler.insertItem(bagFocus);
        if (player instanceof ServerPlayer serverPlayer){
            serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(ModSounds.FOCUS_PICK.value()), SoundSource.PLAYERS, serverPlayer.position().x, serverPlayer.position().y, serverPlayer.position().z, 1.0F, 1.0F, serverPlayer.level().getRandom().nextLong()));
        }
    }
}



