package it.maymity.freezegui.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.Utils;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EntityShootBow implements Listener {

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            if ((Utils.getInstance().getFreezeList().contains(entity))) {
                event.setCancelled(true);
            }
        }
    }
}
