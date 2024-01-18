package com.touhoupixel.touhoupixeldungeongaiden.items.scrolls;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.Statistics;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.*;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.npcs.Rinnosuke;
import com.touhoupixel.touhoupixeldungeongaiden.effects.Identification;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfNamelessStory extends Scroll {


	{
		icon = ItemSpriteSheet.Icons.SCROLL_NS;
	}

	@Override
	public void doRead() {
		curUser.sprite.parent.add(new Identification(curUser.sprite.center().offset(0, -16)));
		GLog.w(Messages.get(this, "throw_to_use"));
		Sample.INSTANCE.play(Assets.Sounds.READ);
		identify();
		readAnimation();
	}

	@Override
	public void onThrow( int cell ) {
		Heap heap = Dungeon.level.drop( this, cell );
		Char ch = (Char) Actor.findChar(cell);
		if (!heap.isEmpty() && ch != null && ch != Dungeon.heroine) {
			identify();
			Sample.INSTANCE.play(Assets.Sounds.BLAST);

			if (!ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS) && !(ch instanceof Rinnosuke) && !ch.properties().contains(Char.Property.MITAMA) && !ch.properties().contains(Char.Property.NOT_EXTERMINABLE)) {
				GLog.i(Messages.get(this, "target_exterminate"));
				Statistics.exterminatedEnemy = Actor.findChar(cell).getClass();
				Mob mob = (Mob) ch;
				mob.exterminate();
				mob.sprite.killAndErase();
			} else {
				GLog.w(Messages.get(this, "cant_exterminate"));
			}

			Heap[] heaps = new Heap[1];
			heaps[0] = Dungeon.level.heaps.get(ch.pos);
			for (Heap h : heaps) {
				for (Item i : h.items.toArray(new Item[0])){
					if (i == this){
						h.remove(i);
					}
				}
			}
		} else {
			heap.sprite.drop( cell );
		}
	}

	@Override
	public Item identify(boolean byHero) {
		defaultAction = AC_THROW;
		return super.identify(byHero);
	}

	@Override
	public int value() {
		return isKnown() ? 40 * quantity : super.value();
	}
}
