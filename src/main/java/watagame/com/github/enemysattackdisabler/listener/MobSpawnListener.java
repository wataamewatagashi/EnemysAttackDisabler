package watagame.com.github.enemysattackdisabler.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import watagame.com.github.enemysattackdisabler.EnemysAttackDisabler;

public class MobSpawnListener implements Listener {
    private final EnemysAttackDisabler ead;

    public MobSpawnListener(EnemysAttackDisabler ead) {
        this.ead = ead;

        ead.getServer().getPluginManager().registerEvents(this, ead);
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        Entity ent = event.getEntity();
        PersistentDataContainer data = ent.getPersistentDataContainer();
        data.set(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE, (byte)0);
    }
}
