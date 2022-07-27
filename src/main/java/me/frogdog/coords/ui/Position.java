package me.frogdog.coords.ui;

import me.frogdog.coords.Coords;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public final class Position {

    private double i = 0;
    private MinecraftClient mc = Coords.getMc();

    public void render(MatrixStack matrixStack) {
        mc.inGameHud.getFontRenderer().draw(matrixStack, getCords(), 1.0f, 1.0f, nextColour());
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
