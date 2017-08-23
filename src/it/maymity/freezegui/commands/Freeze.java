package it.maymity.freezegui.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.no_freeze_yourself")));
                            } else if (target.hasPermission("freezegui.use")) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.no_freeze_player")));
                            } else if (Utils.getInstance().getFreezeList().contains(target)) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.already_freeze")));
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.freeze_message").replaceAll("%player%", target.getName())));
                                Utils.getInstance().setFreeze(target);
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.notifyfreeze_message")));
                                target.openInventory(Utils.getInstance().getFreezeInventory());
                            }
                        }
                        else
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.player_not_found")));
                    }
                    else
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.player_not_found")));
                }else
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',Utils.getInstance().getConfig().getString("messages.no_permission")));
            }

            if (args.length == 0) {
                    if (player.hasPermission("freezegui.use"))
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',Utils.getInstance().getConfig().getString("messages.freeze_usage")));
                    else
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.no_permission")));
                }
            }
        else
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("messages.must_player")));
            return true;
        }
    }

