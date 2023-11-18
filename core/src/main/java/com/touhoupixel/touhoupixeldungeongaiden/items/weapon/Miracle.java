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

package com.touhoupixel.touhoupixeldungeongaiden.items.weapon;

import static com.touhoupixel.touhoupixeldungeongaiden.Dungeon.heroine;
import static com.touhoupixel.touhoupixeldungeongaiden.actors.Actor.TICK;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.BossKiller;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MagicBuff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Roots;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Terror;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.HeroClass;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Beam;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.PurpleParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.Wand;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Terrain;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.CellSelector;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.tiles.DungeonTilemap;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Miracle extends Item {

	public static final String AC_SHOOT = "SHOOT";

	{
		image = ItemSpriteSheet.MIRACLE_ARROW;

		defaultAction = AC_SHOOT;

		unique = true;
		bones = false;
	}

	@Override
	public ArrayList<String> actions(Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		actions.remove(AC_DROP);
		actions.remove(AC_THROW);
		if (Dungeon.heroine.buff(Onigiri.class) == null) {
			actions.add(AC_SHOOT);
		}
		return actions;
	}

	@Override
	public void execute(Hero heroine, String action) {

		super.execute(heroine, action);

		if (action.equals(AC_SHOOT)) {
			if (Statistics.card66) {
				if (curUser.buff(AnkhInvulnerability.class) != null) {
					GLog.w(Messages.get(this, "alreadyinv"));
				} else if (Statistics.spellcard < 1 && !(curUser.HT > 4) && !(curUser.HP > 4)) {
					GLog.w(Messages.get(this, "nospellcard"));
				} else if (Statistics.spellcard > 0) {
					if (Statistics.card35) {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
					} else {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
					}
					Statistics.spellcard -= 1;
					Statistics.spellcarduse = true;
					GameScene.flash(0x80FFFFFF);
					Sample.INSTANCE.play(Assets.Sounds.BLAST);
				} else {
					if (Statistics.card35) {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
					} else {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
					}
					curUser.HP -= 4;
					curUser.HT -= 4;
					Statistics.maxHP_down -= 4;
					Statistics.spellcarduse = true;
					GameScene.flash(0x80FFFFFF);
					Sample.INSTANCE.play(Assets.Sounds.BLAST);
				}
			} else {
				if (Statistics.spellcard < 1) {
					GLog.w(Messages.get(this, "nospellcard"));
				} else if (curUser.buff(AnkhInvulnerability.class) != null) {
					GLog.w(Messages.get(this, "alreadyinv"));
				} else {
					if (Statistics.card35) {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION);
					} else {
						Buff.prolong(curUser, AnkhInvulnerability.class, AnkhInvulnerability.DURATION / 2f);
					}
					Statistics.spellcard -= 1;
					Statistics.bomb_count += 1;
					Statistics.spellcarduse = true;
					GameScene.flash(0x80FFFFFF);
					Sample.INSTANCE.play(Assets.Sounds.BLAST);

					switch (Dungeon.heroine.heroClass){
						case PLAYERRENKO:
						case PLAYERHEARN:
							break;
					}
				}
			}
		}
	}


	@Override
	public String desc() {
		String desc = super.desc();
		HeroClass whoHero = heroine.heroClass;
		switch (whoHero) {
			case PLAYERRENKO:
			case PLAYERHEARN:
				break;
		}
		return desc;
	}

	//Marisa's laser
	protected static CellSelector.Listener zapper = new CellSelector.Listener() {

		@Override
		public void onSelect(Integer target) {
			if (target != null) {
				//FIXME this safety check shouldn't be necessary
				//it would be better to eliminate the curItem static variable.
				final Ballistica[] lasers;
				int w = Dungeon.level.width();
				if (Statistics.card10){
					switch (laserPlacement(heroine.pos, target)) {
						case VERTICAL:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - w, target - w, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 2*w, target - 2*w, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + w, target + w, Ballistica.WONT_STOP),
								new Ballistica(heroine.pos + 2*w, target + 2*w, Ballistica.WONT_STOP)};
							break;
						default:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 1, target - 1, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 2, target - 2, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + 1, target + 1, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + 2, target + 2, Ballistica.WONT_STOP)};
							break;
					}
				}
				else {
					switch (laserPlacement(heroine.pos, target)) {
						case VERTICAL:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - w, target - w, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + w, target + w, Ballistica.WONT_STOP)};
							break;
						default:
							lasers = new Ballistica[]{new Ballistica(heroine.pos, target, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos - 1, target - 1, Ballistica.WONT_STOP),
									new Ballistica(heroine.pos + 1, target + 1, Ballistica.WONT_STOP)};
							break;
					}
				}
				final Ballistica beam = lasers[0];
				int cell = beam.collisionPos;

				if (target == heroine.pos || cell == heroine.pos) {
					GLog.i(Messages.get(Wand.class, "self_target"));
					return;
				}

				heroine.sprite.zap(cell);
				heroine.busy();
				marisaBombFx(beam, new Callback() {
					public void call() {
						for (Ballistica laser : lasers) {

							boolean terrainAffected = false;
							ArrayList<Char> chars = new ArrayList<>();

							for (int c : laser.subPath(1, laser.dist)) {

								Char ch;
								if ((ch = Actor.findChar(c)) != null) {
									chars.add(ch);
								}

								if (Dungeon.level.flamable[c]) {

									Dungeon.level.destroy(c);
									GameScene.updateMap(c);
									terrainAffected = true;

								}

								CellEmitter.center(c).burst(PurpleParticle.BURST, Random.IntRange(1, 2));
							}

							if (terrainAffected) {
								Dungeon.observe();
							}
							for (Char ch : chars) {
								ch.damage(laserDamageRoll(), this);
							}
						}
						heroine.spendAndNext(TICK);
					}

				});

			}
		}

		@Override
		public String prompt() {
			return Messages.get(Wand.class, "prompt");
		}
	};

	private static void marisaBombFx(Ballistica beam, Callback callback) {
			int cell = beam.path.get(beam.dist);
			if (Statistics.card10){
				curUser.sprite.parent.add(new Beam.MasterSparkWideRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cell )));
			}
			else {
				curUser.sprite.parent.add(new Beam.MasterSparkRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cell )));
			}
			callback.call();
		}

	private static int minLaserDamage(){
		return 4 + Dungeon.floor*2 + ((Dungeon.floor) / 30) * 20; // After the 29th floor there is a sharp increase in damage
	}
	private static int maxLaserDamage(){
		return 8 + (Dungeon.floor + 1)*6 + ((Dungeon.floor) / 30) * 50;
	}
	private static int laserDamageRoll(){
		int dmg = Random.NormalIntRange(minLaserDamage(), maxLaserDamage());

		Char enemy = heroine.enemy();
		if (heroine.buff(WandZeroDamage.class) != null){
			dmg *= 0f;
		}
		if (heroine.buff(MagicBuff.class) != null) {
			dmg *= 1.25f;
		}
		if (Statistics.card31) {
			dmg *= 1.25f;
		}

		if (Statistics.wand_power_up == 1) {
			dmg *= 1.06f;
		}
		if (Statistics.wand_power_up == 2) {
			dmg *= 1.12f;
		}
		if (Statistics.wand_power_up == 3) {
			dmg *= 1.18f;
		}
		if (Statistics.wand_power_up == 4) {
			dmg *= 1.24f;
		}
		if (Statistics.wand_power_up >= 5) {
			dmg *= 1.3f;
		} //potion of enlightenment

		if (Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.MINIBOSS) ||
				Dungeon.heroine.buff(BossKiller.class) != null && enemy.properties().contains(Char.Property.BOSS)){
			dmg *= 2f;
		}

		return dmg;
	}
	private enum LaserPlacement{
		VERTICAL,

		HORIZONTAL;
	}
		private static LaserPlacement laserPlacement(int from, int to){

			int w = Dungeon.level.width();

			int x0 = from % w;
			int x1 = to % w;
			int y0 = from / w;
			int y1 = to / w;

			int dx = x1 - x0;
			int dy = y1 - y0;

			int dxAbs = Math.abs( dx );
			int dyAbs = Math.abs( dy );

			if (dyAbs > dxAbs){
				return LaserPlacement.HORIZONTAL;
			}
			else {
				return LaserPlacement.VERTICAL;
			}
		}
		// Sanae's ability
		private void sanaeBomb() {
			final int initDamage = 10 + Dungeon.floor*4;
			float damage = initDamage;
			int targets = 0;
			float decreaseFact = 0.8f;
			for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
				if (Dungeon.level.heroFOV[mob.pos] && (Dungeon.level.map[mob.pos] == Terrain.GRASS || Dungeon.level.map[mob.pos] == Terrain.WATER)) {
					targets++;
					damage = damage * (1 + decreaseFact * (targets - 1)) / targets; // if the enemy is one, he will receive 100% damage, if 2, then 90%, if 3, then 78%, etc.
				}
				if (damage < initDamage * 0.2f) {
					damage = initDamage * 0.2f; // Min damage - 20% of the initial value
					break;
				}
			}
			int finalDamage = (int)damage;
			for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
				if (Dungeon.level.heroFOV[mob.pos] && (Dungeon.level.map[mob.pos] == Terrain.GRASS || Dungeon.level.map[mob.pos] == Terrain.WATER)) {
					if (Dungeon.level.map[mob.pos] == Terrain.GRASS){
						Buff.prolong(mob, Roots.class, 10f);
						if (Statistics.card14){
							Buff.affect(mob, Poison.class).set(5 + Dungeon.floor*2/3);
						}
					}
					if (Dungeon.level.map[mob.pos] == Terrain.WATER) {
						Buff.prolong(mob, Vertigo.class, 10f);
						if (Statistics.card14){
							Buff.prolong(mob, Terror.class, 10f);
						}
					}

					mob.damage(finalDamage, this);
				}
			}
		}
		private void sakuyaBomb(){
		TimekeepersHourglass tH = heroine.belongings.getItem(TimekeepersHourglass.class);
		if (tH != null){
			tH.charge = Math.min(tH.charge + 3, tH.getChargeCap());
			Item.updateQuickslot();
		}
		if (Statistics.card12) {
			Buff.affect(heroine, Swiftthistle.TimeBubble.class).set(9f);
		}
		}
		@Override
		public boolean isUpgradable() {
			return false;
		}
}