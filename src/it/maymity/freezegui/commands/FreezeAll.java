package it.maymity.freezegui.commands;

import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class FreezeAll implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            int cont = 0;
            Player player = (Player) sender;
            if (args.length == 0) {
                if (player.hasPermission("freezegui.freezeall")) {
                        if (Utils.getInstance().getFreezeallcheck())
                            MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.already_freezeall"));
                        else {
                            Utils.getInstance().setBoolFreezeAll(true);
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (!p.hasPermission("freezegui.use")) {
                                    if(!p.hasPermission("freezegui.freezeall")){
                                      if (!player.getName().equals(p.getName())) {
                                        cont++;
                                        Utils.getInstance().setFreeze(p);
                                        p.openInventory(Utils.getInstance().getFreezeAllInventory());
                                        MessagesManager.getInstance().sendMessage(p, Utils.getInstance().getConfig().getString("messages.notifyfreeze_message"));
                                    }
                                }
                              }
                            }
                            if (cont == 0)
                                MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_freezeall"));
                            else
                                MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.freezeall"));
                        }
                    } else
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
                }
                if(args.length > 0)
                  MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.freezeall_usage"));
            } else
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getConfig().getString("messages.must_player"));
            return true;
        }
    }