/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeongaiden.items.potions;

import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Drowsy;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;

public class PotionOfStrength extends Potion {

	{
		icon = ItemSpriteSheet.Icons.POTION_YINGYANG;
	}

	@Override
	public void apply(Hero heroine) {
		identify();
		if (heroine.MAX_STR == heroine.STR) {
			heroine.STR++;
			heroine.MAX_STR++;
			heroine.sprite.showStatus(CharSprite.POSITIVE, Messages.get(this, "msg_1"));
			GLog.p(Messages.get(this, "max_up"));
		} else {
			heroine.STR++;
			heroine.sprite.showStatus(CharSprite.POSITIVE, Messages.get(this, "msg_1"));
			GLog.p(Messages.get(this, "normal_up"));
		}
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}
