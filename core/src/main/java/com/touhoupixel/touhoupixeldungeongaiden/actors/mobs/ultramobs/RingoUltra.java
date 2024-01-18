package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Ringo;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.RingoSprite;
import com.watabou.utils.Random;

public class RingoUltra extends Ringo {

    {
        spriteClass = RingoSprite.class;

        HP = HT = 16;
        defenseSkill = 5;
        EXP = 2;
        maxLvl = 12;

        properties.add(Property.WARP);

        baseSpeed = 2f;

        //todo
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(4, 5);
    }

    @Override
    public int attackSkill(Char target) {
        return 52;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment){
            Statistics.power += 10;
        }
        return damage;
    }
}