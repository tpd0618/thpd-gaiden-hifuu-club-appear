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

package com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.FumoLover;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class IdolStick extends MeleeWeapon {

    {
        image = ItemSpriteSheet.IDOL_STICK;
        hitSound = Assets.Sounds.HIT_CRUSH;
        hitSoundPitch = 1f;

        tier = 4;
    }

    @Override
    public int WarpFactor(Char owner) {
        return 1;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if (isSymmetric(attacker.HP)) {
            Buff.prolong(attacker, FumoLover.class, FumoLover.DURATION);
        }
        return super.proc(attacker, defender, damage);
    }

    public boolean isSymmetric(int number) {
        String numStr = Integer.toString(number);
        int length = numStr.length();

        for (int i = 0; i < length / 2; i++) {
            if (numStr.charAt(i) != numStr.charAt(length - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}