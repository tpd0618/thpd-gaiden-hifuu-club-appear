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

package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Eiki;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.BlastParticle;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.EikiSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

public class EikiUltra extends Eiki {

    {
        spriteClass = EikiSprite.class;

        HP = HT = 380;
        defenseSkill = 40;
        EXP = 25;
        maxLvl = 99;

        state = WANDERING;

        properties.add(Property.GOD);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(Statistics.enemiesSlain/15, Statistics.enemiesSlain/13);
    }

    @Override
    public int attackSkill(Char target) {
        return 91;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    protected boolean act() {
        if (this.state != SLEEPING && this.state != FLEEING) {
            if (Dungeon.heroine.pos < this.pos || Dungeon.heroine.pos > this.pos){
                if (Random.Int(7) == 0) {
                    Dungeon.heroine.damage(25 - Dungeon.heroine.belongings.armor.DRMin(), this);
                    CellEmitter.center(Dungeon.heroine.pos).burst(BlastParticle.FACTORY, 4);
                    GLog.w(Messages.get(this, "abyss_dragon"));
                }
            }
        }
        return super.act();
    }
}