package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Nazrin;
import com.touhoupixel.touhoupixeldungeongaiden.items.Gold;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.NazrinSprite;
import com.watabou.utils.Random;

public class NazrinUltra extends Nazrin {

    {
        spriteClass = NazrinSprite.class;

        HP = HT = 10;
        defenseSkill = 2;
        EXP = 1;
        maxLvl = 10;

        properties.add(Property.ANIMAL);

        loot = Gold.class;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Dungeon.gold > 200 ? Random.NormalIntRange(3, 4) :
                Random.NormalIntRange(2, 3);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}