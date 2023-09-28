package com.touhoupixel.touhoupixeldungeongaiden.actors.mobs;

import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.HeartHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.itemstats.SpellcardFragment;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.FutoSprite;
import com.watabou.utils.Random;

public class Futo extends Mob {

    {
        spriteClass = FutoSprite.class;

        HP = HT = 79;
        defenseSkill = 22;
        EXP = 14;
        maxLvl = 30;

        state = WANDERING;

        properties.add(Property.WARP);

        properties.add(Property.FUMO);
        //used for fumo lover buff

        loot = new SpellcardFragment();
        lootChance = 0.01f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(13, 19);
    }

    @Override
    public int attackSkill(Char target) {
        return 27;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 2);
    }

    @Override
    protected boolean act() {
        Heap heap = Dungeon.level.heaps.get(pos);
        Item newItem = new HeartHerb();
        if (heap != null && this.pos == heap.pos) {

            Heap[] equipHeaps = new Heap[1];
            equipHeaps[0] = Dungeon.level.heaps.get(this.pos);
            for (Heap h : equipHeaps) {
                for (Item i : h.items.toArray(new Item[0])){
                    if (!i.unique){
                        h.remove(i);
                    }
                }
            }
            Dungeon.level.drop(newItem, heap.pos).sprite.drop();
        }
        return super.act();
    }
}