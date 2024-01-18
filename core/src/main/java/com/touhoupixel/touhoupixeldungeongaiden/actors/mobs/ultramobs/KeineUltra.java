package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.ultramobs;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Keine;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.PurityHerb;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.KeineSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class KeineUltra extends Keine {

    {
        spriteClass = KeineSprite.class;

        HP = HT = 39;
        defenseSkill = 12;
        EXP = 5;
        maxLvl = 20;

        properties.add(Property.ANIMAL);

        loot = new PurityHerb();
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 8);
    }

    @Override
    public int attackSkill(Char target) {
        return 64;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            Artifact artifact = Dungeon.heroine.belongings.getItem(Artifact.class);
            if (artifact != null) {
                artifact.charge = 0;
                Item.updateQuickslot();
                Sample.INSTANCE.play(Assets.Sounds.CURSED);
                GLog.w(Messages.get(ReimuUltra.class, "artifact_charge_lost"));
            }
        }
        return damage;
    }
}