package za.co.infernos.goety.common.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

/**
 * NeoForge 1.21+ networking uses the payload system (CustomPacketPayload + StreamCodec) registered via
 * {@link RegisterPayloadHandlersEvent}. This class will be filled in as packets are ported.
 */
public class ModNetwork {
    public static final String VERSION = "1";

    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        // Intentionally left minimal for now: packets will be registered here as they are ported.
        event.registrar(VERSION);
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
