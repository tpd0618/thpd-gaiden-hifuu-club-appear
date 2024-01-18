package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Clownpiece;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ClownpieceSprite;
import com.watabou.utils.Random;

public class ClownpieceUltra extends Clownpiece {

    {
        spriteClass = ClownpieceSprite.class;

        HP = HT = 167;
        defenseSkill = 35;
        EXP = 19;
        maxLvl = 47;

        properties.add(Property.WARP);

        loot = Generator.Category.TALISMAN;
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(15, 20);
    }

    @Override
    public int attackSkill(Char target) {
        return 74;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            //todo
        }
        return damage;
    }
}