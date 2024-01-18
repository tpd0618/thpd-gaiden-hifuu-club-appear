package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Yukari;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfStamina;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YukariSprite;
import com.watabou.utils.Random;

public class YukariUltra extends Yukari {

    {
        spriteClass = YukariSprite.class;

        HP = HT = 450;
        defenseSkill = 40;
        EXP = 25;
        maxLvl = 99;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        flying = true;

        loot = new PotionOfStamina();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(35, 45);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
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