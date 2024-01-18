package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Yuugi;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YuugiSprite;
import com.watabou.utils.Random;

public class YuugiUltra extends Yuugi {

    {
        spriteClass = YuugiSprite.class;

        HP = HT = 248;
        defenseSkill = 35;
        EXP = 19;
        maxLvl = 47;

        properties.add(Property.WARP);

        loot = new PotionOfHealing();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(29, 34);
    }

    @Override
    public int attackSkill(Char target) {
        return 67;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Buff.prolong(this, SuperHard.class, SuperHard.DURATION/3f);
            //todo
        }
        return damage;
    }
}