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

package com.touhoupixel.touhoupixeldungeongaiden.levels.traps;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Zen;
import com.touhoupixel.touhoupixeldungeongaiden.items.cubes.ClearCubeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Blandfruit;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.ChargrilledMeat;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Food;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.FrozenCarpaccio;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.MeatPie;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.MysteryMeat;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Pasty;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.RottenFood;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.SmallRice;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.StewedMeat;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.watabou.noosa.audio.Sample;

public class DecayTrap extends Trap {

	{
		color = YELLOW;
		shape = LARGE_DOT;

		avoidsHallways = false;
	}

	@Override
	public void activate() {
		Char c = Actor.findChar(pos);
		assert c != null;
		if (c.buff(Onigiri.class) != null){
			c.damage(c.HT, this);
		}
		if (c == Dungeon.heroine) {
			Food food = Dungeon.heroine.belongings.getItem(Food.class);
			Blandfruit blandfruit = Dungeon.heroine.belongings.getItem(Blandfruit.class);
			ChargrilledMeat chargrilledMeat = Dungeon.heroine.belongings.getItem(ChargrilledMeat.class);
			FrozenCarpaccio frozenCarpaccio = Dungeon.heroine.belongings.getItem(FrozenCarpaccio.class);
			MeatPie meatPie = Dungeon.heroine.belongings.getItem(MeatPie.class);
			MysteryMeat mysteryMeat = Dungeon.heroine.belongings.getItem(MysteryMeat.class);
			Pasty pasty = Dungeon.heroine.belongings.getItem(Pasty.class);
			SmallRice smallRice = Dungeon.heroine.belongings.getItem(SmallRice.class);
			StewedMeat stewedMeat = Dungeon.heroine.belongings.getItem(StewedMeat.class);
			if (meatPie != null){
				meatPie.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (pasty != null){
				pasty.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (frozenCarpaccio != null){
				frozenCarpaccio.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (blandfruit != null){
				blandfruit.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (chargrilledMeat != null){
				chargrilledMeat.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (stewedMeat != null){
				stewedMeat.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (smallRice != null){
				smallRice.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (mysteryMeat != null){
				mysteryMeat.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			} else if (food != null){
				food.detach(Dungeon.heroine.belongings.backpack);
				Dungeon.level.drop(new RottenFood(), c.pos).sprite.drop();
			}
		}
		if (Dungeon.level.heroFOV[pos]) {
			GameScene.flash(0x80FFFFFF);
			Sample.INSTANCE.play( Assets.Sounds.TELEPORT );
		}
	}
}