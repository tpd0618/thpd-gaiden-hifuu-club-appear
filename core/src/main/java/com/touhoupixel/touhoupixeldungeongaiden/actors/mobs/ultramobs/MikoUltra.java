package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Miko;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MikoSprite;
import com.watabou.utils.Random;

public class MikoUltra extends Miko {

    {
        spriteClass = MikoSprite.class;

        HP = HT = 78;
        defenseSkill = 22;
        EXP = 14;
        maxLvl = 30;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new LifeFragment();
        lootChance = 0.08f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 17);
    }

    @Override
    public int attackSkill(Char target) {
        return 85;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            //todo
        }
        return damage;
    }
}