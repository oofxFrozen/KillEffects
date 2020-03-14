package help.me;

import org.bukkit.plugin.java.JavaPlugin;

public final class Me extends JavaPlugin {

    public static Me instance;
    public static Me getInstance() {
        return instance;
    }
    killEffects killEffects = new killEffects();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(killEffects, this);
        getLogger().info("PLEASE WORK");
        instance = this;
        getCommand("ke").setExecutor(new keCmd());
        loadConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        if (getConfig().get("Numbers.counter") == null) {
            getConfig().set("Numbers.counter", 1);
        }
        saveConfig();
    }
}
