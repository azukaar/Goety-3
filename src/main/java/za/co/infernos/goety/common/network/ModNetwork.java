package za.co.infernos.goety.common.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import za.co.infernos.goety.common.network.client.CActivateCurioKeyPacket;
import za.co.infernos.goety.common.network.client.CAutoRideablePacket;
import za.co.infernos.goety.common.network.client.CBagKeyPacket;
import za.co.infernos.goety.common.network.client.CDismissServantsPacket;
import za.co.infernos.goety.common.network.client.CExtractPotionKeyPacket;
import za.co.infernos.goety.common.network.client.CMagnetPacket;
import za.co.infernos.goety.common.network.client.CMultiJumpPacket;
import za.co.infernos.goety.common.network.client.CRavagerRoarPacket;
import za.co.infernos.goety.common.network.client.CSetLichMode;
import za.co.infernos.goety.common.network.client.CSetLichNightVisionMode;
import za.co.infernos.goety.common.network.client.CStopAttackPacket;
import za.co.infernos.goety.common.network.client.CTargetPlayerPacket;
import za.co.infernos.goety.common.network.client.CWandKeyPacket;
import za.co.infernos.goety.common.network.client.CWitchRobePacket;
import za.co.infernos.goety.common.network.client.brew.CBrewBagKeyPacket;

/**
 * NeoForge 1.21+ networking uses the payload system (CustomPacketPayload + StreamCodec) registered via
 * {@link RegisterPayloadHandlersEvent}. Use {@link net.neoforged.neoforge.network.PacketDistributor} at
 * call sites to actually send payloads.
 */
public class ModNetwork {
    public static final String VERSION = "1";

    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(VERSION);

        registrar.playToServer(CWandKeyPacket.TYPE, CWandKeyPacket.STREAM_CODEC, CWandKeyPacket::handle);
        registrar.playToServer(CBagKeyPacket.TYPE, CBagKeyPacket.STREAM_CODEC, CBagKeyPacket::handle);
        registrar.playToServer(CWitchRobePacket.TYPE, CWitchRobePacket.STREAM_CODEC, CWitchRobePacket::handle);
        registrar.playToServer(CStopAttackPacket.TYPE, CStopAttackPacket.STREAM_CODEC, CStopAttackPacket::handle);
        registrar.playToServer(CMagnetPacket.TYPE, CMagnetPacket.STREAM_CODEC, CMagnetPacket::handle);
        registrar.playToServer(CSetLichNightVisionMode.TYPE, CSetLichNightVisionMode.STREAM_CODEC, CSetLichNightVisionMode::handle);
        registrar.playToServer(CExtractPotionKeyPacket.TYPE, CExtractPotionKeyPacket.STREAM_CODEC, CExtractPotionKeyPacket::handle);
        registrar.playToServer(CBrewBagKeyPacket.TYPE, CBrewBagKeyPacket.STREAM_CODEC, CBrewBagKeyPacket::handle);
        registrar.playToServer(CRavagerRoarPacket.TYPE, CRavagerRoarPacket.STREAM_CODEC, CRavagerRoarPacket::handle);
        registrar.playToServer(CAutoRideablePacket.TYPE, CAutoRideablePacket.STREAM_CODEC, CAutoRideablePacket::handle);
        registrar.playToServer(CSetLichMode.TYPE, CSetLichMode.STREAM_CODEC, CSetLichMode::handle);
        registrar.playToServer(CActivateCurioKeyPacket.TYPE, CActivateCurioKeyPacket.STREAM_CODEC, CActivateCurioKeyPacket::handle);
        registrar.playToServer(CDismissServantsPacket.TYPE, CDismissServantsPacket.STREAM_CODEC, CDismissServantsPacket::handle);
        registrar.playToServer(CTargetPlayerPacket.TYPE, CTargetPlayerPacket.STREAM_CODEC, CTargetPlayerPacket::handle);
        registrar.playToServer(CMultiJumpPacket.TYPE, CMultiJumpPacket.STREAM_CODEC, CMultiJumpPacket::handle);
    }

    // ---------------------------------------------------------------------
    // Temporary compatibility helpers while porting to NeoForge payload networking.
    // These keep the project compiling; they will be replaced with real payload registration + PacketDistributor usage.
    // ---------------------------------------------------------------------

    public static void sendTo(Player player, Object msg) {
        // TODO (NeoForge 1.21): clientbound payload to a specific player
    }

    public static void sendToServer(Object msg) {
        // TODO (NeoForge 1.21): serverbound payload
    }

    public static void sentToTrackingChunk(LevelChunk chunk, Object msg) {
        // TODO (NeoForge 1.21): payload to players tracking a chunk
    }

    public static void sentToTrackingEntity(Entity entity, Object msg) {
        // TODO (NeoForge 1.21): payload to players tracking an entity
    }

    public static void sentToTrackingEntityAndPlayer(Entity entity, Object msg) {
        // TODO (NeoForge 1.21): payload to players tracking an entity + self
    }

    public static void sendToALL(Object msg) {
        // TODO (NeoForge 1.21): payload to all players
    }

    public static void sendToClient(ServerPlayer player, Object msg) {
        // TODO (NeoForge 1.21): payload to client for given player
    }
}
