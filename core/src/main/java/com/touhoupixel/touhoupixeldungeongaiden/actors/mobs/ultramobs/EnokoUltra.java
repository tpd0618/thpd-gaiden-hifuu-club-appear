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

package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Enoko;
import com.touhoupixel.touhoupixeldungeongaiden.items.spells.ReclaimTrap;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.EnokoSprite;
import com.watabou.utils.Random;

public class EnokoUltra extends Enoko {

    {
        spriteClass = EnokoSprite.class;

        HP = HT = 267;
        defenseSkill = 35;
        EXP = 19;
        maxLvl = 47;

        properties.add(Property.YOKAI);

        loot = new ReclaimTrap();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Statistics.trap_act_count+1, Statistics.trap_act_count+5);
    }

    @Override
    public int attackSkill(Char target) {
        return 59;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}