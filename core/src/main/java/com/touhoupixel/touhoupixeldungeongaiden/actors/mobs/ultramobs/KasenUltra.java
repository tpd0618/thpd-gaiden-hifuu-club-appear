package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Kasen;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KasenSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.utils.Random;

public class KasenUltra extends Kasen {

    {
        spriteClass = KasenSprite.class;

        HP = HT = 70;
        defenseSkill = 20;
        EXP = 11;
        maxLvl = 27;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        //todo
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(11, 16);
    }

    @Override
    public int attackSkill(Char target) {
        return 58;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc( Char hero, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Armor armor = Dungeon.heroine.belongings.armor();
            if (armor != null) {
                Dungeon.heroine.belongings.armor = null;
                Dungeon.quickslot.clearItem(armor);
                Item.updateQuickslot();

                Ballistica trajectory = new Ballistica(this.pos, enemy.pos, Ballistica.STOP_TARGET);
                trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);

                Char ch = (Char) Actor.findChar(trajectory.collisionPos);
                if (ch != null && ch != Dungeon.heroine && trajectory.collisionPos.equals(ch.pos)) {
                    ch.damage(Dungeon.heroine.STR*2+armor.level()*3, armor);
                    GLog.w(Messages.get(this, "armor_blow_away_and_destroy"));
                } else {
                    armor.onThrow(trajectory.collisionPos);
                    GLog.w(Messages.get(this, "armor_blow_away"));
                }
            }
        }
        return damage;
    }
}