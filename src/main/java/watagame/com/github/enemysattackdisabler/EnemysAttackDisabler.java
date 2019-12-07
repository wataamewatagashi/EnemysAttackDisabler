package watagame.com.github.enemysattackdisabler;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import watagame.com.github.enemysattackdisabler.listener.DamageListener;

public final class EnemysAttackDisabler extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new DamageListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HandlerList.unregisterAll(this);
    }
}
