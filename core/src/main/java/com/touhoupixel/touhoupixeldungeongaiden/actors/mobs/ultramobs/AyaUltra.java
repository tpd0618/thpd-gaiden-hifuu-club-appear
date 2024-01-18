package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Aya;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfDoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.AyaSprite;
import com.watabou.utils.Random;

public class AyaUltra extends Aya {

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
        return 71;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}