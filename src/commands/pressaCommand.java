package commands;

import me.jdev.jpressa.Main;
import me.jdev.jpressa.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class pressaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

        if(!(sender instanceof Player)) {return true;}

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission(Main.plugin.getConfig().getString("permission"))) {
                if (p.getWorld().getName().equals(Main.plugin.getConfig().getString("mundoMina"))) {
                    if (!(Utils.hasPotionEffect(p, PotionEffectType.FAST_DIGGING, 1))) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                        for(String msg : Main.plugin.getConfig().getStringList("pressa-ativada")) {
                            p.sendMessage(msg.replaceAll("&", "§"));
                        }
                    } else {
                        p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                        for(String msg : Main.plugin.getConfig().getStringList("pressa-desativada")) {
                            p.sendMessage(msg.replaceAll("&", "§"));
                        }
                    }
                } else {
                    p.sendMessage(Main.plugin.getConfig().getString("mundo-errado").replaceAll("&", "§"));
                }
            } else {
                p.sendMessage(Main.plugin.getConfig().getString("sem-permissao").replaceAll("&", "§"));
            }
        }

        return false;
    }
}
