package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Might;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.YuumaAbsorb;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.LifeFragment;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YuumaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Yuuma extends Mob {

    {
        spriteClass = YuumaSprite.class;

        HP = HT = 358;
        defenseSkill = 40;
        EXP = 25;
        maxLvl = 99;

        properties.add(Property.YOKAI);

        loot = new LifeFragment();
        lootChance = 0.05f;
    }

    @Override
    protected boolean act() {
        boolean result = super.act();
        for (int i : PathFinder.NEIGHBOURS8) {
            for (Buff b : this.buffs()) {
                if (b.type == Buff.buffType.NEGATIVE) {
                    b.detach();
                    this.HP = this.HT;
                    this.sprite.emitter().burst(Speck.factory(Speck.HEALING), 6);
                    Buff.prolong(this, YuumaAbsorb.class, YuumaAbsorb.DURATION);
                    Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 100f);
                    if (Statistics.difficulty > 2) {
                        Buff.prolong(this, Might.class, Might.DURATION);
                    }
                    if (Statistics.difficulty > 4) {
                        Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION);
                    }
                    GLog.w(Messages.get(this, "absorb"));
                }
            }
            for (Buff c : Dungeon.heroine.buffs()) {
                if (c.type == Buff.buffType.POSITIVE && Dungeon.heroine.pos == this.pos + i) {
                    c.detach();
                    this.HP = this.HT;
                    this.sprite.emitter().burst(Speck.factory(Speck.HEALING), 6);
                    Buff.prolong(this, YuumaAbsorb.class, YuumaAbsorb.DURATION);
                    Buff.prolong(this, DoubleSpeed.class, DoubleSpeed.DURATION * 100f);
                    if (Statistics.difficulty > 2) {
                        Buff.prolong(this, Might.class, Might.DURATION);
                    }
                    if (Statistics.difficulty > 4) {
                        Buff.prolong(this, Doublerainbow.class, Doublerainbow.DURATION);
                    }
                    GLog.w(Messages.get(this, "absorb"));
                }
            }
        }
        return result;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(34, 39);
    }

    @Override
    public int attackSkill(Char target) {
        return 75;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }
}