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

public class CAddFocusToBagPacket {
    public CAddFocusToBagPacket(){
    }

    public static void encode(CAddFocusToBagPacket packet, FriendlyByteBuf buffer) {
    }

    public static CAddFocusToBagPacket decode(FriendlyByteBuf buffer) {
        return new CAddFocusToBagPacket();
    }

    public static void consume(CAddFocusToBagPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);
            if (player != null) {
                ItemStack stack = TotemFinder.findBag(player);
                if (stack.getCount() <= 0) {
                    return;
                }

                ItemStack wand = WandUtil.findWand(player);

                FocusBagItemHandler bagHandler = FocusBagItemHandler.get(stack);
                SoulUsingItemHandler wandHandler = SoulUsingItemHandler.get(wand);

                ItemStack wandFocus = wandHandler.getSlot();

                for (int i = 1; i < bagHandler.getSlots(); ++i) {
                    ItemStack itemStack = bagHandler.getStackInSlot(i);
                    if (itemStack.isEmpty()) {
                        bagHandler.setStackInSlot(i, wandFocus);
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



