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

package com.touhoupixel.touhoupixeldungeongaiden.items.wands;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.AliceCurse;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DismantlePressure;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.LockedFloor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MagicDrain;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Recharging;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Slow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.MagicMissile;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeongaiden.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeongaiden.items.cubes.BlackCubeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.items.cubes.BlueCubeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.items.cubes.ClearCubeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.items.cubes.RedCubeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.items.cubes.WhiteCubeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfEnergy;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MarisaStaff;
import com.touhoupixel.touhoupixeldungeongaiden.levels.traps.DisarmingTrap;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.CellSelector;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.ui.QuickSlotButton;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public abstract class Wand extends Item {

	public static final String AC_ZAP	= "ZAP";

	private static final float TIME_TO_ZAP	= 1f;

	public int maxCharges = initialCharges();
	public int curCharges = maxCharges;
	public float partialCharge = 0f;

	protected Charger charger;

	public boolean curChargeKnown = false;

	public boolean curseInfusionBonus = false;
	public int resinBonus = 0;

	private static final int USES_TO_ID = 10;
	private float usesLeftToID = USES_TO_ID;
	private float availableUsesToID = USES_TO_ID/2f;

	protected int collisionProperties = Ballistica.MAGIC_BOLT;

	{
		defaultAction = AC_ZAP;
		usesTargeting = true;
		bones = true;
	}

	@Override
	public ArrayList<String> actions( Hero heroine) {
		ArrayList<String> actions = super.actions(heroine);
		if (Dungeon.heroine.buff(Onigiri.class) == null) {
			if (curCharges > 0 || !curChargeKnown) {
				actions.add(AC_ZAP);
			}
		}

		return actions;
	}

	@Override
	public void execute(Hero heroine, String action ) {

		super.execute(heroine, action );

		if (action.equals( AC_ZAP )) {

			curUser = heroine;
			curItem = this;
			GameScene.selectCell( zapper );
		}
	}

	@Override
	public int targetingPos(Hero user, int dst) {
		return new Ballistica( user.pos, dst, collisionProperties ).collisionPos;
	}

	public abstract void onZap(Ballistica attack);

	public abstract void onHit(MarisaStaff staff, Char attacker, Char defender, int damage);

	public boolean tryToZap(Hero owner, int target ){
		ClearCubeFragment clearCubeFragment = Dungeon.heroine.belongings.getItem(ClearCubeFragment.class);
		BlackCubeFragment blackCubeFragment = Dungeon.heroine.belongings.getItem(BlackCubeFragment.class);
		BlueCubeFragment blueCubeFragment = Dungeon.heroine.belongings.getItem(BlueCubeFragment.class);
		RedCubeFragment redCubeFragment = Dungeon.heroine.belongings.getItem(RedCubeFragment.class);
		WhiteCubeFragment whiteCubeFragment = Dungeon.heroine.belongings.getItem(WhiteCubeFragment.class);
		//Sanae's exorcism rod needs all types of cube fragments.
		if (curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&

				curItem instanceof MarisaStaff && blackCubeFragment != null && blackCubeFragment.quantity() > 1
				&& blueCubeFragment != null && blueCubeFragment.quantity() > 1
				&& redCubeFragment != null && redCubeFragment.quantity() > 1
				&& whiteCubeFragment != null && whiteCubeFragment.quantity() > 1 ||

				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof SanaeExorcismRod && blackCubeFragment != null && blackCubeFragment.quantity() > 1
						&& blueCubeFragment != null && blueCubeFragment.quantity() > 1
						&& redCubeFragment != null && redCubeFragment.quantity() > 1
						&& whiteCubeFragment != null && whiteCubeFragment.quantity() > 1 ||

				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfAntiDoor && blackCubeFragment != null && blackCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfBlastWave && blackCubeFragment != null && blackCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfCorrosion && blackCubeFragment != null && blackCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfCorruption && blackCubeFragment != null && blackCubeFragment.quantity() > 1 ||

				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfDisintegration && blueCubeFragment != null && blueCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfFireblast && blueCubeFragment != null && blueCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfFrost && blueCubeFragment != null && blueCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfHealWounds && blueCubeFragment != null && blueCubeFragment.quantity() > 1 ||

				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfLightning && redCubeFragment != null && redCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfLivingEarth && redCubeFragment != null && redCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfMagicMissile && redCubeFragment != null && redCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfMindburst && redCubeFragment != null && redCubeFragment.quantity() > 1 ||

				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfPrismaticLight && whiteCubeFragment != null && whiteCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfRegrowth && whiteCubeFragment != null && whiteCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfSetsunatrip && whiteCubeFragment != null && whiteCubeFragment.quantity() > 1 ||
				curCharges >= (cursed ? 1 : chargesPerCast()) && clearCubeFragment != null && clearCubeFragment.quantity() > 49 &&
						curItem instanceof WandOfWarding && whiteCubeFragment != null && whiteCubeFragment.quantity() > 1){

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			clearCubeFragment.detach(Dungeon.heroine.belongings.backpack);

			if (curItem instanceof MarisaStaff){
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);

				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);

				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);

				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof SanaeExorcismRod){
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);

				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);

				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);

				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}
			//all types

			if (curItem instanceof WandOfAntiDoor) {
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfBlastWave) {
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfCorrosion) {
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfCorruption) {
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blackCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}
			//black

			if (curItem instanceof WandOfDisintegration) {
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfFireblast) {
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfFrost) {
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfHealWounds) {
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				blueCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}
			//blue

			if (curItem instanceof WandOfLightning) {
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfLivingEarth) {
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfMagicMissile) {
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfMindburst) {
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				redCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}
			//red

			if (curItem instanceof WandOfPrismaticLight) {
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfRegrowth) {
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfSetsunatrip) {
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}

			if (curItem instanceof WandOfWarding) {
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
				whiteCubeFragment.detach(Dungeon.heroine.belongings.backpack);
			}
			//white

			return true;
		} else if (Statistics.card43 && Dungeon.gold >= 400){
			GLog.w(Messages.get(this, "eiki_money_trigger"));
			Sample.INSTANCE.play( Assets.Sounds.SHATTER);
			curCharges += 1;
			Dungeon.gold -= 400;
			Buff.prolong(Dungeon.heroine, Recharging.class, Recharging.DURATION);
			return true;
		} else {
			GLog.w(Messages.get(this, "fizzles"));
			return false;
		}
	}

	@Override
	public boolean collect( Bag container ) {
		if (super.collect( container )) {
			if (container.owner != null) {
				if (container instanceof MagicalHolster)
					charge( container.owner, ((MagicalHolster) container).HOLSTER_SCALE_FACTOR );
				else
					charge( container.owner );
			}
			return true;
		} else {
			return false;
		}
	}

	public void gainCharge( float amt ){
		gainCharge( amt, false );
	}

	public void gainCharge( float amt, boolean overcharge ){
		partialCharge += amt;
		while (partialCharge >= 1) {
			if (overcharge) curCharges = Math.min(maxCharges+(int)amt, curCharges+1);
			else curCharges = Math.min(maxCharges, curCharges+1);
			partialCharge--;
			updateQuickslot();
		}
	}
	
	public void charge( Char owner ) {
		if (charger == null) charger = new Charger();
		charger.attachTo( owner );
	}

	public void charge( Char owner, float chargeScaleFactor ){
		charge( owner );
		charger.setScaleFactor( chargeScaleFactor );
	}

	protected void wandProc(Char target, int chargesUsed){
		wandProc(target, buffedLvl(), chargesUsed);

		if (Dungeon.heroine.buff(DismantlePressure.class) != null){
			Buff.prolong(Dungeon.heroine, Slow.class, Slow.DURATION);
		}

		if (Dungeon.heroine.buff(AliceCurse.class) != null) {
			new DisarmingTrap().set(Dungeon.heroine.pos).activate();
		}

		if (Dungeon.heroine.buff(MagicDrain.class) != null && Dungeon.heroine.HP > 3){
			Dungeon.heroine.HP /= 2;
			GameScene.flash(-65536);
			GLog.w(Messages.get(this, "magic_drain"));
		}

		if (Statistics.card34) {
			Statistics.power -= Random.Int(1,3);
		} else Statistics.power -= 10;
	}

	protected static void wandProc(Char target, int wandLevel, int chargesUsed){
	}

	@Override
	public void onDetach( ) {
		stopCharging();
	}

	public void stopCharging() {
		if (charger != null) {
			charger.detach();
			charger = null;
		}
	}
	
	public void level( int value) {
		super.level( value );
		updateLevel();
	}
	
	@Override
	public Item identify( boolean byHero ) {
		
		curChargeKnown = true;
		super.identify(byHero);
		
		updateQuickslot();
		
		return this;
	}
	
	public void onHeroGainExp( float levelPercent, Hero heroine){
		if (!isIdentified() && availableUsesToID <= USES_TO_ID/2f) {
			//gains enough uses to ID over 1 level
			availableUsesToID = Math.min(USES_TO_ID/2f, availableUsesToID + levelPercent * USES_TO_ID/2f);
		}
	}

	@Override
	public String info() {
		String desc = desc();

		desc += "\n\n" + statsDesc();

		if (resinBonus == 1){
			desc += "\n\n" + Messages.get(Wand.class, "resin_one");
		} else if (resinBonus > 1){
			desc += "\n\n" + Messages.get(Wand.class, "resin_many", resinBonus);
		}

		if (cursed && cursedKnown) {
			desc += "\n\n" + Messages.get(Wand.class, "cursed");
		} else if (!isIdentified() && cursedKnown){
			desc += "\n\n" + Messages.get(Wand.class, "not_cursed");
		}

		return desc;
	}

	public String statsDesc(){
		return Messages.get(this, "stats_desc");
	}
	
	@Override
	public boolean isIdentified() {
		return super.isIdentified() && curChargeKnown;
	}
	
	@Override
	public String status() {
		if (levelKnown) {
			return (curChargeKnown ? curCharges : "?") + "/" + maxCharges;
		} else {
			return null;
		}
	}
	
	@Override
	public int level() {
		if (!cursed && curseInfusionBonus){
			curseInfusionBonus = false;
			updateLevel();
		}
		int level = super.level();
		if (curseInfusionBonus) level += 1 + level/6;
		level += resinBonus;
		return level;
	}
	
	@Override
	public Item upgrade() {

		super.upgrade();

		if (Random.Int(3) == 0) {
			cursed = false;
		}

		if (resinBonus > 0){
			resinBonus--;
		}

		updateLevel();
		curCharges = Math.min( curCharges + 1, maxCharges );
		updateQuickslot();
		
		return this;
	}
	
	@Override
	public Item degrade() {
		super.degrade();
		
		updateLevel();
		updateQuickslot();
		
		return this;
	}

	@Override
	public int buffedLvl() {
		int lvl = super.buffedLvl();

		if (charger != null && charger.target != null) {
			WandOfMagicMissile.MagicCharge buff = charger.target.buff(WandOfMagicMissile.MagicCharge.class);
			if (buff != null && buff.level() > lvl){
				return buff.level();
			}
		}
		return lvl;
	}

	public void updateLevel() {
		maxCharges = Math.min( initialCharges() + level(), 10 );
		curCharges = Math.min( curCharges, maxCharges );
	}
	
	protected int initialCharges() {
		return 2;
	}

	protected int chargesPerCast() {
		return 1;
	}
	
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar( curUser.sprite.parent,
				MagicMissile.MAGIC_MISSILE,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
	}

	public void staffFx( MarisaStaff.StaffParticle particle ){
		particle.color(0xFFFFFF); particle.am = 0.3f;
		particle.setLifespan( 1f);
		particle.speed.polar( Random.Float(PointF.PI2), 2f );
		particle.setSize( 1f, 2f );
		particle.radiateXY(0.5f);
	}

	protected void wandUsed() {
		if (!isIdentified()) {
			float uses = Math.min( availableUsesToID, availableUsesToID );
			availableUsesToID -= uses;
			usesLeftToID -= uses;
			if (usesLeftToID <= 0) {
				identify();
				GLog.p( Messages.get(Wand.class, "identify") );
			}
		}
		
		curCharges -= cursed ? 1 : chargesPerCast();

		//remove magic charge at a higher priority, if we are benefiting from it are and not the
		//wand that just applied it
		WandOfMagicMissile.MagicCharge buff = curUser.buff(WandOfMagicMissile.MagicCharge.class);
		if (buff != null
				&& buff.wandJustApplied() != this
				&& buff.level() == buffedLvl()
				&& buffedLvl() > super.buffedLvl()){
			buff.detach();
		}

		//if the wand is owned by the hero, but not in their inventory, it must be in the staff
		if (charger != null
				&& charger.target == Dungeon.heroine
				&& !Dungeon.heroine.belongings.contains(this)) {
		}

		if (Statistics.card28 && Random.Int(2) == 0) {
			Invisibility.dispel();
		} else {
			Invisibility.dispel();
		}
		updateQuickslot();

		curUser.spendAndNext( TIME_TO_ZAP );
	}
	
	@Override
	public Item random() {
		//+0: 66.67% (2/3)
		//+1: 26.67% (4/15)
		//+2: 6.67%  (1/15)
		int n = 0;
		if (Random.Int(3) == 0) {
			n++;
			if (Random.Int(5) == 0){
				n++;
			}
		}
		level(n);
		curCharges += n;
		
		//30% chance to be cursed
		if (Random.Float() < 0.3f) {
			cursed = true;
		}

		return this;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		if (resinBonus == 0) return null;

		return new ItemSprite.Glowing(0xFFFFFF, 1f/(float)resinBonus);
	}

	@Override
	public int value() {
		int price = 75;
		if (cursed && cursedKnown) {
			price /= 2;
		}
		if (levelKnown) {
			if (level() > 0) {
				price *= (level() + 1);
			} else if (level() < 0) {
				price /= (1 - level());
			}
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}
	
	private static final String USES_LEFT_TO_ID     = "uses_left_to_id";
	private static final String AVAILABLE_USES      = "available_uses";
	private static final String CUR_CHARGES         = "curCharges";
	private static final String CUR_CHARGE_KNOWN    = "curChargeKnown";
	private static final String PARTIALCHARGE       = "partialCharge";
	private static final String CURSE_INFUSION_BONUS= "curse_infusion_bonus";
	private static final String RESIN_BONUS         = "resin_bonus";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( USES_LEFT_TO_ID, usesLeftToID );
		bundle.put( AVAILABLE_USES, availableUsesToID );
		bundle.put( CUR_CHARGES, curCharges );
		bundle.put( CUR_CHARGE_KNOWN, curChargeKnown );
		bundle.put( PARTIALCHARGE , partialCharge );
		bundle.put( CURSE_INFUSION_BONUS, curseInfusionBonus );
		bundle.put( RESIN_BONUS, resinBonus );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		usesLeftToID = bundle.getInt( USES_LEFT_TO_ID );
		availableUsesToID = bundle.getInt( AVAILABLE_USES );
		curseInfusionBonus = bundle.getBoolean(CURSE_INFUSION_BONUS);
		resinBonus = bundle.getInt(RESIN_BONUS);

		updateLevel();

		curCharges = bundle.getInt( CUR_CHARGES );
		curChargeKnown = bundle.getBoolean( CUR_CHARGE_KNOWN );
		partialCharge = bundle.getFloat( PARTIALCHARGE );
	}
	
	@Override
	public void reset() {
		super.reset();
		usesLeftToID = USES_TO_ID;
		availableUsesToID = USES_TO_ID/2f;
	}

	public int collisionProperties(int target){
		return collisionProperties;
	}

	public static class PlaceHolder extends Wand {

		{
			image = ItemSpriteSheet.WAND_HOLDER;
		}

		@Override
		public boolean isSimilar(Item item) {
			return item instanceof Wand;
		}

		@Override
		public void onZap(Ballistica attack) {}
		public void onHit(MarisaStaff staff, Char attacker, Char defender, int damage) {}

		@Override
		public String info() {
			return "";
		}
	}
	
	protected static CellSelector.Listener zapper = new  CellSelector.Listener() {
		
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {

				//FIXME this safety check shouldn't be necessary
				//it would be better to eliminate the curItem static variable.
				final Wand curWand;
				if (curItem instanceof Wand) {
					curWand = (Wand) Wand.curItem;
				} else {
					return;
				}

				final Ballistica shot = new Ballistica( curUser.pos, target, curWand.collisionProperties(target));
				int cell = shot.collisionPos;
				
				if (target == curUser.pos || cell == curUser.pos) {
					GLog.i( Messages.get(Wand.class, "self_target") );
					return;
				}

				curUser.sprite.zap(cell);

				//attempts to target the cell aimed at if something is there, otherwise targets the collision pos.
				if (Actor.findChar(target) != null)
					QuickSlotButton.target(Actor.findChar(target));
				else
					QuickSlotButton.target(Actor.findChar(cell));
				
				if (curWand.tryToZap(curUser, target)) {
					
					curUser.busy();
					
					if (curWand.cursed){
						if (!curWand.cursedKnown){
							GLog.n(Messages.get(Wand.class, "curse_discover", curWand.name()));
						}
						CursedWand.cursedZap(curWand,
								curUser,
								new Ballistica(curUser.pos, target, Ballistica.MAGIC_BOLT),
								new Callback() {
									@Override
									public void call() {
										curWand.wandUsed();
									}
								});
					} else {
						curWand.fx(shot, new Callback() {
							public void call() {
								curWand.onZap(shot);
								curWand.wandUsed();
							}
						});
					}
					curWand.cursedKnown = true;
					
				}
				
			}
		}
		
		@Override
		public String prompt() {
			return Messages.get(Wand.class, "prompt");
		}
	};
	
	public class Charger extends Buff {
		
		private static final float BASE_CHARGE_DELAY = 10f;
		private static final float SCALING_CHARGE_ADDITION = 40f;
		private static final float NORMAL_SCALE_FACTOR = 0.875f;

		private static final float CHARGE_BUFF_BONUS = 0.25f;

		float scalingFactor = NORMAL_SCALE_FACTOR;
		
		@Override
		public boolean attachTo( Char target ) {
			super.attachTo( target );
			
			return true;
		}
		
		@Override
		public boolean act() {
			if (curCharges < maxCharges)
				recharge();
			
			while (partialCharge >= 1 && curCharges < maxCharges) {
				partialCharge--;
				curCharges++;
				updateQuickslot();
			}
			
			if (curCharges == maxCharges){
				partialCharge = 0;
			}
			
			spend( TICK );
			
			return true;
		}

		private void recharge(){
			int missingCharges = maxCharges - curCharges;
			missingCharges = Math.max(0, missingCharges);

			float turnsToCharge = (float) (BASE_CHARGE_DELAY
					+ (SCALING_CHARGE_ADDITION * Math.pow(scalingFactor, missingCharges)));

			LockedFloor lock = target.buff(LockedFloor.class);
			if (lock == null || lock.regenOn())
				partialCharge += (1f/turnsToCharge) * RingOfEnergy.wandChargeMultiplier(target);

			for (Recharging bonus : target.buffs(Recharging.class)){
				if (bonus != null && bonus.remainder() > 0f) {
					partialCharge += CHARGE_BUFF_BONUS * bonus.remainder();
				}
			}
		}
		
		public Wand wand(){
			return Wand.this;
		}

		public void gainCharge(float charge){
			if (curCharges < maxCharges) {
				partialCharge += charge;
				while (partialCharge >= 1f) {
					curCharges++;
					partialCharge--;
				}
				curCharges = Math.min(curCharges, maxCharges);
				updateQuickslot();
			}
		}

		private void setScaleFactor(float value){
			this.scalingFactor = value;
		}
	}
}
