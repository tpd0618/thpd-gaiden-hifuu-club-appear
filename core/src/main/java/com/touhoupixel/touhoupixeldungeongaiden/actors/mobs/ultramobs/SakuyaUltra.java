package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.FutureSight;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Sakuya;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SakuyaSprite;
import com.watabou.utils.Random;

public class SakuyaUltra extends Sakuya {

    {
        spriteClass = SakuyaSprite.class;

        HP = HT = 40;
        defenseSkill = 15;
        EXP = 7;
        maxLvl = 22;

        properties.add(Property.HUMAN);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new LifeFragment();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 8);
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
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Dungeon.heroine.buff(FutureSight.class) == null && Random.Int(3) == 0) {
            Buff.prolong(enemy, FutureSight.class, FutureSight.DURATION);
        }
        return damage;
    }
}