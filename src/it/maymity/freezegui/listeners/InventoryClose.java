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
        if (Utils.getInstance().getFreezeList().contains(player) && !Utils.getInstance().getFreezeallcheck()) {
            if (inventory != null) {
                if (inventory.getName() != null) {
                    if (!inventory.getName().isEmpty()) {
                        if ((inventory.getName().equals(Utils.getInstance().getFreeze().getName())) || (inventory.getName().equals(Utils.getInstance().getSure().getName()))) {
                            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("FreezeGUI"), () -> {
                                if (player.getOpenInventory().getType() != InventoryType.CHEST)
                                    player.openInventory(inventory);
                            }, 5L);
                        }
                    }
                }
            }
        }
        else if (Utils.getInstance().getFreezeallcheck()){
            if(inventory.getName().equals(Utils.getInstance().getFreezeAll().getName())){
                Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("FreezeGUI"), () -> {
                    player.openInventory(Utils.getInstance().getFreezeAllInventory());
                }, 5L);
            }
        }
    }
}