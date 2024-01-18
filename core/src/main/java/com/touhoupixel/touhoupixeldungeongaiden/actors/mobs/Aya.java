package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfDoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.AyaSprite;
import com.watabou.utils.Random;

public class Aya extends Mob {

    {
        spriteClass = AyaSprite.class;

        HP = HT = 62;
        defenseSkill = 17;
        EXP = 9;
        maxLvl = 25;

        baseSpeed = 5f;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new PotionOfDoubleSpeed();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(6, 10);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}