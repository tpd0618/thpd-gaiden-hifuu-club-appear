package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Bleeding;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Rumia;
import com.touhoupixel.touhoupixeldungeongaiden.items.Torch;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.RumiaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class RumiaUltra extends Rumia {

    {
        spriteClass = RumiaSprite.class;

        HP = HT = 315;
        defenseSkill = 35;
        EXP = 17;
        maxLvl = 75;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new Torch();
        lootChance = 0.05f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(30, 37);
    }

    @Override
    public int attackSkill(Char target) {
        return 50;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(5) == 0) {
            Buff.detach(enemy, Light.class);
            Buff.affect(enemy, Bleeding.class).set(8);
            Sample.INSTANCE.play( Assets.Sounds.CURSED );
            GLog.w(Messages.get(this, "rumia"));
        }
        return damage;
    }
}