package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Meiling;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MeilingSprite;
import com.watabou.utils.Random;

public class MeilingUltra extends Meiling {

    {
        spriteClass = MeilingSprite.class;

        HP = HT = 54;
        defenseSkill = 15;
        EXP = 6;
        maxLvl = 22;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Generator.Category.TALISMAN;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 8);
    }

    @Override
    public int attackSkill(Char target) {
        return 64;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
} //see mob.attackproc