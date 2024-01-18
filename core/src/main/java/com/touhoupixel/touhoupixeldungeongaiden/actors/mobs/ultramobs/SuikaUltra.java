package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.SuperHard;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Suika;
import com.touhoupixel.touhoupixeldungeongaiden.items.Gold;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SuikaSprite;
import com.watabou.utils.Random;

public class SuikaUltra extends Suika {

    {
        spriteClass = SuikaSprite.class;

        HP = HT = 6; //gitan mamel minus 6HP
        defenseSkill = 35;
        EXP = 19;
        maxLvl = 47;

        state = WANDERING;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Gold.class;
        lootChance = 1f;
    }

    @Override
    protected boolean act() {
        boolean result = super.act();
        if (buff(SuperHard.class) == null) {
            Buff.prolong(this, SuperHard.class, SuperHard.DURATION * 10000f);
        }
        if (buff(DoubleSpeed.class) == null) {
            Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 10000f);
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(28, 33);
    }

    @Override
    public int attackSkill(Char target) {
        return 58;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}