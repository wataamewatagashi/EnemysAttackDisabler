package watagame.com.github.enemysattackdisabler;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import org.bukkit.persistence.PersistentDataType;

public class ChangeAttackableSkill extends SkillMechanic implements ITargetedEntitySkill {

    private final  EnemysAttackDisabler ead;
    private boolean attackable;

    public ChangeAttackableSkill(EnemysAttackDisabler ead, String skill, MythicLineConfig mlc) {
        super(skill, mlc);
        this.attackable = mlc.getBoolean(new String[]{"attackable","a"},true);

        this.ead = ead;
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (target == null) return false;
        if (!(target.isLiving()) || !(target.isPlayer() || target.isCreature() || target.isAnimal())) {
            return false;
        }
        Byte ba = attackable? (byte)1:(byte)0;
        //NBTEditor.set(target.getBukkitEntity(),  ba, ead.attackable);
        target.getBukkitEntity().getPersistentDataContainer().set(ead.attackable, PersistentDataType.BYTE, ba);
        ead.getLogger().info("setAttackable! mob:" + target.getBukkitEntity().getType().toString() + "setAttackable" + ba.toString());
        return true;
    }
}
