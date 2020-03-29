package help.me;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class keysCmd extends LootBoxes implements CommandExecutor {

    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.AQUA + "Your amount of keys: " + ChatColor.WHITE + getKeys(player));
        } else {
            Player currentPlayer = Bukkit.getServer().getPlayer(args[1]);
            int value = Integer.parseInt(args[2]);
            if (args[0].equals("set")) {
                setKeys(currentPlayer, value);
            }
            if (args[0].equals("add")) {
                addKeys(currentPlayer, value);
            }
        }
        return true;
    }
}
