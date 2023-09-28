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
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.buffs.CursedBlow;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.GameScene;
import com.watabou.noosa.audio.Sample;

public class CursedBlowTrap extends Trap {

	{
		color = VIOLET;
		shape = STARS;

		avoidsHallways = false;
	}

	@Override
	public void activate() {
		Char c = Actor.findChar(pos);
		if (c != null && c == Dungeon.heroine) {
			Buff.prolong(c, CursedBlow.class, CursedBlow.DURATION);
		}
		if (Dungeon.level.heroFOV[pos]) {
			GameScene.flash(0x80FFFFFF);
			Sample.INSTANCE.play( Assets.Sounds.TELEPORT );
		}
	}
}