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

import com.touhoupixel.touhoupixeldungeongaiden.GamesInProgress;
import com.touhoupixel.touhoupixeldungeongaiden.items.EquipableItem;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.KindOfWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.KindofMisc;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfExorcism;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Iterator;

public class Belongings implements Iterable<Item> {

	private Hero owner;

	public static class Backpack extends Bag {
		{
			image = ItemSpriteSheet.POUCH;
		}
		public int capacity(){
			int cap = super.capacity();
			for (Item item : items){
				if (item instanceof Bag){
					cap++;
				}
			}
			return cap;
		}
	}

	public Backpack backpack;

	public Belongings( Hero owner ) {
		this.owner = owner;

		backpack = new Backpack();
		backpack.owner = owner;
	}

	public KindOfWeapon weapon = null;
	public Armor armor = null;
	public Artifact artifact = null;
	public KindofMisc misc = null;
	public Ring ring = null;

	//used when thrown weapons temporary become the current weapon
	public KindOfWeapon thrownWeapon = null;

	//*** these accessor methods are so that worn items can be affected by various effects/debuffs
	// we still want to access the raw equipped items in cases where effects should be ignored though,
	// such as when equipping something, showing an interface, or dealing with items from a dead hero

	public KindOfWeapon weapon(){
		//no point in lost invent check, if it's assigned it must be usable
		if (thrownWeapon != null) return thrownWeapon;

		return weapon;
	}

	public Armor armor(){
		return armor;
	}

	public Artifact artifact(){
		return artifact;
	}

	public KindofMisc misc(){
		return misc;
	}

	public Ring ring(){
		return ring;
	}

	// ***

	private static final String WEAPON		= "weapon";
	private static final String ARMOR		= "armor";
	private static final String ARTIFACT   = "artifact";
	private static final String MISC       = "misc";
	private static final String RING       = "ring";

	public void storeInBundle( Bundle bundle ) {

		backpack.storeInBundle( bundle );

		bundle.put( WEAPON, weapon );
		bundle.put( ARMOR, armor );
		bundle.put( ARTIFACT, artifact );
		bundle.put( MISC, misc );
		bundle.put( RING, ring );
	}

	public void restoreFromBundle( Bundle bundle ) {

		backpack.clear();
		backpack.restoreFromBundle( bundle );

		weapon = (KindOfWeapon) bundle.get(WEAPON);
		if (weapon() != null)       weapon().activate(owner);

		armor = (Armor)bundle.get( ARMOR );
		if (armor() != null)        armor().activate( owner );

		artifact = (Artifact) bundle.get(ARTIFACT);
		if (artifact() != null)     artifact().activate(owner);

		misc = (KindofMisc) bundle.get(MISC);
		if (misc() != null)         misc().activate( owner );

		ring = (Ring) bundle.get(RING);
		if (ring() != null)         ring().activate( owner );
	}

	public static void preview( GamesInProgress.Info info, Bundle bundle ) {
		if (bundle.contains( ARMOR )){
			Armor armor = ((Armor)bundle.get( ARMOR ));
			info.armorTier = armor.tier;
		} else {
			info.armorTier = 0;
		}
	}

	//ignores lost inventory debuff
	public ArrayList<Bag> getBags(){
		ArrayList<Bag> result = new ArrayList<>();

		result.add(backpack);

		for (Item i : this){
			if (i instanceof Bag){
				result.add((Bag)i);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public<T extends Item> T getItem( Class<T> itemClass ) {

		for (Item item : this) {
			if (itemClass.isInstance( item )) {
				return (T) item;
			}
		}

		return null;
	}

	public<T extends Item> ArrayList<T> getAllItems( Class<T> itemClass ) {
		ArrayList<T> result = new ArrayList<>();

		for (Item item : this) {
			if (itemClass.isInstance( item )) {
				result.add((T) item);
			}
		}

		return result;
	}

	public boolean contains( Item contains ){

		for (Item item : this) {
			if (contains == item) {
				return true;
			}
		}

		return false;
	}

	public Item getSimilar( Item similar ){

		for (Item item : this) {
			if (similar != item && similar.isSimilar(item)) {
				return item;
			}
		}

		return null;
	}

	public ArrayList<Item> getAllSimilar( Item similar ){
		ArrayList<Item> result = new ArrayList<>();

		for (Item item : this) {
			if (item != similar && similar.isSimilar(item)) {
				result.add(item);
			}
		}

		return result;
	}

	//triggers when a run ends, so ignores lost inventory effects
	public void identify() {
		for (Item item : this) {
			item.identify();
		}
	}

	public void observe() {
		if (weapon() != null) {
			weapon().identify();
		}
		if (armor() != null) {
			armor().identify();
		}
		if (artifact() != null) {
			artifact().identify();
		}
		if (misc() != null) {
			misc().identify();
		}
		if (ring() != null) {
			ring().identify();
		}
		for (Item item : backpack) {
			if (item instanceof EquipableItem) {
				item.cursedKnown = true;
			}
		}
		Item.updateQuickslot();
	}

	public void uncurseEquipped() {
		ScrollOfExorcism.uncurse( owner, armor(), weapon(), artifact(), misc(), ring());
	}

	public Item randomUnequipped() {
		return Random.element( backpack.items );
	}

	public int charge( float charge ) {
		int count = 0;
		return count;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ItemIterator();
	}

	private class ItemIterator implements Iterator<Item> {

		private int index = 0;

		private Iterator<Item> backpackIterator = backpack.iterator();

		private Item[] equipped = {weapon, armor, artifact, misc, ring};
		private int backpackIndex = equipped.length;

		@Override
		public boolean hasNext() {

			for (int i=index; i < backpackIndex; i++) {
				if (equipped[i] != null) {
					return true;
				}
			}

			return backpackIterator.hasNext();
		}

		@Override
		public Item next() {

			while (index < backpackIndex) {
				Item item = equipped[index++];
				if (item != null) {
					return item;
				}
			}

			return backpackIterator.next();
		}

		@Override
		public void remove() {
			switch (index) {
				case 0:
					equipped[0] = weapon = null;
					break;
				case 1:
					equipped[1] = armor = null;
					break;
				case 2:
					equipped[2] = artifact = null;
					break;
				case 3:
					equipped[3] = misc = null;
					break;
				case 4:
					equipped[4] = ring = null;
					break;
				default:
					backpackIterator.remove();
			}
		}
	}
}