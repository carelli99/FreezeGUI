package it.maymity.freezegui;

import it.maymity.freezegui.commands.Freeze;
import it.maymity.freezegui.commands.Unfreeze;
import it.maymity.freezegui.listeners.EntityDamageByEntity;
import it.maymity.freezegui.listeners.InventoryClose;
import it.maymity.freezegui.listeners.InventoryClick;
import it.maymity.freezegui.listeners.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    Logger log;
    public static int config_version = 1;

    public void onEnable() {
        System.out.println("FreezeGUI > Start plugin...");

        System.out.println("FreezeGUI > Registred the event...");
        registerListeners();
        System.out.println("FreezeGUI > Event registred!");

        System.out.println("FreezeGUI > Registed the command...");
        registerExecutors();
        System.out.println("FreezeGUI > Command registred!");

        System.out.println("FreezeGUI > Plugin enabled!");
        System.out.println("FreezeGUI > Plugin created by Maymity!");


        if ((!Utils.getInstance().getConfig().contains("settings.config_version")) || (Utils.getInstance().getConfig().getInt("settings.config_version") < config_version))
        {
            this.log.warning("&cYou config is out of date, regenerate you config file. Your version: &a" + Utils.getInstance().getConfig().getInt("settings.config_version") + " &cnewest version: &a" + config_version);
        }

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void onDisable() {
        System.out.println("FreezeGUI > Plugin disabled!");
        saveConfig();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClose(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
    }

    private void registerExecutors() {
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze());
        Bukkit.getPluginCommand("unfreeze").setExecutor(new Unfreeze());
    }
}