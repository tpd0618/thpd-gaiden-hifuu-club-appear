package com.touhoupixel.touhoupixeldungeongaiden.items.talismans;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.DoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Speck;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class YuumaTalisman extends Talisman {
    {
        image = ItemSpriteSheet.YUUMA;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );
        if (ch != null) {
            for (Buff bch : ch.buffs()) {
                if (bch.type == Buff.buffType.POSITIVE && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
                    bch.detach();
                    curUser.HP = Math.min(curUser.HP + 100000, curUser.HT);
                    Dungeon.heroine.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.2f, 3);
                    Buff.prolong(curUser, DoubleSpeed.class, DoubleSpeed.DURATION);
                }
            }
        }
    }
}