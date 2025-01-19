package com.github.westsi.realchem.chemistry.color;

import net.minecraft.util.Formatting;

import java.util.HashMap;

public class CustomFormatting {
    private static HashMap<Integer, Formatting> builtins = new HashMap<>()
    {{
        put(0x000000, Formatting.BLACK);
        put(0x0000aa, Formatting.DARK_BLUE);
        put(0x00aa00, Formatting.DARK_GREEN);
        put(0x00aaaa, Formatting.DARK_AQUA);
        put(0xaa0000, Formatting.DARK_RED);
        put(0xaa00aa, Formatting.DARK_PURPLE);
        put(0xffaa00, Formatting.GOLD);
        put(0xaaaaaa, Formatting.GRAY);
        put(0x555555, Formatting.DARK_GRAY);
        put(0x5555ff, Formatting.BLUE);
        put(0x55ff55, Formatting.GREEN);
        put(0x55ffff, Formatting.AQUA);
        put(0xff5555, Formatting.RED);
        put(0xff55ff, Formatting.LIGHT_PURPLE);
        put(0xffff55, Formatting.YELLOW);
        put(0xffffff, Formatting.WHITE);
    }};
    public static Formatting getClosestFormattingToHex(Integer hex) {
        int r = (hex & 0xFF0000) >> 16;
        int g = (hex & 0xFF00) >> 8;
        int b = (hex & 0xFF);
        double bestDist = 100000000f;
        Formatting best = Formatting.AQUA;
        for (Integer i : builtins.keySet()) {
            int ir = (i & 0xFF0000) >> 16;
            int ig = (i & 0xFF00) >> 8;
            int ib = (i & 0xFF);
            double distance = Math.sqrt(
                    Math.pow(r - ir, 2)
                    + Math.pow(g - ig, 2)
                    + Math.pow(b - ib, 2)
            );
            if (distance < bestDist) {
                bestDist = distance;
                best = builtins.get(i);
            }
        }
        return best;
    }
}
