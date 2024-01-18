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
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Onigiri;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Food;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.MinorikoSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Minoriko extends Mob {

    public Item item;

    {
        spriteClass = MinorikoSprite.class;

        HP = HT = 98;
        defenseSkill = 27;
        EXP = 15;
        maxLvl = 35;

        properties.add(Property.GOD);

        WANDERING = new Wandering();
        FLEEING = new Fleeing();

        loot = new Food();
        lootChance = 0.1f;
    }

    private static final String ITEM = "item";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(ITEM, item);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        item = (Item) bundle.get(ITEM);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(17, 25);
    }

    @Override
    public int attackSkill(Char target) {
        return 32;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char enemy, int damage) {
        damage = super.attackProc(enemy, damage);
        if (alignment == Alignment.ENEMY && item == null && enemy instanceof Hero && steal((Hero) enemy)) {
            state = WANDERING;
            Dungeon.level.drop(new Food(), enemy.pos).sprite.drop();
            Buff.prolong(enemy, Onigiri.class, Onigiri.DURATION);
        }
        return damage;
    }

    protected boolean steal(Hero heroine) {

        Item toSteal = heroine.belongings.randomUnequipped();

        if (toSteal != null && !toSteal.unique && toSteal.level() < 1) {

            GLog.w(Messages.get(Minoriko.class, "stole", toSteal.name()));
            if (!toSteal.stackable) {
                Dungeon.quickslot.convertToPlaceholder(toSteal);
                Sample.INSTANCE.play( Assets.Sounds.CURSED );
            }
            Item.updateQuickslot();

            item = toSteal.detach(heroine.belongings.backpack);
            return true;
        } else {
            return false;
        }
    }
}