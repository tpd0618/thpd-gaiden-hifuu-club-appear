package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Seiran;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SeiranSprite;
import com.watabou.utils.Random;

public class SeiranUltra extends Seiran {

    {
        spriteClass = SeiranSprite.class;

        HP = HT = 269;
        defenseSkill = 37;
        EXP = 20;
        maxLvl = 50;

        properties.add(Property.WARP);

        loot = Generator.Category.HERB;
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(30, 34);
    }

    @Override
    public int attackSkill(Char target) {
        return 53;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        if (Dungeon.heroine.belongings.weapon() instanceof MeleeWeapon) {
            //todo
        }
        return super.defenseProc(enemy, damage);
    }
}