package it.maymity.freezegui.listeners;

import it.maymity.freezegui.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (Utils.getInstance().getFreezeList().contains(p)) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Utils.getInstance().getConfig().getString("commands.slog_command").replaceAll("%player%", ((p.getDisplayName()))));
            Utils.getInstance().removeFreeze(p);
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("freezegui.use"))
                Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.quit_message").replaceAll("%player%", p.getName()));
        }
    }
}