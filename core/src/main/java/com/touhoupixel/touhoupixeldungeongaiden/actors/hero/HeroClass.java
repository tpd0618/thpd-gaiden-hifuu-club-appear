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

package com.touhoupixel.touhoupixeldungeongaiden.actors.hero;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Challenges;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.items.GlassBottle;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.NitoChecker;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.ReimuArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.MagicalContainer;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.PotionBandolier;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.SpellcardHolder;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.HerbPouch;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.VelvetPouch;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Food;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.Miracle;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ReimuExorcismRod;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;

public enum HeroClass {

	PLAYERRENKO(),
	PLAYERHEARN;

	public void initHero(Hero heroine) {
		//mobs bestiary flavor text todo

		//test, put too many stuffs will trigger a certain bug

		//test, put too many stuffs will trigger a certain bug
		ThrowingKnife throwingKnife = new ThrowingKnife();
		throwingKnife.quantity(3).collect();
		Dungeon.quickslot.setSlot(2, throwingKnife);

		ReimuExorcismRod reimuExorcismRod = new ReimuExorcismRod();
		reimuExorcismRod.identify().collect();

		MarisaStaff staff;
		staff = new MarisaStaff(new WandOfMagicMissile());
		(heroine.belongings.weapon = staff).identify();
		heroine.belongings.weapon.activate(heroine);
		Dungeon.quickslot.setSlot(0, staff);

		Food food = new Food();
		food.collect();

		heroine.heroClass = this;

		Item i = new ReimuArmor().identify();
		if (!Challenges.isItemBlocked(i)) heroine.belongings.armor = (ReimuArmor) i;

		GlassBottle glassBottle = new GlassBottle();
		glassBottle.collect();
		Dungeon.quickslot.setSlot(1, glassBottle);

		Miracle miracle = new Miracle();
		miracle.identify().collect();

		NitoChecker nitoChecker = new NitoChecker();
		nitoChecker.collect();

		new MagicalHolster().collect();
		new PotionBandolier().collect();
		new SpellcardHolder().collect();
		new HerbPouch().collect();
		new VelvetPouch().collect();
		new MagicalContainer().collect();
	}

	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc() {
		return Messages.get(HeroClass.class, name() + "_desc");
	}

	public String spritesheet() {
		switch (this) {
			case PLAYERRENKO:
			default:
				return Assets.Sprites.PLAYERRENKO;
			case PLAYERHEARN:
				return Assets.Sprites.PLAYERHEARN;
		}
	}

	public String splashArt() {
		switch (this) {
			case PLAYERRENKO:
			default:
				return Assets.Splashes.PLAYERRENKO;
			case PLAYERHEARN:
				return Assets.Splashes.PLAYERHEARN;
		}
	}

	public boolean isUnlocked() {
		//no unlock system in THPD:reloaded!
		return true;
	}
}
