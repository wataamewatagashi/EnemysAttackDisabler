package watagame.com.github.enemysattackdisabler.listener;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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
        Entity entity =  event.getEntity();
        //if (NBTEditor.getByte(event.getDamager(), ead.attackable) == (byte)0) {
        if (entity.getPersistentDataContainer().get(ead.attackable, PersistentDataType.BYTE) == 0) {
            event.setCancelled(true);
            ead.getLogger().info("AttackCancelled!!");
            entity.getPersistentDataContainer().set(ead.attackable, PersistentDataType.BYTE, (byte)1);
            //NBTEditor.set(event.getDamager(), (byte)1, ead.attackable);
        } else {
            //NBTEditor.set(event.getDamager(), (byte)0, ead.attackable);
            entity.getPersistentDataContainer().set(ead.attackable, PersistentDataType.BYTE, (byte)0);
        }
    }
}
