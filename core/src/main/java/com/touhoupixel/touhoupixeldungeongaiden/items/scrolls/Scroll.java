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
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.ItemStatusHandler;
import com.touhoupixel.touhoupixeldungeongaiden.journal.Catalog;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.HeroSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public abstract class Scroll extends Item {

	public static final String AC_READ = "READ";

	protected static final float TIME_TO_READ = 1f;

	private static final LinkedHashMap<String, Integer> runes = new LinkedHashMap<String, Integer>() {
		{
			put("KAUNAN", ItemSpriteSheet.SCROLL_KAUNAN);
			put("SOWILO", ItemSpriteSheet.SCROLL_SOWILO);
			put("LAGUZ", ItemSpriteSheet.SCROLL_LAGUZ);
			put("YNGVI", ItemSpriteSheet.SCROLL_YNGVI);
			put("GYFU", ItemSpriteSheet.SCROLL_GYFU);
			put("RAIDO", ItemSpriteSheet.SCROLL_RAIDO);
			put("ISAZ", ItemSpriteSheet.SCROLL_ISAZ);
			put("MANNAZ", ItemSpriteSheet.SCROLL_MANNAZ);
			put("NAUDIZ", ItemSpriteSheet.SCROLL_NAUDIZ);
			put("BERKANAN", ItemSpriteSheet.SCROLL_BERKANAN);
			put("ODAL", ItemSpriteSheet.SCROLL_ODAL);
			put("TIWAZ", ItemSpriteSheet.SCROLL_TIWAZ);
			put("GENSOKYO", ItemSpriteSheet.SCROLL_GENSOKYO);
			put("ZUN", ItemSpriteSheet.SCROLL_ZUN);
		}
	};

	protected static ItemStatusHandler<Scroll> handler;

	protected String rune;

	{
		stackable = true;
		defaultAction = AC_READ;
	}

	@SuppressWarnings("unchecked")
	public static void initLabels() {
		handler = new ItemStatusHandler<>((Class<? extends Scroll>[]) Generator.Category.SCROLL.classes, runes);
	}

	public static void save(Bundle bundle) {
		handler.save(bundle);
	}

	public static void saveSelectively(Bundle bundle, ArrayList<Item> items) {
		ArrayList<Class<? extends Item>> classes = new ArrayList<>();
		for (Item i : items) {
			if (i instanceof Scroll) {
				if (!classes.contains(i.getClass())) {
					classes.add(i.getClass());
				}
			}
		}
		handler.saveClassesSelectively(bundle, classes);
	}

	@SuppressWarnings("unchecked")
	public static void restore(Bundle bundle) {
		handler = new ItemStatusHandler<>((Class<? extends Scroll>[]) Generator.Category.SCROLL.classes, runes, bundle);
	}

	public Scroll() {
		super();
		reset();
	}

	//anonymous scrolls are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;

	public void anonymize() {
		if (!isKnown()) image = ItemSpriteSheet.SCROLL_HOLDER;
		anonymous = true;
	}


	@Override
	public void reset() {
		super.reset();
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			rune = handler.label(this);
		}
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (Dungeon.heroine.buff(Onigiri.class) == null) {
			actions.add(AC_READ);
		}
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals(AC_READ)) {

			if (heroine.buff(Blindness.class) != null) {
				GLog.w(Messages.get(this, "blinded"));
			} else if (heroine.buff(Silence.class) != null) {
				GLog.w(Messages.get(this, "silence"));
			} else {
				curUser = heroine;
				curItem = detach(heroine.belongings.backpack);
				doRead();
			}
		}
	}

	public abstract void doRead();

	protected void readAnimation() {
		Invisibility.dispel();
		curUser.spend(TIME_TO_READ);
		curUser.busy();
		((HeroSprite) curUser.sprite).read();
	}

	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown(this));
	}

	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
				updateQuickslot();
			}

			if (Dungeon.heroine.isAlive()) {
				Catalog.setSeen(getClass());
			}
		}
	}

	@Override
	public Item identify(boolean byHero) {
		super.identify(byHero);

		if (!isKnown()) {
			setKnown();
		}
		return this;
	}

	@Override
	public String name() {
		return isKnown() ? super.name() : Messages.get(this, rune);
	}

	@Override
	public String info() {
		return isKnown() ?
				desc() :
				Messages.get(this, "unknown_desc");
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return isKnown();
	}

	public static HashSet<Class<? extends Scroll>> getKnown() {
		return handler.known();
	}

	public static HashSet<Class<? extends Scroll>> getUnknown() {
		return handler.unknown();
	}

	public static boolean allKnown() {
		return handler.known().size() == Generator.Category.SCROLL.classes.length;
	}

	@Override
	public int value() {
		return 30 * quantity;
	}

	@Override
	public int energyVal() {
		return 6 * quantity;
	}

	public static class PlaceHolder extends Scroll {

		{
			image = ItemSpriteSheet.SCROLL_HOLDER;
		}

		@Override
		public void doRead() {
		}

		@Override
		public String info() {
			return "";
		}
	}
}