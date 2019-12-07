package watagame.com.github.enemysattackdisabler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import watagame.com.github.enemysattackdisabler.EnemysAttackDisabler;

public class DamageListener implements Listener {

    private final EnemysAttackDisabler ead;

    public DamageListener(EnemysAttackDisabler ead) {
        this.ead = ead;

        ead.getServer().getPluginManager().registerEvents(this, ead);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnemyAttack(EntityDamageByEntityEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            event.setCancelled(true);
            //ead.getLogger().info("AttackCancelled!!");
        }
    }
}
