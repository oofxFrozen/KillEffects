package help.me;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class lootboxCmd implements CommandExecutor {

    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        Location location = player.getLocation();
        location.getBlock().setType(Material.CHEST);
        Block lootbox = location.getBlock();
        lootbox.setMetadata("lootbox", new FixedMetadataValue(Me.getInstance(), "lootbox"));
        Me.getInstance().getConfig().set("Lootboxes." + location + ".delay", true);
        return true;
    }
}
