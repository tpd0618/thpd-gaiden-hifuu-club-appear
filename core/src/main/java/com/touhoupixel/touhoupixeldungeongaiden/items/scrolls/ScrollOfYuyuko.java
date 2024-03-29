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

package com.touhoupixel.touhoupixeldungeongaiden.items.scrolls;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Identification;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Food;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.Talisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Plant;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

public class ScrollOfYuyuko extends InventoryScroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_FATE;

		bones = true;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return !item.unique && !(item instanceof Plant.Seed) && !(item instanceof MissileWeapon) && !(item instanceof Herb) && !(item instanceof Talisman);
	}

	@Override
	protected void onItemSelected( Item item ) {
		item.detach(Dungeon.heroine.belongings.backpack);
		Dungeon.level.drop(new Food(), curUser.pos).sprite.drop();
		if (item instanceof Weapon && item.isEquipped(Dungeon.heroine)){
			Dungeon.heroine.belongings.weapon = null;
		}
		if (item instanceof Armor && item.isEquipped(Dungeon.heroine)){
			Dungeon.heroine.belongings.armor = null;
		}
		updateQuickslot();
	}

	@Override
	public int value() {
		return isKnown() ? 30 * quantity : super.value();
	}
}
