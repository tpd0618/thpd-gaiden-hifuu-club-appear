/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfExorcism;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.HinaSprite;
import com.watabou.utils.Random;

public class Hina extends Mob {

    {
        spriteClass = HinaSprite.class;

        HP = HT = 93;
        defenseSkill = 25;
        EXP = 13;
        maxLvl = 32;

        properties.add(Property.GOD);

        loot = new ScrollOfExorcism();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(13, 21);
    }

    @Override
    public int attackSkill(Char target) {
        return 30;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            new CursingTrap().set(enemy.pos).activate();
            Buff.prolong(enemy, HinaCurse.class, HinaCurse.DURATION);
        }
        return damage;
    }
}