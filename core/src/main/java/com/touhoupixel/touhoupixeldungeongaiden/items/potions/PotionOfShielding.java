package com.touhoupixel.touhoupixeldungeongaiden.items.potions;

import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Barrier;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;

public class PotionOfShielding extends Potion {

    {
        icon = ItemSpriteSheet.Icons.POTION_SHIELDING;
    }

    @Override
    public void apply(Hero heroine) {
        identify();
        Buff.affect(heroine, Barrier.class).setShield((int) (0.6f * heroine.HT + 10));
    }

    @Override
    public int value() {
        return isKnown() ? 30 * quantity : super.value();
    }
}