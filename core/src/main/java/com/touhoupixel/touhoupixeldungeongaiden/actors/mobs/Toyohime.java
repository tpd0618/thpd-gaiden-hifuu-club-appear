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
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DeSlaying;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeongaiden.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeongaiden.items.tickets.FiveStarTicket;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ToyohimeSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Toyohime extends Mob implements Callback {

	private static final float TIME_TO_ZAP = 1f;

	{
		spriteClass = ToyohimeSprite.class;

		HP = HT = 500;
		defenseSkill = 40;
		EXP = 24;
		maxLvl = 99;

		flying = true;

		properties.add(Property.WARP);

		loot = new FiveStarTicket();
		lootChance = 0.1f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(40, 50);
	}

	@Override
	public int attackSkill(Char target) {
		return 75;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	@Override
	protected boolean canAttack(Char enemy) {
		return new Ballistica(pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
	}

	protected boolean doAttack(Char enemy) {

		if (Dungeon.level.adjacent(pos, enemy.pos)) {

			return super.doAttack(enemy);

		} else {

			if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
				sprite.zap(enemy.pos);
				return false;
			} else {
				zap();
				return true;
			}
		}
	}

	//used so resistances can differentiate between melee and magical attacks
	public static class DarkBolt {
	}

	private void zap() {
		spend(TIME_TO_ZAP);

		if (hit(this, enemy, true)) {
			//TODO would be nice for this to work on ghost/statues too
			if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
				Buff.prolong(enemy, DismantlePressure.class, DismantlePressure.DURATION);
				Sample.INSTANCE.play(Assets.Sounds.DEBUFF);
			}

			int dmg = Random.NormalIntRange(26, 32);
			enemy.damage(dmg, new DarkBolt());

			if (enemy == Dungeon.heroine && !enemy.isAlive()) {
				Dungeon.fail(getClass());
				GLog.n(Messages.get(this, "bolt_kill"));
			}
		} else {
			enemy.sprite.showStatus(CharSprite.NEUTRAL, enemy.defenseVerb());
		}
	}

	public void onZapComplete() {
		zap();
		next();
	}

	@Override
	public void call() {
		next();
	}

}
