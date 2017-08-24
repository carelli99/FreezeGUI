package it.maymity.freezegui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import it.maymity.freezegui.Utils;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (Utils.getInstance().getFreezeList().contains(p)) {
                event.getPlayer().setAllowFlight(true);
                event.getPlayer().teleport(event.getFrom());
            }
        }
    }