package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.common.inventory.ModSaveInventory;
import za.co.infernos.goety.common.inventory.WitchRobeInventory;
import za.co.infernos.goety.common.items.curios.WitchRobeItem;
import za.co.infernos.goety.utils.CuriosFinder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CExtractPotionKeyPacket {
    public static void encode(CExtractPotionKeyPacket packet, FriendlyByteBuf buffer) {
    }

    public static CExtractPotionKeyPacket decode(FriendlyByteBuf buffer) {
        return new CExtractPotionKeyPacket();
    }

    public static void consume(CExtractPotionKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                ItemStack stack = CuriosFinder.findCurio(playerEntity, itemStack -> itemStack.getItem() instanceof WitchRobeItem);

                if (!stack.isEmpty()){
                    int inventoryId = WitchRobeItem.getOrCreateInventoryId(stack);
                    if (inventoryId < 0) {
                        return;
                    }
                    WitchRobeInventory inventory = ModSaveInventory.getInstance().getWitchRobeInventory(inventoryId, playerEntity);
                    inventory.extractPotions();
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


