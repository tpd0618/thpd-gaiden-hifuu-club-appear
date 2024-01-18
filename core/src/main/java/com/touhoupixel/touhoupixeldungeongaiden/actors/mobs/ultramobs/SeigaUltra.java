/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2018 Evan Debenham
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

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Amok;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.PotionFreeze;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Terror;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Seiga;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.SeigaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashSet;

public class SeigaUltra extends Seiga {

    {
        spriteClass = SeigaSprite.class;

        HP = HT = 71;
        defenseSkill = 20;
        EXP = 10;
        maxLvl = 27;

        flying = true;
        passWall = true;
        state = WANDERING;

        WANDERING = new Wandering();
        HUNTING = new Hunting();

        properties.add(Property.WARP);
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 10, 13 );
    }

    @Override
    public int attackSkill( Char target ) {
        return 25;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    protected Char chooseEnemy() {

        Terror terror = buff( Terror.class );
        if (terror != null) {
            Char source = (Char)Actor.findById( terror.object );
            if (source != null) {
                return source;
            }
        }

        //find a new enemy if..
        boolean newEnemy = false;
        //we have no enemy, or the current one is dead
        if ( enemy == null || !enemy.isAlive() || state == WANDERING)
            newEnemy = true;
            //We are an ally, and current enemy is another ally.
        else if (alignment == Alignment.ALLY && enemy.alignment == Alignment.ALLY)
            newEnemy = true;
            //We are amoked and current enemy is the hero
        else if (buff( Amok.class ) != null && enemy == Dungeon.heroine)
            newEnemy = true;

        if ( newEnemy ) {

            HashSet<Char> enemies = new HashSet<>();

            //if the mob is amoked...
            if ( buff(Amok.class) != null) {
                //try to find an enemy mob to attack first.
                for (Mob mob : Dungeon.level.mobs)
                    if (mob.alignment == Alignment.ENEMY && mob != this && distance(mob) <= viewDistance)
                        enemies.add(mob);

                if (enemies.isEmpty()) {
                    //try to find ally mobs to attack second.
                    for (Mob mob : Dungeon.level.mobs)
                        if (mob.alignment == Alignment.ALLY && mob != this && distance(mob) <= viewDistance)
                            enemies.add(mob);

                    if (enemies.isEmpty()) {
                        //try to find the hero third
                        if (distance(Dungeon.heroine) <= viewDistance) {
                            enemies.add(Dungeon.heroine);
                        }
                    }
                }

                //if the mob is an ally...
            } else if ( alignment == Alignment.ALLY ) {
                //look for hostile mobs that are not passive to attack
                for (Mob mob : Dungeon.level.mobs)
                    if (mob.alignment == Alignment.ENEMY
                            && distance(mob) <= viewDistance
                            && mob.state != mob.PASSIVE)
                        enemies.add(mob);

                //if the mob is an enemy...
            } else if (alignment == Alignment.ENEMY) {
                //look for ally mobs to attack
                for (Mob mob : Dungeon.level.mobs)
                    if (mob.alignment == Alignment.ALLY && distance(mob) <= viewDistance)
                        enemies.add(mob);

                //and look for the hero
                if (distance(Dungeon.heroine) <= viewDistance) {
                    enemies.add(Dungeon.heroine);
                }

            }

            //neutral character in particular do not choose enemies.
            if (enemies.isEmpty()){
                return null;
            } else {
                //go after the closest potential enemy, preferring the hero if two are equidistant
                Char closest = null;
                for (Char curr : enemies){
                    if (closest == null
                            || Dungeon.level.distance(pos, curr.pos) < Dungeon.level.distance(pos, closest.pos)
                            || Dungeon.level.distance(pos, curr.pos) == Dungeon.level.distance(pos, closest.pos) && curr == Dungeon.heroine){
                        closest = curr;
                    }
                }
                return closest;
            }

        } else
            return enemy;
    }

    public void safeRandomTarget(){
        ArrayList<Integer> canMove = new ArrayList<>();
        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            int p = pos + PathFinder.NEIGHBOURS8[i];
            if(Dungeon.level.insideMap( p)) {
                if (Actor.findChar(p) == null && (Dungeon.level.passable[p] || Dungeon.level.avoid[p] || Dungeon.level.solid[p])) {
                    canMove.add(p);
                }
            }
        }
        if(canMove.size() > 0)
            target = canMove.get(Random.Int( canMove.size() ));
    }

    private class Wandering extends Mob.Wandering{
        @Override
        public boolean act( boolean enemyInFOV, boolean justAlerted ) {
            if (enemyInFOV && (justAlerted || Random.Int((int) (distance( enemy ) / 2 + enemy.stealth())) == 0)) {

                enemySeen = true;

                alerted = true;
                state = HUNTING;
                target = enemy.pos;

            }else if(enemy != null){
                enemySeen = false;
                int oldPos = pos;
                ArrayList<Integer> canMove = new ArrayList<>();

                for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
                    int p = pos + PathFinder.NEIGHBOURS8[i];
                    if(p >= 0 && p < Dungeon.level.length() && !(p % Dungeon.level.width() == 0 || p % Dungeon.level.width() == Dungeon.level.width() -1 || p / Dungeon.level.width() == 0 || p / Dungeon.level.width() == Dungeon.level.height() -1)) {
                        if (Actor.findChar(p) == null && (Dungeon.level.passable[p] || Dungeon.level.avoid[p] || Dungeon.level.solid[p])) {
                            canMove.add(p);
                        }
                    }
                }

                if(!canMove.isEmpty()) {

                    int minDistance = 1000;

                    for (int i : canMove) {
                        if (Dungeon.level.distance(i, enemy.pos) <= minDistance) {
                            minDistance = Dungeon.level.distance(i, enemy.pos);
                        }
                    }

                    ArrayList<Integer> moveTo = new ArrayList<>();

                    for (int i : canMove) {
                        if (Dungeon.level.distance(i, enemy.pos) == minDistance) {
                            moveTo.add(i);
                        }
                    }

                    if (Random.Int(10) <= 7) {
                        target = moveTo.get(Random.Int(moveTo.size()));
                    } else target = canMove.get(Random.Int(canMove.size()));


                    if (target != -1 && getCloser(target)) {
                        spend(1 / speed());
                        return moveSprite(oldPos, pos);
                    } else spend(TICK);
                }else spend(TICK);
            }
            else {

                enemySeen = false;

                int oldPos = pos;
                if (target != -1 && getCloser( target )) {
                    spend( 1 / speed() );
                    return moveSprite( oldPos, pos );
                } else {
                    safeRandomTarget();
                }

            }
            return true;
        }
    }
    protected class Hunting extends Mob.Hunting {

        @Override
        public boolean act( boolean enemyInFOV, boolean justAlerted ) {
            enemySeen = enemyInFOV;
            if (enemyInFOV && !isCharmedBy( enemy ) && canAttack( enemy )) {

                return doAttack( enemy );

            } else {

                if (enemyInFOV) {
                    target = enemy.pos;
                } else if (enemy == null) {
                    state = WANDERING;
                    safeRandomTarget();
                    return true;
                }

                int oldPos = pos;
                if (target != -1 && getCloser( target )) {

                    spend( 1 / speed() );
                    return moveSprite( oldPos,  pos );

                } else {
                    spend( TICK );
                    if (!enemyInFOV) {
                        sprite.showLost();
                        state = WANDERING;
                        safeRandomTarget();
                    }
                    return true;
                }
            }
        }
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(3) == 0) {
            Dungeon.heroine.exp = 0;
            Sample.INSTANCE.play(Assets.Sounds.CURSED);
            GLog.w(Messages.get(this, "exp_reset"));
            if (Statistics.difficulty > 2) {
                Buff.prolong(enemy, PotionFreeze.class, PotionFreeze.DURATION);
            }
            if (Statistics.difficulty > 4) {
                Buff.prolong(enemy, Degrade.class, Degrade.DURATION);
            }
        }
        return damage;
    }
}