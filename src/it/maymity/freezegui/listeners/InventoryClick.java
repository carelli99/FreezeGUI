package it.maymity.freezegui.listeners;

import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import it.maymity.freezegui.Utils;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if (Utils.getInstance().getFreezeList().contains(player)) {
            if (inventory != null) {
                if (inventory.getName() != null) {
                    if (!inventory.getName().isEmpty()) {
                        if (inventory.getName().equals(Utils.getInstance().getFreeze().getName())) {
                            if (clicked.getType() != null) {
                                if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("freezegui.Items." + 1 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("freezegui.Items." + 1 + ".damage"))) {
                                    player.openInventory(Utils.getInstance().getSureInventory());
                                }
                                event.setCancelled(true);
                            }
                        }
                        else if (inventory.getName().equals(Utils.getInstance().getSure().getName())) {
                            if (clicked.getType() != null) {
                                if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("suregui.Items." + 1 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("suregui.Items." + 1 + ".damage"))) {
                                    Utils.getInstance().removeFreeze(player);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Utils.getInstance().getConfig().getString("commands.punish_command").replaceAll("%player%", ((Player) event.getWhoClicked()).getDisplayName()));
                                } else if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("suregui.Items." + 2 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("suregui.Items." + 2 + ".damage"))) {
                                    player.openInventory(Utils.getInstance().getFreezeInventory());
                                }
                                event.setCancelled(true);
                            }
                        }
                        else if (inventory.getName().equals(Utils.getInstance().getFreezeAll().getName())) {
                            if (clicked.getType() != null) {
                                if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("freezeallgui.Items." + 1 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("freezeallgui.Items." + 1 + ".damage"))) {
                                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.notifyfreeze_message"));
                                }
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}