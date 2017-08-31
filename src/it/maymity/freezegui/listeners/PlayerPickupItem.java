package it.maymity.freezegui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.Utils;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItem implements Listener {

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if ((Utils.getInstance().getFreezeList().contains(player))) {
            event.setCancelled(true);
            }
        }
    }