package it.maymity.freezegui.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class Freeze implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (player.hasPermission("freezegui.use")) {
                    if(target != null) {
                        if (target.isOnline()) {
                            if (player.getName().equals(target.getName())) {
                                Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_freeze_yourself"));
                            } else if (target.hasPermission("freezegui.use")) {
                                Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_freeze_player"));
                            } else if (Utils.getInstance().getFreezeList().contains(target)) {
                                Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.already_freeze"));
                            } else {
                                Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.freeze_message").replaceAll("%player%", target.getName()));
                                Utils.getInstance().setFreeze(target);
                                Utils.getInstance().sendPluginMessage(target, Utils.getInstance().getConfig().getString("messages.notifyfreeze_message"));
                                target.openInventory(Utils.getInstance().getFreezeInventory());
                            }
                        }
                        else
                            Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                    }
                    else
                        Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                }else
                    Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }

            if (args.length == 0) {
                    if (player.hasPermission("freezegui.use"))
                        Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.freeze_usage"));
                    else
                        Utils.getInstance().sendPluginMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
                }
            }
        else
            Utils.getInstance().sendPluginSenderMessage(sender, Utils.getInstance().getConfig().getString("messages.must_player"));
            return true;
        }
    }

