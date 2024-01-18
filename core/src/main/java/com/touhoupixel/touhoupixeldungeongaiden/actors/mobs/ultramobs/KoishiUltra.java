package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Koishi;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KoishiSprite;
import com.watabou.utils.Random;

public class KoishiUltra extends Koishi {

    {
        spriteClass = KoishiSprite.class;

        HP = HT = 249;
        defenseSkill = 32;
        EXP = 15;
        maxLvl = 75;

        state = WANDERING;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new PotionOfInvisibility();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(36, 41);
    }

    @Override
    public int attackSkill(Char target) {
        return 82;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}