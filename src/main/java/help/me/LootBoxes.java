package help.me;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class LootBoxes implements Listener {

    @EventHandler
    public void settingKeys (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (getKeys(player) == null) {
            setKeys(player, 0);
        }
    }
    @EventHandler
    public void checkLootbox (PlayerInteractEvent event) {
        Location location = event.getClickedBlock().getLocation();
        if (event.getClickedBlock() != null
                && event.getPlayer() != null
                && event.getAction() == Action.RIGHT_CLICK_BLOCK
                && event.getClickedBlock().hasMetadata("lootbox")
                && getDelay(location)) {
            startGacha(event.getPlayer(), location);
            setDelay(location);
            Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> setDelay(event.getClickedBlock().getLocation()), 91);
        }
    }


    public void startGacha (Player player, Location location) {
        double time = 40;
        float pitch = 1f;
        while (time != 2) {
            float finalPitch = pitch;
            Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> location.getWorld().playSound(location, Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1, finalPitch), (long) time);
            time = time/2;
            pitch = pitch + 0.2f;
        }
        Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> {
            location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 0);
            location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 0.5f, 1.3f);
            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setInvulnerable(true);
            armorStand.setMarker(true);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(getGachedItem((int) (Math.random()*4+1)));
            Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> {
                armorStand.setCustomNameVisible(false);
                armorStand.remove();
            }, 13);
        }, 77);
    }

    public String getGachedItem (int id) {
        String item;
        switch (id) {
            case 1:
                item = ChatColor.LIGHT_PURPLE + "Писька";
                break;
            case 2:
                item = "Кто я?";
                break;
            case 3:
                item = "sonic gay";
                break;
            case 4:
                item = "Я тоже, кстати гей";
                break;
            default:
                item = "Ты шо ебанутый? Как ты смог это выбить?";
                break;
        }
        return item;
    }
    public boolean getDelay (Location location) {
       return Me.getInstance().getConfig().getBoolean("Lootboxes." + location + ".delay");
    }
    public void setDelay (Location location) {
        Me.getInstance().getConfig().set("Lootboxes." + location + ".delay", !getDelay(location));
    }
    public String getKeys (Player player) {
        return Me.getInstance().getConfig().getString("Users." + player.getUniqueId() + ".keys");
    }
    public void setKeys (Player player, int value) {
        Me.getInstance().getConfig().set("Users." + player.getUniqueId() + ".keys", value);
    }
    public void addKeys (Player player, int value) {
        int currentValue = Integer.parseInt(getKeys(player));
        Me.getInstance().getConfig().set("Users." + player.getUniqueId() + ".keys", currentValue + value);
    }

}
