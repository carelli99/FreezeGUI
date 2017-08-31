package it.maymity.freezegui.commands;

import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class UnfreezeAll implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            int cont = 0;
            Player player = (Player) sender;
            if (args.length == 0) {
                if (player.hasPermission("freezegui.freezeall")) {
                    if (!Utils.getInstance().getFreezeallcheck())
                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_freezeall"));
                    else {
                        Utils.getInstance().setBoolFreezeAll(false);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (Utils.getInstance().getFreezeList().contains(p)) {
                                    cont++;
                                    Utils.getInstance().removeFreeze(p);
                                    p.closeInventory();
                                    MessagesManager.getInstance().sendMessage(p, Utils.getInstance().getConfig().getString("messages.notifyunfreeze_message"));
                                }
                        }
                        if (cont == 0)
                            MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_unfreezeall"));
                        else
                            MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.unfreezeall"));
                    }
                } else
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.no_permission"));
            }
            if(args.length > 0)
                MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getConfig().getString("messages.unfreezeall_usage"));
        } else
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getConfig().getString("messages.must_player"));
        return true;
    }
}