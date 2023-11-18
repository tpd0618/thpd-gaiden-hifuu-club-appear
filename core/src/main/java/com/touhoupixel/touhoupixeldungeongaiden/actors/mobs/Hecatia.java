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

package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.HecatiaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.HecatiaSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Hecatia extends Mob {

	{
		spriteClass = HecatiaSprite.class;

		HP = HT = Dungeon.floor*5;
		defenseSkill = 0;

		flying = true;

		properties.add(Property.GOD);

		EXP = 0;
		maxLvl = 99;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(Dungeon.floor, Dungeon.floor +2);
	}

	@Override
	public int attackSkill(Char target) {
		return Dungeon.floor+5;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	@Override
	public int attackProc(Char hero, int damage) {
		damage = super.attackProc(enemy, damage);
		if (!(Dungeon.heroine.belongings.armor() instanceof HecatiaArmor)) {
			if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
				Sample.INSTANCE.play(Assets.Sounds.READ);
				CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					mob.beckon(enemy.pos);
					Buff.prolong(mob, DoubleSpeed.class, DoubleSpeed.DURATION * 1000f);
					Buff.prolong(mob, Might.class, Might.DURATION * 1000f);
				}
			}
		}
		return damage;
	}
}