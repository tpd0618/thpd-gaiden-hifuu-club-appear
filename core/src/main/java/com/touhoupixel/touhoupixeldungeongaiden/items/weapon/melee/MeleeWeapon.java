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

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaAra;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaKusi;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaNigi;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.MitamaSaki;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.Weapon;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.watabou.utils.Random;

public class MeleeWeapon extends Weapon {

	@Override
	public int min(int lvl) {
		return 4+Dungeon.heroine.lvl+Dungeon.heroine.STR;
	}//all weapons must need this

	@Override
	public int max(int lvl) {
		return 5+Dungeon.heroine.lvl*2+Dungeon.heroine.STR*2;
	}//all weapons must need this

	@Override
	public int damageRoll(Char owner) {
		int damage = augment.damageFactor(super.damageRoll(owner));
		if (Dungeon.heroine.buff(Onigiri.class) != null) {
			return Random.NormalIntRange(4+Dungeon.heroine.lvl+Dungeon.heroine.STR, 5+Dungeon.heroine.lvl*2+Dungeon.heroine.STR*2);
		} else {
			//normal
		}

		return damage;
	}

	public int critDamageRoll(Char owner) {
		int damage = augment.damageFactor(Random.IntRange((int) (max()), max()));
		if (Dungeon.heroine.buff(Onigiri.class) != null) {
			return Random.NormalIntRange(4+Dungeon.heroine.lvl+Dungeon.heroine.STR, 5+Dungeon.heroine.lvl*2+Dungeon.heroine.STR*2);
		} else {
			//normal
		}
		return damage;
	}

	@Override
	public void onThrow(int cell) {
		Heap heap = Dungeon.level.drop(this, cell);
		Char ch = (Char) Actor.findChar(cell);
		if (!heap.isEmpty() && ch != null && ch != Dungeon.heroine) {
			MeleeWeapon meleeWeapon = (MeleeWeapon) curItem;
			if (ch instanceof MitamaAra || ch instanceof MitamaKusi || ch instanceof MitamaNigi || ch instanceof MitamaSaki) {
				ch.damage(0, curUser);
				//zero damage
			} else {
				ch.damage(Random.NormalIntRange(Dungeon.heroine.STR + (meleeWeapon.min() * (meleeWeapon.level() + 1)),
						Dungeon.heroine.STR + (meleeWeapon.max() * (meleeWeapon.level() + 1))), curUser);
				//high damage
			}
			Heap[] equipHeaps = new Heap[1];
			equipHeaps[0] = Dungeon.level.heaps.get(ch.pos);
			heap.remove(curItem);
		} else {
			heap.sprite.drop(cell);
		}
	}
	
	@Override
	public String info() {

		String info = desc();
		if (levelKnown) {
			info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_known", augment.damageFactor(min()), augment.damageFactor(max()));
			if (Dungeon.heroine.heroClass == HeroClass.PLAYERHEARN){
				info += "\n\n" + Messages.get(MeleeWeapon.class, "crit_chance", (int)(Dungeon.heroine.getCritChance()*100));
			}
		} else {
			info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_unknown", min(0), max(0));
		}

		String statsInfo = statsInfo();
		if (!statsInfo.equals("")) info += "\n\n" + statsInfo;

		switch (augment) {
			case SPEED:
				info += " " + Messages.get(Weapon.class, "faster");
				break;
			case DAMAGE:
				info += " " + Messages.get(Weapon.class, "stronger");
				break;
			case NONE:
		}

		if (enchantment != null && (cursedKnown || !enchantment.curse())){
			info += "\n\n" + Messages.get(Weapon.class, "enchanted", enchantment.name());
			info += " " + enchantment.desc();
		}

		if (cursed && isEquipped( Dungeon.heroine)) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed_worn");
		} else if (cursedKnown && cursed) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed");
		} else if (!isIdentified() && cursedKnown){
			info += "\n\n" + Messages.get(Weapon.class, "not_cursed");
		}
		
		return info;
	}
	
	public String statsInfo(){
		return Messages.get(this, "stats_desc");
	}
	
	@Override
	public int value() {
		int price = 20;
		if (hasGoodEnchant()) {
			price *= 1.5;
		}
		if (cursedKnown && (cursed || hasCurseEnchant())) {
			price /= 2;
		}
		if (levelKnown && level() > 0) {
			price *= (level() + 1);
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}

}
