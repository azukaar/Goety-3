package com.Polarice3.Goety.common.network.client;

import com.Polarice3.Goety.common.inventory.ModSaveInventory;
import com.Polarice3.Goety.common.inventory.WitchRobeInventory;
import com.Polarice3.Goety.common.items.curios.WitchRobeItem;
import com.Polarice3.Goety.utils.CuriosFinder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import com.Polarice3.Goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CExtractPotionKeyPacket {
    public static void encode(CExtractPotionKeyPacket packet, FriendlyByteBuf buffer) {
    }

    public static CExtractPotionKeyPacket decode(FriendlyByteBuf buffer) {
        return new CExtractPotionKeyPacket();
    }

    public static void consume(CExtractPotionKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = com.Polarice3.Goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

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


