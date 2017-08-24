package it.maymity.freezegui.commands;

import it.maymity.freezegui.managers.MessagesManager;
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
                Player target = Bukkit.getPlayerExact(args[0]);
                if (player.hasPermission("freezegui.use")) {
                    if(target != null) {
                        if (target.isOnline()) {
                            if (player.getName().equals(target.getName())) {
                                MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_freeze_yourself"));
                            } else if (target.hasPermission("freezegui.use")) {
                                MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_freeze_player"));
                            } else if (Utils.getInstance().getFreezeList().contains(target)) {
                                MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.already_freeze"));
                            } else {
                                MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.freeze_message").replaceAll("%player%", target.getName()));
                                Utils.getInstance().setFreeze(target);
                                MessagesManager.getInstance().sendMessage(target, Utils.getInstance().getConfig().getString("messages.notifyfreeze_message"));
                                target.openInventory(Utils.getInstance().getFreezeInventory());
                            }
                        }
                        else
                            MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                    }
                    else
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.player_not_found"));
                }else
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }

            if (args.length == 0) {
                    if (player.hasPermission("freezegui.use"))
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.freeze_usage"));
                    else
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
                }
            }
        else
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getConfig().getString("messages.must_player"));
            return true;
        }
    }

