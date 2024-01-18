package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Youmu;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.SpearheadHerb;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YoumuSprite;
import com.watabou.utils.Random;

public class YoumuUltra extends Youmu {

    {
        spriteClass = YoumuSprite.class;

        HP = HT = 217;
        defenseSkill = 32;
        EXP = 15;
        maxLvl = 45;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpearheadHerb();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(24, 30);
    }

    @Override
    public int attackSkill(Char target) {
        return 77;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            enemy.damage(15, this);
        }
        return damage;
    }
}