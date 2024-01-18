package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Yuuka;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YuukaSprite;
import com.watabou.utils.Random;

public class YuukaUltra extends Yuuka {

    {
        spriteClass = YuukaSprite.class;

        HP = HT = 450; //todo
        defenseSkill = 0;
        EXP = 1;
        maxLvl = 27;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        baseSpeed = 0.2f;
    }

    @Override
    public int damageRoll() {
        return 1;
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public float attackDelay() { return super.attackDelay()*3f; }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            //todo
        }
        return damage;
    }
}