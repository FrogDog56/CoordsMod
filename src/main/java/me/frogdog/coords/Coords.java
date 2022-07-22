package me.frogdog.coords;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Coords implements ModInitializer {
    public static final String MODID = "hud_tweaks";
    public static final String NAME = "Hud Tweaks";
    public static final String VERSION = "0.0.2";

    public static final Logger logger = LogManager.getLogger();
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    private double i = 0;
    private boolean toggled = true;
    private boolean rainbow = false;

    @Override
    public void onInitialize() {
        logger.info("Initializing Coords Mod");

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            mc.inGameHud.getFontRenderer().draw(matrixStack, getCords(), 1.0f, 1.0f, nextColour());
        });
    }

    private String getCords() {
        if (mc.world != null && mc.player != null)  {
            if (mc.world.getDimension().isBedWorking()) {
                return "XYZ " + (long) mc.player.getX() + " " + (long) mc.player.getY() + " " + (long) mc.player.getZ() + " Nether " + (long) mc.player.getX() / 8 + " " + (long) mc.player.getY() + " " + (long) mc.player.getZ() / 8;
            }

            if (!mc.world.getDimension().isBedWorking() && mc.world.getDimension().hasCeiling()) {
                return "XYZ " + (long) mc.player.getX() + " " + (long) mc.player.getY() + " " + (long) mc.player.getZ() + " Overworld " + (long) mc.player.getX() * 8 + " " + (long) mc.player.getY() + " " + (long) mc.player.getZ() * 8;
            }

            if (!mc.world.getDimension().hasSkyLight() && !mc.world.getDimension().hasCeiling()) {
                return "XYZ " + (long) mc.player.getX() + " " + (long) mc.player.getY() + " " + (long) mc.player.getZ();
            }

        }
        return "Error";
    }

    private String getFacing() {
        if (mc.player != null) {
            return "Facing " + mc.player.getHorizontalFacing().getName();
        }
        return "Error";
    }

    private int nextColour() {
        if (i > 6) {
            i = 0;
        }

        int[] colours = {0xFFFFFF, 0xFFFF55, 0xFF55FF, 0xFF5555, 0x55FFFF, 0x55FF55, 0x5555FF};
        int index = (int) Math.ceil(i);
        int colour = colours[index];

        i += 0.0125;

        return colour;
    }
}
