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

package com.touhoupixel.touhoupixeldungeongaiden.items.potions;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Fire;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Burning;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Splash;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.ItemStatusHandler;
import com.touhoupixel.touhoupixeldungeongaiden.items.Recipe;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.touhoupixel.touhoupixeldungeongaiden.journal.Catalog;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Terrain;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Blindweed;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Dreamfoil;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Fadeleaf;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Firebloom;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Icecap;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Plant;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sorrowmoss;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Starflower;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Stormvine;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sungrass;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndBag;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndOptions;
import com.touhoupixel.touhoupixeldungeongaiden.windows.WndUseItem;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Potion extends Item {

	public static final String AC_DRINK = "DRINK";

	//used internally for potions that can be drunk or thrown
	public static final String AC_CHOOSE = "CHOOSE";

	private static final float TIME_TO_DRINK = 1f;
	protected boolean isHarmfulGasPotion = false; // for sanae's card "Danmaku ghost"

	private static final LinkedHashMap<String, Integer> colors = new LinkedHashMap<String, Integer>() {
		{
			put("crimson", ItemSpriteSheet.POTION_CRIMSON);
			put("amber", ItemSpriteSheet.POTION_AMBER);
			put("golden", ItemSpriteSheet.POTION_GOLDEN);
			put("jade", ItemSpriteSheet.POTION_JADE);
			put("turquoise", ItemSpriteSheet.POTION_TURQUOISE);
			put("azure", ItemSpriteSheet.POTION_AZURE);
			put("indigo", ItemSpriteSheet.POTION_INDIGO);
			put("magenta", ItemSpriteSheet.POTION_MAGENTA);
			put("bistre", ItemSpriteSheet.POTION_BISTRE);
			put("charcoal", ItemSpriteSheet.POTION_CHARCOAL);
			put("silver", ItemSpriteSheet.POTION_SILVER);
			put("ivory", ItemSpriteSheet.POTION_IVORY);
			put("pink", ItemSpriteSheet.POTION_PINK);
			put("yellow", ItemSpriteSheet.POTION_YELLOW);
			put("spectral", ItemSpriteSheet.POTION_SPECTRAL);
			put("green", ItemSpriteSheet.POTION_GREEN);
			put("greentea_crimson",ItemSpriteSheet.GREENTEA_POTION_CRIMSON);
			put("greentea_amber",ItemSpriteSheet.GREENTEA_POTION_AMBER);
			put("greentea_golden",ItemSpriteSheet.GREENTEA_POTION_GOLDEN);
			put("greentea_jade",ItemSpriteSheet.GREENTEA_POTION_JADE);
			put("greentea_turquoise",ItemSpriteSheet.GREENTEA_POTION_TURQUOISE);
			put("greentea_azure",ItemSpriteSheet.GREENTEA_POTION_AZURE);
			put("greentea_indigo",ItemSpriteSheet.GREENTEA_POTION_INDIGO);
			put("greentea_magenta",ItemSpriteSheet.GREENTEA_POTION_MAGENTA);
			//put("greentea_bistre",ItemSpriteSheet.GREENTEA_POTION_BISTRE);
			//put("greentea_charcoal",ItemSpriteSheet.GREENTEA_POTION_CHARCOAL);
			//put("greentea_silver",ItemSpriteSheet.GREENTEA_POTION_SILVER);
			//put("greentea_ivory",ItemSpriteSheet.GREENTEA_POTION_IVORY);
			//put("ggreentea_pink",ItemSpriteSheet.GREENTEA_POTION_PINK);
			//put("greentea_yellow",ItemSpriteSheet.GREENTEA_POTION_YELLOW);
			//put("greentea_spectral",ItemSpriteSheet.GREENTEA_POTION_SPECTRAL);
			//put("greentea_green",ItemSpriteSheet.GREENTEA_POTION_GREEN);
		}
	};

	private static final HashSet<Class<? extends Potion>> mustThrowPots = new HashSet<>();

	static {
		mustThrowPots.add(PotionOfToxicGas.class);
		mustThrowPots.add(PotionOfLiquidFlame.class);
		mustThrowPots.add(PotionOfParalyticGas.class);
		mustThrowPots.add(PotionOfFrost.class);
		//also all brews, hardcoded
	}

	private static final HashSet<Class<? extends Potion>> canThrowPots = new HashSet<>();

	static {
		canThrowPots.add(AlchemicalCatalyst.class);

		canThrowPots.add(PotionOfPurity.class);
		canThrowPots.add(PotionOfLevitation.class);

		//elixirs
		canThrowPots.add(ElixirOfHoneyedHealing.class);
	}

	protected static ItemStatusHandler<Potion> handler;

	protected String color;

	{
		stackable = true;
		defaultAction = AC_DRINK;
	}

	@SuppressWarnings("unchecked")
	public static void initColors() {
		handler = new ItemStatusHandler<>((Class<? extends Potion>[]) Generator.Category.POTION.classes, colors);
	}

	public static void save(Bundle bundle) {
		handler.save(bundle);
	}

	public static void saveSelectively(Bundle bundle, ArrayList<Item> items) {
		ArrayList<Class<? extends Item>> classes = new ArrayList<>();
		for (Item i : items) {
			if (i instanceof Potion) {
				if (!classes.contains(i.getClass())) {
					classes.add(i.getClass());
				}
			}
		}
		handler.saveClassesSelectively(bundle, classes);
	}

	@SuppressWarnings("unchecked")
	public static void restore(Bundle bundle) {
		handler = new ItemStatusHandler<>((Class<? extends Potion>[]) Generator.Category.POTION.classes, colors, bundle);
	}

	public Potion() {
		super();
		reset();
	}

	//anonymous potions are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;

	public void anonymize() {
		if (!isKnown()) image = ItemSpriteSheet.POTION_HOLDER;
		anonymous = true;
	}

	@Override
	public void reset() {
		super.reset();
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			color = handler.label(this);
		}
		setAction();
	}

	@Override
	public boolean collect(Bag container) {
		if (super.collect(container)) {
			setAction();
			return true;
		} else {
			return false;
		}
	}

	public void setAction() {
		if (isKnown() && mustThrowPots.contains(this.getClass())) {
			defaultAction = AC_THROW;
		} else if (isKnown() && canThrowPots.contains(this.getClass())) {
			defaultAction = AC_CHOOSE;
		} else {
			defaultAction = AC_DRINK;
		}
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (Dungeon.heroine.buff(Onigiri.class) == null) {
			actions.add(AC_DRINK);
		}
		return actions;
	}

	@Override
	public void execute(final Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals(AC_CHOOSE)) {

			GameScene.show(new WndUseItem(null, this));

		} else if (action.equals(AC_DRINK)) {

			if (heroine.buff(Silence.class) != null) {
				GLog.w(Messages.get(this, "silence"));
			} else if (heroine.buff(PotionFreeze.class) != null) {
				GLog.w(Messages.get(this, "potionfreeze"));
			} else if (isKnown() && mustThrowPots.contains(getClass())) {

				GameScene.show(
						new WndOptions(new ItemSprite(this),
								Messages.get(Potion.class, "harmful"),
								Messages.get(Potion.class, "sure_drink"),
								Messages.get(Potion.class, "yes"), Messages.get(Potion.class, "no")) {
							@Override
							protected void onSelect(int index) {
								if (index == 0) {
									drink(heroine);
								}
							}
						}
				);

			} else {
				drink(heroine);
			}
		}
	}

	@Override
	public void doThrow(final Hero heroine) {

		if (isKnown()
				&& !mustThrowPots.contains(this.getClass())
				&& !canThrowPots.contains(this.getClass())) {

			GameScene.show(
					new WndOptions(new ItemSprite(this),
							Messages.get(Potion.class, "beneficial"),
							Messages.get(Potion.class, "sure_throw"),
							Messages.get(Potion.class, "yes"), Messages.get(Potion.class, "no")) {
						@Override
						protected void onSelect(int index) {
							if (index == 0) {
								Potion.super.doThrow(heroine);
							}
						}
					}
			);

		} else {
			super.doThrow(heroine);
		}
	}

	public void drink(Hero heroine) {

		detach(heroine.belongings.backpack);

		heroine.spend(TIME_TO_DRINK);
		heroine.busy();
		apply(heroine);

		Sample.INSTANCE.play(Assets.Sounds.DRINK);

		heroine.sprite.operate(heroine.pos);
	}

	@Override
	protected void onThrow(int cell) {
		if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {

			super.onThrow(cell);

		} else {

			Dungeon.level.pressCell(cell);
			shatter(cell);

		}
	}

	public void apply(Hero heroine) {
		shatter(heroine.pos);
	}

	public void shatter(int cell) {
		if (Dungeon.level.heroFOV[cell]) {
			GLog.i(Messages.get(Potion.class, "shatter"));
			Sample.INSTANCE.play(Assets.Sounds.SHATTER);
			splash(cell);
		}
	}

	@Override
	public void cast(final Hero user, int dst) {
		super.cast(user, dst);
	}

	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown(this));
	}

	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
				updateQuickslot();
				Potion p = Dungeon.heroine.belongings.getItem(getClass());
				if (p != null) p.setAction();
			}
		}

		if (Dungeon.heroine.isAlive()) {
			Catalog.setSeen(getClass());
		}
	}
	
	@Override
	public Item identify( boolean byHero ) {
		super.identify(byHero);

		if (!isKnown()) {
			setKnown();
		}
		return this;
	}
	
	@Override
	public String name() {
		return isKnown() ? super.name() : Messages.get(this, color);
	}
	
	@Override
	public String info() {
		return isKnown() ? desc() : Messages.get(this, "unknown_desc");
	}
	
	@Override
	public boolean isIdentified() {
		return isKnown();
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	public static HashSet<Class<? extends Potion>> getKnown() {
		return handler.known();
	}
	
	public static HashSet<Class<? extends Potion>> getUnknown() {
		return handler.unknown();
	}
	
	public static boolean allKnown() {
		return handler.known().size() == Generator.Category.POTION.classes.length;
	}
	
	protected int splashColor(){
		return anonymous ? 0x00AAFF : ItemSprite.pick( image, 5, 9 );
	}
	
	protected void splash( int cell ) {

		Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
		if (fire != null)
			fire.clear( cell );

		final int color = splashColor();

		Char ch = Actor.findChar(cell);
		if (ch != null && ch.alignment == Char.Alignment.ALLY) {
			Buff.detach(ch, Burning.class);
			Splash.at( ch.sprite.center(), color, 5 );
		} else {
			Splash.at( cell, color, 5 );
		}
	}

	@Override
	public int value() {
		return 30 * quantity;
	}

	@Override
	public int energyVal() {
		return 6 * quantity;
	}

	public static class PlaceHolder extends Potion {
		
		{
			image = ItemSpriteSheet.POTION_HOLDER;
		}
		
		@Override
		public String info() {
			return "";
		}
	}
}
