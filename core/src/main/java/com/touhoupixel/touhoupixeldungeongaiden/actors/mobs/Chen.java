package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Chill;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Frost;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ChenSprite;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Chen extends Mob {

    protected ArrayList<Class<? extends Buff>> harmfulBuffs = new ArrayList<>();

    {
        spriteClass = ChenSprite.class;

        HP = HT = 38;
        defenseSkill = 10;
        EXP = 4;
        maxLvl = 17;

        baseSpeed = 2f;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        harmfulBuffs.add( Chill.class );
        harmfulBuffs.add( Frost.class );

        loot = Generator.Category.MISSILE;
        lootChance = 0.15f;
    }

    @Override
    public void add( Buff buff ) {
        if (harmfulBuffs.contains( buff.getClass() )) {
            damage( Random.NormalIntRange( HT/2, HT * 3/5 ), buff );
        } else {
            super.add( buff );
        }
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(8, 10);
    }

    @Override
    public int attackSkill(Char target) {
        return 15;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}