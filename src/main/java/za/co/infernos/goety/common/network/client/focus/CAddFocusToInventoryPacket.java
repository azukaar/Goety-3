package za.co.infernos.goety.common.network.client.focus;

import za.co.infernos.goety.common.items.handler.SoulUsingItemHandler;
import za.co.infernos.goety.init.ModSounds;
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

public class CAddFocusToInventoryPacket {
    public CAddFocusToInventoryPacket(){
    }

    public static void encode(CAddFocusToInventoryPacket packet, FriendlyByteBuf buffer) {
    }

    public static CAddFocusToInventoryPacket decode(FriendlyByteBuf buffer) {
        return new CAddFocusToInventoryPacket();
    }

    public static void consume(CAddFocusToInventoryPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);
            if (player != null) {
                ItemStack stack = WandUtil.findFocus(player);
                if (stack.getCount() <= 0) {
                    return;
                }

                ItemStack wand = WandUtil.findWand(player);

                SoulUsingItemHandler wandHandler = SoulUsingItemHandler.get(wand);

                ItemStack wandFocus = wandHandler.getSlot();

                for (int i = 0; i < player.getInventory().items.size(); ++i) {
                    ItemStack itemStack = player.getInventory().getItem(i);
                    if (itemStack.isEmpty()) {
                        player.getInventory().setItem(i, wandFocus);
                        wandHandler.extractItem();
                        break;
                    }
                }
                if (player instanceof ServerPlayer serverPlayer){
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(ModSounds.FOCUS_PICK.value()), SoundSource.PLAYERS, serverPlayer.position().x, serverPlayer.position().y, serverPlayer.position().z, 1.0F, 1.0F, serverPlayer.level().getRandom().nextLong()));
                }
            }
        });
    }
}



