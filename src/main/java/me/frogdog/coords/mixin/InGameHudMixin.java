package me.frogdog.coords.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public abstract class InGameHudMixin {

    @Shadow
    private int scaledWidth;

    @Shadow private int scaledHeight;

    @Shadow @Final
    private MinecraftClient client;

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
        //client.textRenderer.drawWithShadow(matrixStack, "Test", 100.0f, 100.0f, 0xFFFFFF);
    }
}
