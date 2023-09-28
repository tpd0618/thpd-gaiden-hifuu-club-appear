package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Hisou;
import com.touhoupixel.touhoupixeldungeongaiden.items.StrengthCard;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Food;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.YuyukoSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Yuyuko extends Mob {

    {
        spriteClass = YuyukoSprite.class;

        HP = HT = 217;
        defenseSkill = 32;
        EXP = 15;
        maxLvl = 45;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new Food();
        lootChance = 0.1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(22, 28);
    }

    @Override
    public int attackSkill(Char target) {
        return 42;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            Dungeon.heroine.STR--;
            Dungeon.level.drop(new StrengthCard(), Dungeon.level.randomRespawnCell(null)).sprite.drop();
            Sample.INSTANCE.play( Assets.Sounds.CURSED );
            GLog.w(Messages.get(Kanako.class, "str_reduce"));
        }
        return damage;
    }
}