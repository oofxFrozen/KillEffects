package help.me;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class killEffects extends keList implements Listener {

        @EventHandler
        public void keChoose(InventoryClickEvent event) {
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            if (event.getInventory().contains(customInventory.NONE)) {
                event.setCancelled(true);
                checkClickedItem(player, clickedItem);
            }
        }

        @EventHandler
        public void KillEffect(EntityDeathEvent event) {
            if (event.getEntity().getKiller() != null) {
                Player player = event.getEntity().getKiller();
                Location location = event.getEntity().getLocation();
                Player killed = (Player) event.getEntity();
                event.setShouldPlayDeathSound(false);
                playKillEffect(player, location, killed);
            }
        }

}

