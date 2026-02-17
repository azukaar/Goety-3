package com.Polarice3.Goety.init;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.client.render.CuriosRenderer;
import com.Polarice3.Goety.client.render.ModModelLayer;
import com.Polarice3.Goety.client.render.block.ArcaRenderer;
import com.Polarice3.Goety.client.render.block.BlackCrystalRenderer;
import com.Polarice3.Goety.client.render.block.LoftyChestRenderer;
import com.Polarice3.Goety.client.render.block.ModBlockLayer;
import com.Polarice3.Goety.client.render.model.GraveGolemSkullModel;
import com.Polarice3.Goety.client.render.model.LichModeModel;
import com.Polarice3.Goety.client.render.model.RedstoneGolemSkullModel;
import com.Polarice3.Goety.client.render.model.RedstoneMonstrosityHeadModel;
import com.Polarice3.Goety.client.render.model.TallSkullModel;
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
