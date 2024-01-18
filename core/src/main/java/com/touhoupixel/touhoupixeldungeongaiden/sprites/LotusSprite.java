/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeongaiden.sprites;

import com.touhoupixel.touhoupixeldungeongaiden.Assets;
import com.touhoupixel.touhoupixeldungeongaiden.Dungeon;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeongaiden.effects.particles.LeafParticle;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.particles.Emitter;

import java.util.ArrayList;

public class LotusSprite extends MobSprite {

	private ArrayList<Emitter> grassVfx;

	public LotusSprite(){
		super();

		perspectiveRaise = 0f;

		texture( Assets.Sprites.LOTUS );

		TextureFilm frames = new TextureFilm( texture, 19, 16 );

		idle = new Animation( 1, true );
		idle.frames( frames, 0 );

		run = new Animation( 1, true );
		run.frames( frames, 0 );

		attack = new Animation( 1, false );
		attack.frames( frames, 0 );

		die = new Animation( 1, false );
		die.frames( frames, 0 );

		play( idle );
	}

	@Override
	public void link( Char ch ) {
		super.link( ch );

		renderShadow = false;
	}

	@Override
	public void place(int cell) {
		if (parent != null) parent.sendToBack(this);
		super.place(cell);
	}

	@Override
	public void turnTo(int from, int to) {
		//do nothing
	}

	@Override
	public void update() {
		visible = true;
		super.update();
	}

	@Override
	public void die() {
		super.die();

		if (grassVfx != null){
			for (Emitter e : grassVfx){
				e.on = false;
			}
			grassVfx = null;
		}
	}

	@Override
	public void kill() {
		super.kill();

		if (grassVfx != null){
			for (Emitter e : grassVfx){
				e.on = false;
			}
			grassVfx = null;
		}
	}
}
