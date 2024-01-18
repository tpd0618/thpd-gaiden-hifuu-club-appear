package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Koakuma;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KoakumaSprite;
import com.watabou.utils.Random;

public class KoakumaUltra extends Koakuma {

    {
        spriteClass = KoakumaSprite.class;

        HP = HT = 37;
        defenseSkill = 15;
        EXP = 6;
        maxLvl = 22;

        properties.add(Property.WARP);

        loot = Generator.Category.SCROLL;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Statistics.upgradesUsed/2, 1+Statistics.upgradesUsed/2);
    }

    @Override
    public int attackSkill(Char target) {
        return 57;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}