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

package com.touhoupixel.touhoupixeldungeonreloaded.levels.traps;

import com.touhoupixel.touhoupixeldungeonreloaded.Assets;
import com.touhoupixel.touhoupixeldungeonreloaded.Dungeon;
import com.touhoupixel.touhoupixeldungeonreloaded.Statistics;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeonreloaded.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeonreloaded.messages.Messages;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class EnchantEraseTrap extends Trap {

	{
		color = YELLOW;
		shape = LARGE_DOT;

		avoidsHallways = false;
	}

	@Override
	public void activate() {
		Char c = Actor.findChar(pos);
		if (!Statistics.card36) {
			if (c != null && c == Dungeon.heroine) {
				MeleeWeapon meleeweapon = Dungeon.heroine.belongings.getItem(MeleeWeapon.class);
				Armor armor = Dungeon.heroine.belongings.getItem(Armor.class);
				if (meleeweapon != null) {
					meleeweapon.enchantment = null;
					GLog.w(Messages.get(this, "weaponenchanterase"));
				}
				if (armor != null) {
					armor.glyph = null;
					GLog.w(Messages.get(this, "armorenchanterase"));
				}
			}
			if (Dungeon.level.heroFOV[pos]) {
				GameScene.flash(0x80FFFFFF);
				Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
			}
		}
	}
}