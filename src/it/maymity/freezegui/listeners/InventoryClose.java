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
    public void onInvClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        if (Utils.getInstance().getFreezeList().contains(player)) {
            if ((inventory.getName().equals(Utils.getInstance().getFreeze().getName())) || (inventory.getName().equals(Utils.getInstance().getSure().getName()))) {
                    Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("FreezeGUI"), () -> {
                        if(player.getOpenInventory().getType() != InventoryType.CHEST)
                        player.openInventory(inventory);
                    }, 5L);
            }
        }
    }
}