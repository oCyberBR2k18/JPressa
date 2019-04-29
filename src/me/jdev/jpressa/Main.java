package me.jdev.jpressa;

import commands.pressaCommand;
import listeners.teleportWorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static PluginManager pm = Bukkit.getPluginManager();

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getCommand("pressa").setExecutor(new pressaCommand());
        pm.registerEvents(new teleportWorldEvent(), this);
        if(!Main.plugin.getConfig().getBoolean("ativar")) {
            pm.disablePlugin(this);
        }
        Bukkit.getConsoleSender().sendMessage(Main.plugin.getConfig().getString("plugin-ativado").replaceAll("&", "§"));
        super.onEnable();
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
        Bukkit.getConsoleSender().sendMessage(Main.plugin.getConfig().getString("plugin-desativado").replaceAll("&", "§"));
        super.onDisable();
    }
}
