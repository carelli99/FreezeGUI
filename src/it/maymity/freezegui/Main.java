package it.maymity.freezegui;

import it.maymity.freezegui.commands.*;
import it.maymity.freezegui.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    Logger log;
    public static double config_version = 2.1;

    public void onEnable() {
        System.out.println("FreezeGUI > Start plugin...");

        System.out.println("FreezeGUI > Registred the event...");
        registerListeners();
        System.out.println("FreezeGUI > Event registred!");

        System.out.println("FreezeGUI > Registed the command...");
        registerExecutors();
        System.out.println("FreezeGUI > Command registred!");

            SpigotUpdater updater = new SpigotUpdater(this, 46176);
            try {
                if (updater.checkForUpdates()) {
                    Utils.getInstance().setBoolUpdate(true);
                    Utils.getInstance().setUpdateLink(updater.getResourceURL());
                    System.out.println("========================================================");
                    System.out.println("FreezeGUI Update Checker");
                    System.out.println("There is a new update available");
                    System.out.println("Latest Version: " + updater.getLatestVersion());
                    System.out.println("Your Version: " + updater.getPlugin().getDescription().getVersion());
                    System.out.println("Get it here: " + updater.getResourceURL());
                    System.out.println("========================================================");
                } else {
                    System.out.println("========================================================");
                    System.out.println("FreezeGUI Update Checker");
                    System.out.println("You are using the latest version!");
                    System.out.println("========================================================");
                }
            } catch (Exception e) {
                System.out.println("========================================================");
                System.out.println("FreezeGUI Update Checker");
                System.out.println("Could not connect to Spigot's API!");
                System.out.println("Error: ");
                e.printStackTrace();
                System.out.println("========================================================");
            }

        System.out.println("FreezeGUI > Plugin enabled!");
        System.out.println("FreezeGUI > Plugin created by Maymity!");


        if ((!Utils.getInstance().getConfig().contains("settings.config_version")) || (Utils.getInstance().getConfig().getDouble("settings.config_version") < config_version)) {
            this.log.warning("&cYou config is out of date, regenerate you config file. Your version: &a" + Utils.getInstance().getConfig().getDouble("settings.config_version") + " &cnewest version: &a" + config_version);
        }

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void onDisable() {
        System.out.println("FreezeGUI > Plugin disabled!");
        saveConfig();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryClose(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocess(), this);
        Bukkit.getPluginManager().registerEvents(new EntityShootBow(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItem(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    private void registerExecutors() {
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze());
        Bukkit.getPluginCommand("unfreeze").setExecutor(new Unfreeze());
        Bukkit.getPluginCommand("freezeall").setExecutor(new FreezeAll());
        Bukkit.getPluginCommand("unfreezeall").setExecutor(new UnfreezeAll());
    }
}