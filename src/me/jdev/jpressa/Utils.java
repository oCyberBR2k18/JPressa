package me.jdev.jpressa;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Utils {

    public static boolean hasPotionEffect(Player p, PotionEffectType type, int amplifier) {
        for (PotionEffect effect : p.getActivePotionEffects()) {
            if (effect.getType().equals(type) && effect.getAmplifier() == amplifier) {
                return true;
            }
        }
        return false;
    }

}
