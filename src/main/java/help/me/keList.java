package help.me;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.EulerAngle;

import java.util.List;

public class keList {


    public void setKillEffect (Player player, String name) {
        Me.getInstance().getConfig().set("Users." + player.getUniqueId() + ".killEffect", name);
        Me.getInstance().getConfig().set("Users." + player.getUniqueId() + ".lobbyEffect", name);
        Me.getInstance().saveConfig();
    }

    public String getKillEffect (Player player) {
        return Me.getInstance().getConfig().getString("Users." + player.getUniqueId() + ".killEffect");
    }

    public void checkClickedItem (Player player, ItemStack item) {
        if (item.equals(new ItemStack(Material.BARRIER))) setKillEffect(player, "NONE");
        if (item.equals(new ItemStack(Material.SPECKLED_MELON))) setKillEffect(player,"RAINBOW");
        if (item.equals(new ItemStack(Material.BONE))) setKillEffect(player, "WHITE");
        if (item.equals(customInventory.BONE_MEAL)) setKillEffect(player,"WHITE_EXPLOSION");
        if (item.equals(new ItemStack(Material.SLIME_BALL))) setKillEffect(player, "SLIME");
        if (item.equals(new ItemStack(Material.BLAZE_ROD))) setKillEffect(player, "FIRE");
        if (item.equals(new ItemStack(Material.BLAZE_POWDER))) setKillEffect(player, "FIRE_EXPLOSION");
        if (item.equals(new ItemStack(Material.SKULL_ITEM))) setKillEffect(player, "FINAL_DEATH");
        if (item.equals(new ItemStack(Material.CACTUS))) setKillEffect(player, "REKT");
        if (item.equals(new ItemStack(Material.SNOW_BALL))) setKillEffect(player, "SNOW");
    }

    public double getRndmX () {
        return Math.random() * 1.5 - 0.75;
    }
    public double getRndmZ () {
        return Math.random() * 1.5 - 0.75;
    }
    public double getRndmY () {
        return Math.random() * 2;
    }
    public int getRndmColor () {
        int randomColor = (int) (Math.random() + 1);
        if (randomColor == 1) return 255;
        else return 0;
    }

    public void playKillEffect (Player player, Location l, Entity killed) {
        if (getKillEffect(player).equals("RAINBOW")) {
            for (int i = 0; i < 25; i++)
                l.getWorld().spawnParticle(Particle.REDSTONE,
                        l.getX() + getRndmX(), l.getY() + getRndmY(), l.getZ() + getRndmZ(), 0,
                        getRndmColor(), getRndmColor(), getRndmColor(), 1);
        }
        if (getKillEffect(player).equals("WHITE")) {
            for (int i = 0; i < 25; i++)
                l.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,
                        l.getX() + getRndmX(), l.getY() + getRndmY(), l.getZ() + getRndmZ(), 0);
            l.getWorld().playSound(l, Sound.BLOCK_WOOD_HIT, (float) 0.9, (float) 0.8);
        }
        if (getKillEffect(player).equals("WHITE_EXPLOSION")) {
            for (int i = 0; i < 30; i++)
                l.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,
                        l.getX() + getRndmX(), l.getY() + getRndmY(), l.getZ() + getRndmZ(), 1);
            l.getWorld().playSound(l, Sound.ENTITY_GHAST_SHOOT, (float) 0.2, (float) 1);
        }
        if (getKillEffect(player).equals("SLIME")) {
            l.getWorld().playSound(l, Sound.ENTITY_SLIME_DEATH, (float) 1.5, (float) 0.8);
            for (int i = 0; i < 25; i++)
                l.getWorld().spawnParticle(Particle.SLIME,
                        l.getX() + getRndmX(), l.getY() + getRndmY(), l.getZ() + getRndmZ(), 0);
        }
        if (getKillEffect(player).equals("FIRE")) {
            l.getWorld().playSound(l, Sound.BLOCK_FIRE_AMBIENT, (float) 0.6, (float) 1.5);
            for (int i = 0; i < 25; i++)
                l.getWorld().spawnParticle(Particle.FLAME,
                        l.getX() + getRndmX(), l.getY() + getRndmY(), l.getZ() + getRndmZ(), 0);
        }
        if (getKillEffect(player).equals("FIRE_EXPLOSION")) {
            l.getWorld().playSound(l, Sound.BLOCK_FIRE_AMBIENT, (float) 0.6, (float) 1.5);
            for (int i = 0; i < 25; i++)
                l.getWorld().spawnParticle(Particle.FLAME,
                        l.getX() + getRndmX(), l.getY() + getRndmY(), l.getZ() + getRndmZ(), 2);
        }
        if (getKillEffect(player).equals("REKT")) {
            ArmorStand rekted = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0, 1, 0), EntityType.ARMOR_STAND);
            rekted.setVisible(false);
            rekted.setMarker(true);
            rekted.setCollidable(false);
            rekted.setGravity(false);
            rekted.setCustomNameVisible(true);
            rekted.setInvulnerable(true);
            l.getWorld().playSound(l, Sound.ENTITY_CAT_AMBIENT, 0.1f, 1f);
            rekted.setCustomName(ChatColor.RED + "#" + ChatColor.GOLD + "R" + ChatColor.YELLOW + "E" + ChatColor.GREEN + "K" + ChatColor.BLUE + "T");
            Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> {
                rekted.setInvulnerable(false);
                rekted.remove();
            }, 20 * 2);
        }
        if (getKillEffect(player).equals("SNOW")) {
            l.getWorld().playSound(l,Sound.BLOCK_SNOW_STEP, 1f, 1.3f);
            for (int i = 0; i < 25; i++)
                l.getWorld().spawnParticle(Particle.SNOWBALL,
                        l.getX() + getRndmX(), l.getY() + getRndmY(), l.getZ() + getRndmZ(), 0);
        }
        if (getKillEffect(player).equals("FINAL_DEATH")) {
            String color;
            String team = "";
            if (!killed.getMetadata("team").isEmpty()) {
                List<MetadataValue> values = killed.getMetadata("team");
                team = values.get(0).asString();
            }
            LeatherArmorMeta meta = getMetaColor(team);
            color = getTeamColor(team);
            ItemStack skull = new ItemStack(Material.SKULL_ITEM);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            chestplate.setItemMeta(meta);
            ArmorStand dead = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0, -1, 0), EntityType.ARMOR_STAND);
            dead.setChestplate(chestplate);
            dead.setHelmet(skull);
            dead.setInvulnerable(true);
            dead.setVisible(false);
            dead.setMarker(true);
            dead.setCollidable(false);
            dead.setGravity(false);
            dead.setBodyPose(new EulerAngle(Math.toRadians(270), Math.toRadians(0), Math.toRadians(0)));
            dead.setHeadPose(new EulerAngle(Math.toRadians(287), Math.toRadians(7), Math.toRadians(17)));
            ArmorStand deadName = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0, 1, 0), EntityType.ARMOR_STAND);
            deadName.setInvulnerable(true);
            deadName.setVisible(false);
            deadName.setMarker(true);
            deadName.setCollidable(false);
            deadName.setGravity(false);
            deadName.setCustomNameVisible(true);
            deadName.setCustomName(color + killed.getName());
        }
    }

    public LeatherArmorMeta getMetaColor (String team) {
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
        switch (team) {
            case ("RED"):
                meta.setColor(Color.RED);
                break;
            case ("GREEN"):
                meta.setColor(Color.GREEN);
                break;
            case ("BLUE"):
                meta.setColor(Color.BLUE);
                break;
            case ("YELLOW"):
                meta.setColor(Color.YELLOW);
                break;
            default:
                meta.setColor(Color.WHITE);
                break;
        }
        return meta;
    }

    public String getTeamColor (String team) {
        String color;
        switch (team) {
            case ("RED"):
                color = "" + ChatColor.RED;
                break;
            case ("GREEN"):
                color = "" + ChatColor.GREEN;
                break;
            case ("BLUE"):
                color = "" + ChatColor.BLUE;
                break;
            case ("YELLOW"):
                color = "" + ChatColor.YELLOW;
                break;
            default:
                color = "" + ChatColor.WHITE;
                break;
        }
        return color;
    }

}
