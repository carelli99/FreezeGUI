package it.maymity.freezegui.commands;

import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class FreezeGui implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("freezegui.reload")){
                        Bukkit.getPluginManager().getPlugin("FreezeGUI").reloadConfig();
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.reload"));
                        }
                    else
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
                    }
                }

            if (args.length == 0) {
                if (player.hasPermission("freezegui.reload"))
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.reload_usage"));
                else
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }
        }
        else
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getConfig().getString("messages.must_player"));
        return true;
    }
}
