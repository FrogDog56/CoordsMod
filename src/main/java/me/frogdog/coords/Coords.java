package me.frogdog.coords;

import me.frogdog.coords.ui.Position;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Coords implements ModInitializer {
    public static final Logger logger = LogManager.getLogger();
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    private Position position;

    @Override
    public void onInitialize() {
        logger.info("Initializing Coords Mod");
        position = new Position();

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            position.render(matrixStack);
        });
    }

    public static Logger getLogger() {
        return logger;
    }

    public static MinecraftClient getMc() {
        return mc;
    }
}
