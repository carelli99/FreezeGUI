package it.maymity.freezegui.commands;

import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class Unfreeze implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getServer().getPlayerExact(args[0]);
                if (player.hasPermission("freezegui.use")) {
                  if(Utils.getInstance().getFreezeList().contains(target)) {
                      if (target != null) {
                          if (target.isOnline()) {
                              if (target.hasPermission("freezegui.use")) {
                                  MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_unfreeze_player"));
                              } else {
                                  MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.unfreeze_message").replaceAll("%player%", target.getName()));
                                  Utils.getInstance().removeFreeze(target);
                                  MessagesManager.getInstance().sendMessage(target, Utils.getInstance().getConfig().getString("messages.notifyunfreeze_message"));
                                  target.closeInventory();
                              }
                          }
                          else
                              MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                      } else
                          MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                  }
                  else
                      MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_freeze"));

                } else
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }

            if (args.length == 0) {
                if (player.hasPermission("freezegui.use"))
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.unfreeze_usage"));
                else
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }
        }
        else
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getConfig().getString("messages.must_player"));
        return true;
    }
}