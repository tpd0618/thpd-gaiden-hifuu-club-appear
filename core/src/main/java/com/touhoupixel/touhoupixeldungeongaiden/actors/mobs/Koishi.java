package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.exotic.PotionOfReverseYingYang;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KoishiSprite;
import com.watabou.utils.Random;

public class Koishi extends Mob {

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

        loot = new PotionOfReverseYingYang();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(36, 41);
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