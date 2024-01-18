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
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfRandomLife;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfSuperUnlucky;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfYuyuko;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KoishiDagger;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;

public enum HeroClass {

	PLAYERRENKO(),
	PLAYERHEARN;

	public void initHero(Hero heroine) {
		//mobs bestiary flavor text todo

		//test, put too many stuffs will trigger a certain bug
		PotionOfSuperUnlucky sda = new PotionOfSuperUnlucky();
		sda.collect();

		PotionOfRandomLife sda2 = new PotionOfRandomLife();
		sda2.collect();

		KoishiDagger sda222 = new KoishiDagger();
		sda222.collect();

		KoishiDagger sda2223 = new KoishiDagger();
		sda2223.collect();

		KoishiDagger sda22233 = new KoishiDagger();
		sda22233.collect();

		ScrollOfYuyuko  asd = new ScrollOfYuyuko();
		asd.quantity(30).collect();
		//test, put too many stuffs will trigger a certain bug
		ThrowingKnife throwingKnife = new ThrowingKnife();
		throwingKnife.quantity(3).collect();
		Dungeon.quickslot.setSlot(2, throwingKnife);

		Food food = new Food();
		food.collect();

		heroine.heroClass = this;

		Item k = new KoishiDagger().identify();
		heroine.belongings.weapon = (KoishiDagger) k;

		Item i = new ReimuArmor().identify();
		heroine.belongings.armor = (ReimuArmor) i;

		GlassBottle glassBottle = new GlassBottle();
		glassBottle.collect();
		Dungeon.quickslot.setSlot(1, glassBottle);

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
