package it.maymity.freezegui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import it.maymity.freezegui.Utils;

public class PlayerCommandPreprocess implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (Utils.getInstance().getFreezeList().contains(player)) {
            event.setCancelled(true);
        }
    }
}
