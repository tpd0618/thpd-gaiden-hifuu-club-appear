package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfToxicGas;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YamameSprite;
import com.watabou.utils.Random;

public class Yamame extends Mob {

    {
        spriteClass = YamameSprite.class;

        HP = HT = 22;
        defenseSkill = 2;
        EXP = 2;
        maxLvl = 10;

        properties.add(Property.YOKAI);

        loot = new PotionOfToxicGas();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(1, 1);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.affect(hero, Poison.class).set(Math.round(Statistics.upgradesUsed/5f));
        }
        return damage;
    }
}