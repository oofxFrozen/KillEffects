package help.me;

import org.bukkit.plugin.java.JavaPlugin;

public final class Me extends JavaPlugin {

    public static Me instance;
    public static Me getInstance() {
        return instance;
    }
    killEffects killEffects = new killEffects();
    LootBoxes lootBoxes = new LootBoxes();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(killEffects, this);
        this.getServer().getPluginManager().registerEvents(lootBoxes, this);
        getLogger().info("PLEASE WORK");
        instance = this;
        getCommand("ke").setExecutor(new keCmd());
        getCommand("keys").setExecutor(new keysCmd());
        getCommand("lootbox").setExecutor(new lootboxCmd());
        loadConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
