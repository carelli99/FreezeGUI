package it.maymity.freezegui.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.Utils;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
            Entity entity = event.getEntity();
            if (entity instanceof Player) {
                if ((Utils.getInstance().getFreezeList().contains(entity))) {
                    event.setCancelled(true);
                }
            }
        }
    }