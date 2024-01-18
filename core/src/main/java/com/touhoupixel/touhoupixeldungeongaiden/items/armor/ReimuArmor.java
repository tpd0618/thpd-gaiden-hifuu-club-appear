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

package com.touhoupixel.touhoupixeldungeongaiden.items.armor;

import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class ReimuArmor extends Armor {

	{
		image = ItemSpriteSheet.ARMOR_REIMU;

		bones = false; //Finding them in bones would be semi-frequent and disappointing.
	}

	//return resistance factor 0
	
	public ReimuArmor() {
		super( 1 );
	}

	@Override
	public int DRMax(int lvl) {
		int max = tier * (2 + lvl) + augment.defenseFactor(lvl);
		if (lvl > max) {
			return ((lvl - max) + 1) / 2;
		} else {
			return max;
		}
	}//all armors must need this

	@Override
	public int DRMin(int lvl) {
		int max = DRMax(lvl);
		if (lvl >= max) {
			return (lvl - max);
		} else {
			return lvl;
		}
	}//all armors must need this
}
