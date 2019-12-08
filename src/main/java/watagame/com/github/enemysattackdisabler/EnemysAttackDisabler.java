package watagame.com.github.enemysattackdisabler;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import watagame.com.github.enemysattackdisabler.listener.DamageListener;
import watagame.com.github.enemysattackdisabler.listener.MobSpawnListener;
import watagame.com.github.enemysattackdisabler.listener.MythicMobsListeners;

public final class EnemysAttackDisabler extends JavaPlugin {

    public String attackable = "Attackable";

    @Override
    public void onEnable() {
        // Plugin startup logic
        new DamageListener(this);
        new MobSpawnListener(this);
        if (getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            new MythicMobsListeners(this);
        } else {
            getLogger().warning("MythicMobs is missing");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HandlerList.unregisterAll(this);
    }
}
