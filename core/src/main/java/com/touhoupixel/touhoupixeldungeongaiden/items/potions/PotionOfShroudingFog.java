package com.touhoupixel.touhoupixeldungeongaiden.items.potions;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeongaiden.actors.blobs.SmokeScreen;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Barrier;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class PotionOfShroudingFog extends Potion {

    {
        icon = ItemSpriteSheet.Icons.POTION_SHROUDFOG;
    }

    @Override
    public void shatter(int cell) {

        if (Dungeon.level.heroFOV[cell]) {
            identify();

            splash(cell);
            Sample.INSTANCE.play(Assets.Sounds.SHATTER);
            Sample.INSTANCE.play(Assets.Sounds.GAS);
        }

        int centerVolume = 180;
        for (int i : PathFinder.NEIGHBOURS8) {
            if (!Dungeon.level.solid[cell + i]) {
                GameScene.add(Blob.seed(cell + i, 180, SmokeScreen.class));
            } else {
                centerVolume += 180;
            }
        }

        GameScene.add(Blob.seed(cell, centerVolume, SmokeScreen.class));
    }

    @Override
    public int value() {
        return isKnown() ? 30 * quantity : super.value();
    }
}