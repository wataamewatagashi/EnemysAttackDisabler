package watagame.com.github.enemysattackdisabler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
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
        //NBTEditor.set(event.getEntity(), (byte)0, ead.attackable);
        event.getEntity().getPersistentDataContainer().set(ead.attackable, PersistentDataType.BYTE, (byte)0);
    }
}
