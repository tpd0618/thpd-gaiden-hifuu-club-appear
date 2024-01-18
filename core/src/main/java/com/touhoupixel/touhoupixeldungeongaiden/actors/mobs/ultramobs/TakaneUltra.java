package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Takane;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfPowerUp;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.TakaneSprite;
import com.watabou.utils.Random;

public class TakaneUltra extends Takane {

    {
        spriteClass = TakaneSprite.class;

        HP = HT = 59;
        defenseSkill = 17;
        EXP = 8;
        maxLvl = 25;

        properties.add(Property.YOKAI);

        loot = new PotionOfPowerUp();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(7, 11);
    }

    @Override
    public int attackSkill(Char target) {
        return 22;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        //todo
        return damage;
    }
}