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
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class KoishiDagger extends MeleeWeapon {
	
	{
		image = ItemSpriteSheet.THROWING_KNIFE;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.1f;
		
		bones = false;
	}
	
	@Override
	public int damageRoll(Char owner) {
		if (owner instanceof Hero) {
			Hero heroine = (Hero)owner;
			Char enemy = heroine.enemy();
			if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(heroine)) {
				int diff = max() - min();
				int damage = augment.damageFactor(Random.NormalIntRange(
						min() + Math.round(diff*0.75f),
						max()));
				int exStr = heroine.STR();
				if (exStr > 0) {
					damage += Random.IntRange(0, exStr);
				}
				return damage;
			}
		}
		return super.damageRoll(owner);
	}

	@Override
	public int min(int lvl) {
		return 4+Dungeon.heroine.lvl+Dungeon.heroine.STR;
	}//all weapons must need this

	@Override
	public int max(int lvl) {
		return 5+Dungeon.heroine.lvl*2+Dungeon.heroine.STR*2;
	}//all weapons must need this
}