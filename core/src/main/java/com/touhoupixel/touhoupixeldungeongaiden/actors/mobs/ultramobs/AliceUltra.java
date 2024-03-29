package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.AliceCurse;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Alice;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.AliceSprite;
import com.watabou.utils.Random;

public class AliceUltra extends Alice {

    {
        spriteClass = AliceSprite.class;

        HP = HT = 31;
        defenseSkill = 10;
        EXP = 4;
        maxLvl = 17;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new PotionOfHealing();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 10);
    }

    @Override
    public int attackSkill(Char target) {
        return 61;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0){
            new SummoningTrap().set(pos).activate();
            Buff.prolong(enemy, AliceCurse.class, AliceCurse.DURATION);
        }
        return damage;
    }
}