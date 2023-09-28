package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.HinaCurse;
import com.touhoupixel.touhoupixeldungeongaiden.effects.TargetedCell;
import com.touhoupixel.touhoupixeldungeongaiden.items.spells.ReclaimTrap;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.TewiSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

public class Tewi extends Mob {

    {
        spriteClass = TewiSprite.class;

        HP = HT = 45;
        defenseSkill = 12;
        EXP = 5;
        maxLvl = 20;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new ReclaimTrap();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 6);
    }

    @Override
    public int attackSkill(Char target) {
        return 17;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public void die(Object cause) {
        if (this.pos - 1 == Dungeon.heroine.pos ||
            this.pos + 1 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width() == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width() == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width() - 1 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width() + 1 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width() - 1 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width() + 1 == Dungeon.heroine.pos ||
            this.pos - 2 == Dungeon.heroine.pos ||
            this.pos + 2 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*2 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*2 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*2 - 2 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*2 + 2 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*2 - 2 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*2 + 2 == Dungeon.heroine.pos ||
            this.pos - 3 == Dungeon.heroine.pos ||
            this.pos + 3 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*3 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*3 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*3 - 3 == Dungeon.heroine.pos ||
            this.pos - Dungeon.level.width()*3 + 3 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*3 - 3 == Dungeon.heroine.pos ||
            this.pos + Dungeon.level.width()*3 + 3 == Dungeon.heroine.pos) {
            Dungeon.heroine.damage(8, this);
            GLog.w(Messages.get(this, "star_damage"));
        }

        sprite.parent.addToBack(new TargetedCell(this.pos - 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width(), 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width(), 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width() - 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width() + 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width() - 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width() + 1, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2 - 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*2 + 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2 - 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*2 + 2, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3 - 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos - Dungeon.level.width()*3 + 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3 - 3, 0xFF0000));
        sprite.parent.addToBack(new TargetedCell(this.pos + Dungeon.level.width()*3 + 3, 0xFF0000));
        super.die(cause);
    }
}