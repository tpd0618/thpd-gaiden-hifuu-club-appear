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

package com.touhoupixel.touhoupixeldungeongaiden.windows;

import static com.touhoupixel.touhoupixeldungeongaiden.Dungeon.heroine;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.npcs.Rinnosuke;
import com.touhoupixel.touhoupixeldungeongaiden.items.EquipableItem;
import com.touhoupixel.touhoupixeldungeongaiden.items.Gold;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.MasterThievesArmband;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.ui.RedButton;

public class WndTradeItem extends WndInfoItem {

	private static final float GAP		= 2;
	private static final int BTN_HEIGHT	= 18;

	private WndBag owner;

	private boolean selling = false;

	//selling
	public WndTradeItem( final Item item, WndBag owner ) {

		super(item);

		selling = true;

		this.owner = owner;

		float pos = height;

		if (item.quantity() == 1) {

			RedButton btnSell = new RedButton( Messages.get(this, "sell", item.value()) ) {
				@Override
				protected void onClick() {
					sell( item );
					hide();
				}
			};
			btnSell.setRect( 0, pos + GAP, width, BTN_HEIGHT );
			btnSell.icon(new ItemSprite(ItemSpriteSheet.GOLD));
			add( btnSell );

			pos = btnSell.bottom();

		} else {

			int priceAll= item.value();
			RedButton btnSell1 = new RedButton( Messages.get(this, "sell_1", priceAll / item.quantity()) ) {
				@Override
				protected void onClick() {
					sellOne( item );
					hide();
				}
			};
			btnSell1.setRect( 0, pos + GAP, width, BTN_HEIGHT );
			btnSell1.icon(new ItemSprite(ItemSpriteSheet.GOLD));
			add( btnSell1 );
			RedButton btnSellAll = new RedButton( Messages.get(this, "sell_all", priceAll ) ) {
				@Override
				protected void onClick() {
					sell( item );
					hide();
				}
			};
			btnSellAll.setRect( 0, btnSell1.bottom() + 1, width, BTN_HEIGHT );
			btnSellAll.icon(new ItemSprite(ItemSpriteSheet.GOLD));
			add( btnSellAll );

			pos = btnSellAll.bottom();

		}

		resize( width, (int)pos );
	}

	//buying
	public WndTradeItem( final Heap heap ) {

		super(heap);

		selling = false;

		Item item = heap.peek();

		float pos = height;

		final int price = Rinnosuke.sellPrice( item );

		RedButton btnBuy = new RedButton( Messages.get(this, "buy", price) ) {
			@Override
			protected void onClick() {
				hide();
				buy( heap );
			}
		};
		btnBuy.setRect( 0, pos + GAP, width, BTN_HEIGHT );
		btnBuy.icon(new ItemSprite(ItemSpriteSheet.GOLD));
		btnBuy.enable( price <= Dungeon.gold);

		/* This insertion is necessary to avoid a bug in which a player can buy these cards (without collecting it, for which he uses additional conditions), then buy another card and collect this one */
		boolean miracleMalletSpecialCondition = true;
		boolean undegroundSunSpecialCondition = true;
		TimekeepersHourglass tH = heroine.belongings.getItem(TimekeepersHourglass.class);
		if (tH != null) {
		}
		btnBuy.enable( price <= Dungeon.gold && miracleMalletSpecialCondition && undegroundSunSpecialCondition);
		/* ================= */

		add( btnBuy );

		pos = btnBuy.bottom();

		final MasterThievesArmband.Thievery thievery = heroine.buff(MasterThievesArmband.Thievery.class);
		if (thievery != null && !thievery.isCursed() && thievery.chargesToUse(item) > 0) {
			final float chance = thievery.stealChance(item);
			final int chargesToUse = thievery.chargesToUse(item);
			RedButton btnSteal = new RedButton(Messages.get(this, "steal", Math.min(100, (int) (chance * 100)), chargesToUse), 6) {
				@Override
				protected void onClick() {
					if (thievery.steal(item)) {
						Hero heroine = Dungeon.heroine;
						Item item = heap.pickUp();
						hide();

						if (!item.doPickUp(heroine)) {
							Dungeon.level.drop(item, heap.pos).sprite.drop();
						}
					} else {
						for (Mob mob : Dungeon.level.mobs) {
							if (mob instanceof Rinnosuke) {
								mob.yell(Messages.get(mob, "thief"));
								((Rinnosuke) mob).flee();
								break;
							}
						}
						hide();
					}
				}
			};
			btnSteal.setRect(0, pos + 1, width, BTN_HEIGHT);
			btnSteal.icon(new ItemSprite(ItemSpriteSheet.ARTIFACT_ARMBAND));
			add(btnSteal);

			pos = btnSteal.bottom();

		}

		resize(width, (int) pos);
	}
	
	@Override
	public void hide() {
		
		super.hide();
		
		if (owner != null) {
			owner.hide();
		}
		if (selling) Rinnosuke.sell();
	}
	
	public static void sell( Item item ) {
		
		Hero heroine = Dungeon.heroine;
		
		if (item.isEquipped(heroine) && !((EquipableItem)item).doUnequip(heroine, false )) {
			return;
		}
		item.detachAll( heroine.belongings.backpack );

		//selling items in the sell interface doesn't spend time
		heroine.spend(-heroine.cooldown());

		new Gold( item.value() ).doPickUp(heroine);
	}

	public static void sellOne( Item item ) {
		
		if (item.quantity() <= 1) {
			sell( item );
		} else {
			
			Hero heroine = Dungeon.heroine;
			
			item = item.detach( heroine.belongings.backpack );

			//selling items in the sell interface doesn't spend time
			heroine.spend(-heroine.cooldown());

			new Gold( item.value() ).doPickUp(heroine);
		}
	}
	
	private void buy( Heap heap ) {
		
		Item item = heap.pickUp();
		if (item == null) return;
		
		int price = Rinnosuke.sellPrice( item );
		Dungeon.gold -= price;

		if (!item.doPickUp( heroine)) {
			Dungeon.level.drop( item, heap.pos ).sprite.drop();
		}
	}
}
