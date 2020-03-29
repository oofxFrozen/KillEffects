package help.me;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class customInventory implements Listener {
    public static ItemStack BONE_MEAL;
    public static ItemStack NONE;
    public static Inventory inventory;

    public void keInventory (Player p) {
        inventory = Me.getInstance().getServer().createInventory(null, 27, ChatColor.AQUA + "Kill Effects");
        NONE = new ItemStack(Material.BARRIER);
        ItemMeta meta = NONE.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Remove All Effects");
        inventory.addItem(new ItemStack(Material.BARRIER));
        inventory.addItem(new ItemStack(Material.SPECKLED_MELON));
        BONE_MEAL = new ItemStack(Material.INK_SACK);
        BONE_MEAL.setDurability((short) 15);
        inventory.addItem(BONE_MEAL);
        inventory.addItem(new ItemStack(Material.SLIME_BALL));
        inventory.addItem(new ItemStack(Material.BLAZE_POWDER));
        inventory.addItem(new ItemStack(Material.CACTUS));
        inventory.addItem(new ItemStack(Material.BLAZE_ROD));
        inventory.addItem(new ItemStack(Material.BONE));
        inventory.addItem(new ItemStack(Material.SNOW_BALL));
        inventory.addItem(new ItemStack(Material.SKULL_ITEM));
        inventory.addItem(new ItemStack(Material.REDSTONE));
        p.openInventory(inventory);
    }
}