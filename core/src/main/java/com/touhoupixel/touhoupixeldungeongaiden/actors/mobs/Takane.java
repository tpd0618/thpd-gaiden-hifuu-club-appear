package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.BalanceBreak;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeongaiden.items.spells.AquaBlast;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.GeyserTrap;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.TakaneSprite;
import com.watabou.utils.Random;

public class Takane extends Mob {

    {
        spriteClass = TakaneSprite.class;

        HP = HT = 59;
        defenseSkill = 17;
        EXP = 8;
        maxLvl = 25;

        properties.add(Property.YOKAI);

        loot = new AquaBlast();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(7, 11);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        new GeyserTrap().set(this.pos).activate();
        return damage;
    }
}