package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Patchouli;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.ExplosiveTrap;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.PatchouliSprite;
import com.watabou.utils.Random;

public class PatchouliUltra extends Patchouli {

    {
        spriteClass = PatchouliSprite.class;

        HP = HT = 47;
        defenseSkill = 15;
        EXP = 6;
        maxLvl = 22;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        //because of her explosion upon death, no loot
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 6);
    }

    @Override
    public int attackSkill(Char target) {
        return 71;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void die(Object cause) {
        super.die(cause);
        new ExplosiveTrap().set(this.pos).activate();
    }
}