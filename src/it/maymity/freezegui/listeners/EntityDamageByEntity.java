package it.maymity.freezegui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import it.maymity.freezegui.Utils;


public class EntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (((event.getEntity() instanceof Player)) && ((event.getDamager() instanceof Player))) {
            Player playerDamaged = (Player) event.getEntity();
            Player playerDamager = (Player) event.getDamager();

            if ((Utils.getInstance().getFreezeList().contains(playerDamaged)) || (Utils.getInstance().getFreezeList().contains(playerDamager))){
                event.setCancelled(true);
            }
        }
    }
}
