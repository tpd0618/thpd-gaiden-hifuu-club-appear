package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Hatate;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.FlashingTrap;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.HatateSprite;
import com.watabou.utils.Random;

public class HatateUltra extends Hatate {

    {
        spriteClass = HatateSprite.class;

        HP = HT = 62;
        defenseSkill = 17;
        EXP = 9;
        maxLvl = 25;

        flying = true;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Generator.Category.SCROLL;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(8, 12);
    }

    @Override
    public int attackSkill(Char target) {
        return 69;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(6) == 0) {
            new FlashingTrap().set(enemy.pos).activate();
        }
        return damage;
    }
}