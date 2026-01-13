package com.Polarice3.Goety.client.gui.overlay;

import com.Polarice3.Goety.client.gui.screen.inventory.FocusRadialMenuScreen;
import com.Polarice3.Goety.config.MainConfig;
import com.Polarice3.Goety.utils.WandUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

public class CurrentFocusGui {
    private static final Minecraft minecraft = Minecraft.getInstance();
    public static final ResourceLocation LAYER_ID = com.Polarice3.Goety.Goety.location("current_focus_hud");
    public static final LayeredDraw.Layer LAYER = (guiGraphics, partialTick) -> {
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        drawHUD(guiGraphics, partialTick, screenWidth, screenHeight);
    };

    public static boolean shouldDisplayBar(){
        return !WandUtil.findFocus(minecraft.player).isEmpty() && (minecraft.gameMode != null && minecraft.gameMode.getPlayerMode() != GameType.SPECTATOR) && !(minecraft.screen instanceof FocusRadialMenuScreen) && MainConfig.FocusGuiShow.get();
    }

    public static void drawHUD(GuiGraphics guiGraphics, DeltaTracker partialTick, int screenWidth, int screenHeight) {
        if(!shouldDisplayBar()) {
            return;
        }

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1.0F, 1.0F, 1.0F);
        if (WandUtil.findFocus(minecraft.player) != null) {
            guiGraphics.renderFakeItem(WandUtil.findFocus(minecraft.player), ((screenWidth - 16) / 2) + MainConfig.FocusGuiHorizontal.get(), (screenHeight - 52) + MainConfig.FocusGuiVertical.get());
            guiGraphics.renderItemDecorations(minecraft.font, WandUtil.findFocus(minecraft.player), ((screenWidth - 16) / 2) + MainConfig.FocusGuiHorizontal.get(), (screenHeight - 52) + MainConfig.FocusGuiVertical.get());
        }
        guiGraphics.pose().popPose();
    }
}