/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2023 Evan Debenham
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

package com.touhoupixel.touhoupixeldungeongaiden.journal;

import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSprite;
import com.touhoupixel.touhoupixeldungeongaiden.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeongaiden.ui.Icons;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.DeviceCompat;

import java.util.Collection;
import java.util.LinkedHashMap;

public enum Document {

	ADVENTURERS_GUIDE(ItemSpriteSheet.GUIDE_PAGE),
	ALCHEMY_GUIDE(ItemSpriteSheet.ALCH_PAGE),

	INTROS(Icons.STAIRS);

	Document( int sprite){
		pageIcon = null;
		pageSprite = sprite;
	}

	Document( Icons icon){
		pageIcon = icon;
		pageSprite = 0;
	}

	public static final int NOT_FOUND = 0;
	public static final int FOUND = 1;
	public static final int READ = 2;
	private LinkedHashMap<String, Integer> pagesStates = new LinkedHashMap<>();

	public boolean findPage( String page ) {
		if (pagesStates.containsKey(page) && pagesStates.get(page) == NOT_FOUND){
			pagesStates.put(page, FOUND);
			Journal.saveNeeded = true;
			return true;
		}
		return false;
	}

	public boolean isPageFound( String page ){
		return pagesStates.containsKey(page) && pagesStates.get(page) > NOT_FOUND;
	}

	public boolean readPage( String page ) {
		if (pagesStates.containsKey(page)){
			pagesStates.put(page, READ);
			Journal.saveNeeded = true;
			return true;
		}
		return false;
	}

	public boolean readPage( int pageIdx ) {
		return readPage( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public boolean isPageRead( String page ){
		return pagesStates.containsKey(page) && pagesStates.get(page) == READ;
	}

	public boolean isPageRead( int pageIdx ){
		return isPageRead( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public Collection<String> pageNames(){
		return pagesStates.keySet();
	}

	public int pageIdx(String name){
		int i = 0;
		for( String page : pagesStates.keySet()){
			if (page.equals(name)){
				return i;
			}
			i++;
		}
		return -1;
	}

	private int pageSprite;
	private Icons pageIcon;

	public Image pageSprite(String page){
		if (page.isEmpty() || !isPageFound(page) || this != ADVENTURERS_GUIDE){
			if (pageIcon != null){
				return Icons.get(pageIcon);
			} else {
				return new ItemSprite(pageSprite);
			}
		} else {
			switch (page){
				default:
				case "1":
					return new ItemSprite( ItemSpriteSheet.RING_EMERALD );
				case "2":
					return new ItemSprite( ItemSpriteSheet.RING_AGATE );
				case "3":
					return new ItemSprite( ItemSpriteSheet.RING_DIAMOND );
			}
		}
	}

	public String title(){
		return Messages.get( this, name() + ".title");
	}

	public String pageTitle( String page ){
		return Messages.get( this, name() + "." + page + ".title");
	}

	public String pageTitle( int pageIdx ){
		return pageTitle( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public String pageBody( String page ){
		return Messages.get( this, name() + "." + page + ".body");
	}

	public String pageBody( int pageIdx ){
		return pageBody( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public static final String GUIDE_INTRO          = "Intro";
	public static final String GUIDE_IDING          = "Identifying";
	public static final String GUIDE_FOOD           = "Food";
	public static final String GUIDE_SEARCHING      = "Searching";

	//pages and default states
	static {
		boolean debug = DeviceCompat.isDebug();
		ADVENTURERS_GUIDE.pagesStates.put("1", debug ? READ : FOUND);
		ADVENTURERS_GUIDE.pagesStates.put("2", debug ? READ : FOUND);
		ADVENTURERS_GUIDE.pagesStates.put("3", debug ? READ : FOUND);//todo

		ALCHEMY_GUIDE.pagesStates.put("1", debug ? READ : FOUND);
	}

	private static final String DOCUMENTS = "documents";

	public static void store( Bundle bundle ){

		Bundle docsBundle = new Bundle();

		for ( Document doc : values()){
			Bundle pagesBundle = new Bundle();
			boolean empty = true;
			for (String page : doc.pageNames()){
				if (doc.pagesStates.get(page) != NOT_FOUND){
					pagesBundle.put(page, doc.pagesStates.get(page));
					empty = false;
				}
			}
			if (!empty){
				docsBundle.put(doc.name(), pagesBundle);
			}
		}

		bundle.put( DOCUMENTS, docsBundle );

	}

	public static void restore( Bundle bundle ){

		if (!bundle.contains( DOCUMENTS )){
			return;
		}

		Bundle docsBundle = bundle.getBundle( DOCUMENTS );

		for ( Document doc : values()){
			if (docsBundle.contains(doc.name())){
				Bundle pagesBundle = docsBundle.getBundle(doc.name());

				for (String page : doc.pageNames()) {
					if (pagesBundle.contains(page)) {
						doc.pagesStates.put(page, pagesBundle.getInt(page));
					}
				}
			}
		}
	}
}