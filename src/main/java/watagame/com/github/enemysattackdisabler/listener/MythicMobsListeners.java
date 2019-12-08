package watagame.com.github.enemysattackdisabler.listener;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import watagame.com.github.enemysattackdisabler.ChangeAttackableSkill;
import watagame.com.github.enemysattackdisabler.EnemysAttackDisabler;

public class MythicMobsListeners implements Listener {

    private final EnemysAttackDisabler ead;

    public MythicMobsListeners(EnemysAttackDisabler ead) {
        this.ead = ead;
        ead.getServer().getPluginManager().registerEvents(this, ead);
    }

    @EventHandler
    public void onMythicMobsMechanicsLoad(MythicMechanicLoadEvent event) {
        if (event.getMechanicName().toLowerCase().equals("attackable")) {
            SkillMechanic skill = new ChangeAttackableSkill(ead,event.getContainer().getConfigLine(), event.getConfig());
            if (skill != null) event.register(skill);
        }
    }
}
