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
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Terrain;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.TsukasaSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Tsukasa extends Mob {

    {
        spriteClass = TsukasaSprite.class;

        HP = HT = 79;
        defenseSkill = 20;
        EXP = 10;
        maxLvl = 27;

        properties.add(Property.YOKAI);

        loot = new PotionOfHealing();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(8, 13);
    }

    @Override
    public int attackSkill(Char target) {
        return 25;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void damage(int dmg, Object src) {
        int waterCells = 0;
        for (int i : PathFinder.NEIGHBOURS9) {
            if (Dungeon.level.map[pos + i] == Terrain.DOOR
                    || Dungeon.level.map[pos + i] == Terrain.OPEN_DOOR
                    || Dungeon.level.map[pos + i] == Terrain.LOCKED_DOOR) {
                waterCells++;
            }
        }
        if (waterCells > 0) dmg = Math.round(dmg * (2 - waterCells)*0.25f);

        super.damage(dmg, src);
    }
}