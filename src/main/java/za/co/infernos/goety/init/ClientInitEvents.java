package za.co.infernos.goety.init;

import za.co.infernos.goety.Goety;
import za.co.infernos.goety.client.render.CuriosRenderer;
import za.co.infernos.goety.client.render.ModModelLayer;
import za.co.infernos.goety.client.render.block.ArcaRenderer;
import za.co.infernos.goety.client.render.block.BlackCrystalRenderer;
import za.co.infernos.goety.client.render.block.LoftyChestRenderer;
import za.co.infernos.goety.client.render.block.ModBlockLayer;
import za.co.infernos.goety.client.render.model.GraveGolemSkullModel;
import za.co.infernos.goety.client.render.model.LichModeModel;
import za.co.infernos.goety.client.render.model.RedstoneGolemSkullModel;
import za.co.infernos.goety.client.render.model.RedstoneMonstrosityHeadModel;
import za.co.infernos.goety.client.render.model.TallSkullModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Goety.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInitEvents {
    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosRenderer.register();
            ModKeybindings.init();
        });
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayer.LICH, LichModeModel::createBodyLayer);
        // Register ModBlockLayer model layers
        event.registerLayerDefinition(ModBlockLayer.TALL_SKULL, TallSkullModel::createBodyLayer);
        event.registerLayerDefinition(ModBlockLayer.REDSTONE_GOLEM_SKULL, RedstoneGolemSkullModel::createBodyLayer);
        event.registerLayerDefinition(ModBlockLayer.GRAVE_GOLEM_SKULL, GraveGolemSkullModel::createBodyLayer);
        event.registerLayerDefinition(ModBlockLayer.REDSTONE_MONSTROSITY_HEAD, RedstoneMonstrosityHeadModel::createBodyLayer);
        event.registerLayerDefinition(ModBlockLayer.LOFTY_CHEST, LoftyChestRenderer::createBodyLayer);
        event.registerLayerDefinition(ModBlockLayer.BLACK_CRYSTAL, BlackCrystalRenderer::createBodyLayer);
        event.registerLayerDefinition(ModBlockLayer.ARCA, ArcaRenderer::createBodyLayer);
    }
}
