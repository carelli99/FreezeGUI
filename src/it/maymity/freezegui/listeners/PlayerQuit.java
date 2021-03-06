package it.maymity.freezegui.listeners;

import it.maymity.freezegui.Utils;
import it.maymity.freezegui.managers.MessagesManager;
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
            Utils.getInstance().removeFreeze(p);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Utils.getInstance().getConfig().getString("commands.slog_command").replaceAll("%player%", ((p.getDisplayName()))));
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("freezegui.use"))
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.quit_message").replaceAll("%player%", p.getName()));
            }
        }
    }
}