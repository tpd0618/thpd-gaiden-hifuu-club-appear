package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Kogasa;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfFear;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KogasaSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class KogasaUltra extends Kogasa {

    {
        spriteClass = KogasaSprite.class;

        HP = HT = 9;
        defenseSkill = 2;
        EXP = 1;
        maxLvl = 10;

        properties.add(Property.YOKAI);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new StoneOfFear();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 4);
    }

    @Override
    public int attackSkill(Char target) {
        return 55;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment && Random.Int(4) == 0) {
            damage *= 3f;
            Sample.INSTANCE.play( Assets.Sounds.BLAST );
            GLog.w(Messages.get(this, "punisher"));
            Camera.main.shake( 20, 1f );
        }
        return damage;
    }
} //todo