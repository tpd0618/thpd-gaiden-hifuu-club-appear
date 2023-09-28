package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.sun.tools.javac.jvm.Items;
import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Doublerainbow;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.ExtremeConfusion;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ReisenSprite;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Reisen extends Mob {

    Items items;

    {
        spriteClass = ReisenSprite.class;

        HP = HT = 49;
        defenseSkill = 12;
        EXP = 5;
        maxLvl = 20;

        properties.add(Property.ANIMAL);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = Generator.Category.POTION;
        lootChance = 0.15f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(5, 9);
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
    public int attackProc(Char hero, int damage) {
        damage = super.attackProc(enemy, damage);
        if (enemy == Dungeon.heroine && enemy.alignment != this.alignment) {
            ArrayList<Item> gazer = new ArrayList<>();
            for (Item i : Dungeon.heroine.belongings) {
                if (!i.unique && (i instanceof Potion || i instanceof Herb)) {
                    gazer.add(i);
                }
            }
            if (Random.Int(4) == 0) {
                if (!gazer.isEmpty()) {
                    Item hypnotize = Random.element(gazer).detach(Dungeon.heroine.belongings.backpack);
                    GLog.w(Messages.get(this, "gaze"));
                    hero.sprite.emitter().start(Speck.factory(Speck.BUBBLE), 0.2f, 3);
                    Sample.INSTANCE.play(Assets.Sounds.LULLABY);
                    if (hypnotize instanceof Potion) {
                        ((Potion) hypnotize).drink((Hero) hero);
                    }
                    if (hypnotize instanceof Herb) {
                        hypnotize.execute((Hero) hero);
                    }
                } else {
                    GLog.w(Messages.get(this, "failtogaze"));
                }
            }
        }
        return damage;
    }
}