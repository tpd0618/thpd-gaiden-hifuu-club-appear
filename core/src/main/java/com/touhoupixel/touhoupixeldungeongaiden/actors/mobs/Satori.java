package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ZeroDexterity;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HeavenSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SatoriSprite;
import com.watabou.utils.Random;

public class Satori extends Mob {

    {
        spriteClass = SatoriSprite.class;

        HP = HT = 259;
        defenseSkill = 64;
        EXP = 15;
        maxLvl = 75;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new PotionOfMindVision();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(32, 36);
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