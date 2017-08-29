package it.maymity.freezegui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import it.maymity.freezegui.Utils;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        if (Utils.getInstance().getFreezeList().contains(player)) {
            if (inventory != null) {
                if (inventory.getName() != null) {
                    if (!inventory.getName().isEmpty()) {
                        if (inventory.getName().equals(Utils.getInstance().getFreeze().getName())) {
                            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("FreezeGUI"), () -> {
                                if(player.getOpenInventory().equals(Utils.getInstance().getSure().getName()) || player.getOpenInventory().equals(Utils.getInstance().getFreezeAll().getName()))
                                player.openInventory(Utils.getInstance().getFreezeInventory());
                            }, 5L);
                        } else if (inventory.getName().equals(Utils.getInstance().getSure().getName())) {
                            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("FreezeGUI"), () -> {
                                if(player.getOpenInventory().equals(Utils.getInstance().getFreeze().getName()) || player.getOpenInventory().equals(Utils.getInstance().getFreezeAll().getName()))
                                player.openInventory(Utils.getInstance().getSureInventory());
                            }, 5L);
                        } else if (inventory.getName().equals(Utils.getInstance().getFreezeAll().getName())) {
                            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("FreezeGUI"), () -> {
                                if(player.getOpenInventory().equals(Utils.getInstance().getFreeze().getName()) || player.getOpenInventory().equals(Utils.getInstance().getSure().getName()))
                                player.openInventory(Utils.getInstance().getFreezeAllInventory());
                            }, 5L);
                        }
                    }
                }
            }
        }
    }
}