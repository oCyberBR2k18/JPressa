package listeners;

import me.jdev.jpressa.Main;
import me.jdev.jpressa.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class teleportWorldEvent implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        String mundo = e.getTo().getWorld().getName();
        if(Main.plugin.getConfig().getStringList("desabilitar").contains(mundo)) {
            if(Utils.hasPotionEffect(e.getPlayer(), PotionEffectType.FAST_DIGGING, 1)) {
                e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
                e.getPlayer().sendMessage(Main.plugin.getConfig().getString("saiu-da-mina").replaceAll("&", "§"));
            }
        }
        if(e.getTo().getWorld().getName().equals(Main.plugin.getConfig().getString("mundoMina"))) {
            if(Main.plugin.getConfig().getBoolean("receber-automaticamente")) {
                if(!(Utils.hasPotionEffect(e.getPlayer(), PotionEffectType.FAST_DIGGING, 1))) {
                    if (e.getPlayer().hasPermission(Main.plugin.getConfig().getString("permission"))) {
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1), true);
                        e.getPlayer().sendMessage(Main.plugin.getConfig().getString("entrou-na-mina").replaceAll("&", "§"));
                    }
                }
            }
        }
    }

}
