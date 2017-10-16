package it.maymity.freezegui.listeners;

import it.maymity.freezegui.Utils;
import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Utils.getInstance().getConfig().getBoolean("settings.check_update_on_join")) {
            if (player.hasPermission("freezegui.checkupdate")) {
                if (Utils.getInstance().getBoolUpdate()) {
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.update_message"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDownload:&e " + Utils.getInstance().getUpdateLink()));
                }
            }
        }
    }
}
