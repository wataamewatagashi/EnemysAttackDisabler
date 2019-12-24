package watagame.com.github.enemysattackdisabler;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ChangeAttackableSkill extends SkillMechanic implements ITargetedEntitySkill {

    private final  EnemysAttackDisabler ead;
    private String attackable;

    public ChangeAttackableSkill(EnemysAttackDisabler ead, String skill, MythicLineConfig mlc) {
        super(skill, mlc);
        this.attackable = mlc.getString(new String[]{"attackable","a"},"toggle");

        this.ead = ead;
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (target == null) return false;
        if (!(target.isLiving()) || !(target.isCreature() || target.isAnimal())) {
            return false;
        }
        Entity e = target.getBukkitEntity();
        PersistentDataContainer pdata = e.getPersistentDataContainer();

        Byte ba = 0;
       switch (attackable) {
           case "true" :
               ba = (byte)1;
               break;
           case "false" :
               ba = (byte)0;
               break;
           case "toggle" :
               if (!(pdata.has(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE))) break;
               ba = pdata.get(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE) == 0? (byte)1:(byte)0;
               break;
       }
        pdata.set(new NamespacedKey(ead, "atkb"), PersistentDataType.BYTE, ba);
        //ead.getLogger().info("setAttackable! mob:" + target.getBukkitEntity().getType().toString() + "setAttackable" + ba.toString());
        return true;
    }
}
