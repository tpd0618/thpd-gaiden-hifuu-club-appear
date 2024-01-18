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
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.BlurryMagic;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class TurnaboutCloak extends MeleeWeapon {

    {
        image = ItemSpriteSheet.TURNABOUT_CLOAK;
        hitSound = Assets.Sounds.HIT_CRUSH;
        hitSoundPitch = 1f;
    }

    @Override
    public int YokaiFactor( Char owner ) {
        return 1;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if (Statistics.foodEaten % 2 == 1) {
            damage *= 1.4f;
            Buff.prolong(defender, BlurryMagic.class, BlurryMagic.DURATION);
        }
        return super.proc(attacker, defender, damage);
    }
}