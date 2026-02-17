package za.co.infernos.goety.common.network.client;

import za.co.infernos.goety.api.entities.IOwned;
import za.co.infernos.goety.init.ModSounds;
import za.co.infernos.goety.utils.ModDamageSource;
import za.co.infernos.goety.utils.SEHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import za.co.infernos.goety.compat.legacy.network.NetworkEvent;

import java.util.function.Supplier;

public class CDismissServantsPacket {
    public static void encode(CDismissServantsPacket packet, FriendlyByteBuf buffer) {
    }

    public static CDismissServantsPacket decode(FriendlyByteBuf buffer) {
        return new CDismissServantsPacket();
    }

    public static void consume(CDismissServantsPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = za.co.infernos.goety.common.network.NetworkContextHelper.getServerPlayer(ctx);

            if (playerEntity != null) {
                if (playerEntity.level() instanceof ServerLevel serverLevel){
                    for (Entity entity : serverLevel.getAllEntities()){
                        if (entity instanceof IOwned owned && owned instanceof LivingEntity livingEntity && owned.getTrueOwner() == playerEntity){
                            if (owned.isLimitedLife() && !SEHelper.getGroundedEntities(playerEntity).contains(livingEntity) && !SEHelper.getGroundedEntityTypes(playerEntity).contains(entity.getType())) {
                                entity.hurt(ModDamageSource.getDamageSource(serverLevel, ModDamageSource.DISMISSED), Float.MAX_VALUE);
                                entity.playSound(ModSounds.ROAR_SPELL.get(), 0.5F, 2.0F);
                            }
                        }
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


