package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Cirno;
import com.touhoupixel.touhoupixeldungeongaiden.items.Dewdrop;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.CirnoSprite;
import com.watabou.utils.Random;

public class CirnoUltra extends Cirno {

    {
        spriteClass = CirnoSprite.class;

        HP = HT = 27;
        defenseSkill = 7;
        EXP = 3;
        maxLvl = 15;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new Dewdrop();
        lootChance = 1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 6);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(2) == 0) {
            Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
        }
        return damage;
    }
}