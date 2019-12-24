package watagame.com.github.enemysattackdisabler.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import watagame.com.github.enemysattackdisabler.EnemysAttackDisabler;

public class DamageListener implements Listener {

    private final EnemysAttackDisabler ead;

    public DamageListener(EnemysAttackDisabler ead) {
        this.ead = ead;

        ead.getServer().getPluginManager().registerEvents(this, ead);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnemyAttack(EntityDamageByEntityEvent event) {
        if (!(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK )) return;
        Entity entity =  event.getDamager();
        PersistentDataContainer data =  entity.getPersistentDataContainer();
        if (!(data.has(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE))) return;
        if (data.get(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE).equals((byte)0)) {
            event.setCancelled(true);
//            ead.getLogger().info("AttackCancelled!!");
//            entity.getPersistentDataContainer().set(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE, (byte)1);
//        } else {
//            entity.getPersistentDataContainer().set(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE, (byte)0);
        }
    }
}
