package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Sumireko;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.DisarmingTrap;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SumirekoSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

public class SumirekoUltra extends Sumireko {

    {
        spriteClass = SumirekoSprite.class;

        HP = HT = 187;
        defenseSkill = 37;
        EXP = 22;
        maxLvl = 75;

        properties.add(Property.HUMAN);

        loot = new ScrollOfTeleportation();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(31, 37);
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
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(7) == 0) {
            GLog.n( Messages.get(this, "disarm") );
            new DisarmingTrap().set(enemy.pos).activate();
        }
        return damage;
    }
}