package watagame.com.github.enemysattackdisabler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import watagame.com.github.enemysattackdisabler.EnemysAttackDisabler;
import watagame.com.github.enemysattackdisabler.util.NBTEditor;

public class MobSpawnListener implements Listener {
    private final EnemysAttackDisabler ead;

    public MobSpawnListener(EnemysAttackDisabler ead) {
        this.ead = ead;

        ead.getServer().getPluginManager().registerEvents(this, ead);
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        NBTEditor.set(event.getEntity(), (byte)0, ead.attackable);
    }
}
