package it.maymity.freezegui.commands;

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
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (player.hasPermission("freezegui.use")) {
                  if(Utils.getInstance().getFreezeList().contains(target)) {
                      if (target != null) {
                          if (target.isOnline()) {
                              if (target.hasPermission("freezegui.use")) {
                                  Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_unfreeze_player"));
                              } else {
                                  Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.unfreeze_message").replaceAll("%player%", target.getName()));
                                  Utils.getInstance().removeFreeze(target);
                                  Utils.getInstance().sendPluginMessage(target, Utils.getInstance().getConfig().getString("messages.notifyunfreeze_message"));
                                  target.closeInventory();
                              }
                          }
                          else
                              Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                      } else
                          Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                  }
                  else
                      Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_freeze"));

                } else
                    Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }

            if (args.length == 0) {
                if (player.hasPermission("freezegui.use"))
                    Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.unfreeze_usage"));
                else
                    Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }
        }
        else
            Utils.getInstance().sendPluginSenderMessage(sender, Utils.getInstance().getConfig().getString("messages.must_player"));
        return true;
    }
}