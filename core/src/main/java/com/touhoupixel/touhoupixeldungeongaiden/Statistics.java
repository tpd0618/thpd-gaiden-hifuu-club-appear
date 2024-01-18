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

package com.touhoupixel.touhoupixeldungeongaiden;

import com.watabou.utils.Bundle;
import com.watabou.utils.SparseArray;

public class Statistics {

	public static int goldCollected;
	public static int goldPickedup;
	public static int highestFloor;
	public static int highestAscent;
	public static int enemiesSlain;
	public static int foodEaten;
	public static int potionsCooked;
	public static int itemsCrafted;
	public static int murasasKilled;
	public static int toyohimesKilled;
	public static int yorihimesKilled;
	public static int kisumesKilled;
	public static int shopkeepersKilled;
	public static int moon_Count;
	public static int trap_act_count;
	public static int bamboo;
	public static int nameless;
	public static int chimata;
	public static int floor30;

	//These are used for score calculation
	//some are built incrementally, most are assigned when full score is calculated
	public static int progressScore;
	public static int heldItemValue;
	public static int treasureScore;
	public static SparseArray<Boolean> floorsExplored = new SparseArray<>();
	public static int exploreScore;
	public static float chalMultiplier;
	public static int totalScore;

	//used for hero unlock badges
	public static int upgradesUsed;
	public static int sneakAttacks;
	public static int thrownAssists;

	public static int cardDraw;
	public static int cardDrawalt;

	public static int timeReset;
	public static int tenshiEarthquake;
	public static int bordercount;

	public static int maxHP_change;
	public static int life_count;
	public static int bomb_count;
	public static int dismantle_count;

	public static int seija_roulette;

	public static float duration;

	public static int powerfulres;
	public static int coolres;
	public static int pureres;
	public static int happyres;

	public static int randomencountertrack;
	public static int timetrackbuff;
	public static int timetrackstrup;

	public static int power;
	public static int value;
	public static int life;
	public static int lifefragment;
	public static int spellcard;
	public static int spellcardfragment;

	public static int tenshiattackstep;
	public static int eirinelixircount;

	public static int nextvalue;

	public static int lifefragmentkill;
	public static int spellcardfragmentkill;
	public static int hitoricount;
	public static int hitorilefttime;

	public static int difficulty;

	public static int double_speed_upgrade;
	public static int extermination_number;

	public static boolean identify_use;
	public static boolean fate_use;
	public static boolean earth_use;
	public static boolean exorcism_use;
	public static boolean transmute_use;

	public static boolean elixir_trigger = false;

	public static boolean remi_countdown = false;

	public static boolean qualifiedForNoKilling = false;
	public static boolean completedWithNoKilling = false;

	public static boolean scorelife1 = false;
	public static boolean scorelife2 = false;
	public static boolean scorelife3 = false;
	public static boolean scorelife4 = false;
	public static boolean scorelife5 = false;
	public static boolean scorelife6 = false;
	public static boolean scorelife7 = false;
	public static boolean scorelife8 = false;

	public static boolean lifelose = false;
	public static boolean spellcarduse = false;
	public static boolean torch_use = false;

	public static Class exterminatedEnemy;

	public static void reset() {

		goldCollected	= 0;
		goldPickedup	= 0;
		highestFloor = 0;
		highestAscent	= 0;
		enemiesSlain	= 0;
		foodEaten		= 0;
		potionsCooked	= 0;
		itemsCrafted    = 0;
		murasasKilled = 0;
		toyohimesKilled = 0;
		yorihimesKilled = 0;
		kisumesKilled   = 0;
		shopkeepersKilled = 0;
		moon_Count = 0;
		trap_act_count = 0;
		bamboo = 0;
		nameless = 0;
		chimata = 0;
		floor30 = 0;

		progressScore   = 0;
		heldItemValue   = 0;
		treasureScore   = 0;
		floorsExplored  = new SparseArray<>();
		exploreScore    = 0;
		chalMultiplier  = 1;
		totalScore      = 0;

		upgradesUsed    = 0;
		sneakAttacks    = 0;
		thrownAssists   = 0;

		cardDraw   = 0;
		cardDrawalt   = 0;

		timeReset = 0;
		tenshiEarthquake = 0;
		bordercount = 0;

		maxHP_change = 0;
		life_count = 0;
		bomb_count = 0;
		dismantle_count = 0;

		seija_roulette = 5;

		duration	= 0;

		powerfulres = 0;
		coolres = 0;
		pureres = 0;
		happyres = 0;

		randomencountertrack = 0;
		timetrackbuff = 0;
		timetrackstrup = 0;

		power = 100;
		value = 0;
		life = 2;
		lifefragment = 0;
		spellcard = 3;
		spellcardfragment = 0;

		tenshiattackstep = 0;
		eirinelixircount = 0;

		nextvalue = 500;

		lifefragmentkill = 0;
		spellcardfragmentkill = 0;

		hitoricount = 0;
		hitorilefttime = 0;

		difficulty = 1; //easy

		double_speed_upgrade = 0;
		extermination_number = 0;

		identify_use = false;
		fate_use = false;
		earth_use = false;
		exorcism_use = false;
		transmute_use = false;

		elixir_trigger = false;

		remi_countdown = false;

		qualifiedForNoKilling = false;

		scorelife1 = false;
		scorelife2 = false;
		scorelife3 = false;
		scorelife4 = false;
		scorelife5 = false;
		scorelife6 = false;
		scorelife7 = false;
		scorelife8 = false;

		lifelose = false;
		spellcarduse = false;
		torch_use = false;

		exterminatedEnemy = null;
	}

	private static final String GOLD		= "score";
	private static final String GOLDPICKEDUP		= "goldpickedup";
	private static final String DEEPEST		= "maxDepth";
	private static final String HIGHEST		= "maxAscent";
	private static final String SLAIN		= "enemiesSlain";
	private static final String FOOD		= "foodEaten";
	private static final String ALCHEMY		= "potionsCooked";
	private static final String TOYOHIMES	= "toyohimeskilled";
	private static final String YORIHIMES	= "yorihimeskilled";
	private static final String KISUMES	    = "kisumeskilled";
	private static final String SHOPKEEPERS	= "shopkeeperskilled";
	private static final String MURASAS = "priranhas";
	private static final String MOON_COUNT		= "moon_count";
	private static final String TRAP_ACT_COUNT		= "trap_act_count";
	private static final String BAMBOO		= "bamboo";
	private static final String NAMELESS		= "nameless";
	private static final String CHIMATA		= "chimata";
	private static final String FLOOR30		= "floor30";

	private static final String PROG_SCORE	    = "prog_score";
	private static final String ITEM_VAL	    = "item_val";
	private static final String TRES_SCORE      = "tres_score";
	private static final String FLR_EXPL        = "flr_expl";
	private static final String EXPL_SCORE      = "expl_score";
	private static final String CHAL_MULT		= "chal_mult";
	private static final String TOTAL_SCORE		= "total_score";

	private static final String UPGRADES	= "upgradesUsed";
	private static final String SNEAKS		= "sneakAttacks";
	private static final String THROWN		= "thrownAssists";

	private static final String CARDDRAW		= "cardDraw";
	private static final String CARDDRAWALT		= "cardDrawalt";

	private static final String TIMERESET		= "timeReset";
	private static final String TENSHIEARTHQUAKE		= "tenshiearthquake";
	private static final String BORDERCOUNT		= "bordercount";

	private static final String MAXHP_DOWN		= "maxhp_down";
	private static final String LIFE_COUNT = "life_count";
	private static final String BOMB_COUNT = "bomb_count";
	private static final String DISMANTLE_COUNT = "dismantle_count";

	private static final String SEIJA_ROULETTE = "seija_roulette";

	private static final String DURATION	= "duration";

	private static final String POWERFULRES	= "powerfulres";
	private static final String COOLRES	= "coolres";
	private static final String PURERES	= "pureres";
	private static final String HAPPYRES	= "happyres";

	private static final String RANDOMENCOUNTERTRACK	= "randomencountertrack";
	private static final String TIMETRACKBUFF	= "timetrackbuff";
	private static final String TIMETRACKSTRUP	= "timetrackstrup";

	private static final String POWER	= "power";
	private static final String VALUE	= "value";
	private static final String LIFE	= "life";
	private static final String LIFEFRAGMENT	= "lifefragment";
	private static final String SPELLCARD	= "spellcard";
	private static final String SPELLCARDFRAGMENT	= "spellcardfragment";

	private static final String TENSHIATTACKSTEP	= "tenshiattackstep";
	private static final String EIRINELIXIRCOUNT	= "eirinelixircount";

	private static final String NEXTVALUE	= "nextvalue";

	private static final String LIFEFRAGMENTKILL	= "lifefragmentkill";
	private static final String SPELLCARDFRAGMENTKILL	= "spellcardfragmentkill";

	private static final String HITORICOUNT	= "hitoricount";
	private static final String HITORILEFTTIME	= "hitorilefttime";

	private static final String DIFFICULTY	= "difficulty";

	private static final String DOUBLE_SPEED_UPGRADE	= "double_speed_upgrade";
	private static final String EXTERMINATION_NUMBER	= "extermination_number";

	private static final String IDENTIFY_USE	= "identify_use";
	private static final String FATE_USE	= "fate_use";
	private static final String EARTH_USE	= "earth_use";
	private static final String EXORCISM_USE	= "exorcism_use";
	private static final String TRANSMUTE_USE	= "transmute_use";

	private static final String ELIXIRTRIGGER	= "elixirtrigger";

	private static final String REMI_COUNTDOWN	= "remi_countdown";

	private static final String NO_KILLING_QUALIFIED	= "qualifiedForNoKilling";

	private static final String SCORELIFE1		= "scorelife1";
	private static final String SCORELIFE2		= "scorelife2";
	private static final String SCORELIFE3		= "scorelife3";
	private static final String SCORELIFE4		= "scorelife4";
	private static final String SCORELIFE5		= "scorelife5";
	private static final String SCORELIFE6		= "scorelife6";
	private static final String SCORELIFE7		= "scorelife7";
	private static final String SCORELIFE8		= "scorelife8";

	private static final String LIFELOSE		= "lifelose";
	private static final String SPELLCARDUSE		= "spellcarduse";
	private static final String TORCH_USE		= "torch_use";

	private static final String EXTERMINATED_ENEMY		= "exterminated_enemy";

	public static void storeInBundle( Bundle bundle ) {
		bundle.put( GOLD,		goldCollected );
		bundle.put( GOLDPICKEDUP,		goldPickedup );
		bundle.put( DEEPEST, highestFloor);
		bundle.put( HIGHEST,	highestAscent );
		bundle.put( SLAIN,		enemiesSlain );
		bundle.put( FOOD,		foodEaten );
		bundle.put( ALCHEMY,    itemsCrafted );
		bundle.put( MURASAS, murasasKilled);
		bundle.put( TOYOHIMES,	toyohimesKilled );
		bundle.put( YORIHIMES,	yorihimesKilled );
		bundle.put( SHOPKEEPERS,shopkeepersKilled);
		bundle.put( KISUMES,	kisumesKilled );
		bundle.put( MOON_COUNT, moon_Count);
		bundle.put( TRAP_ACT_COUNT, trap_act_count);
		bundle.put( BAMBOO, bamboo);
		bundle.put( NAMELESS, nameless);
		bundle.put( CHIMATA, chimata);
		bundle.put( FLOOR30, floor30);

		bundle.put( PROG_SCORE,  progressScore );
		bundle.put( ITEM_VAL,    heldItemValue );
		bundle.put( TRES_SCORE,  treasureScore );
		for (int i = 1; i < 51; i++){
			if (floorsExplored.containsKey(i)){
				bundle.put( FLR_EXPL+i, floorsExplored.get(i) );
			}
		}
		bundle.put( EXPL_SCORE,  exploreScore );
		bundle.put( CHAL_MULT,   chalMultiplier );
		bundle.put( TOTAL_SCORE, totalScore );

		bundle.put( UPGRADES,   upgradesUsed );
		bundle.put( SNEAKS,		sneakAttacks );
		bundle.put( THROWN,		thrownAssists );

		bundle.put( CARDDRAW,		cardDraw );
		bundle.put( CARDDRAWALT,		cardDrawalt );

		bundle.put( TIMERESET,		timeReset );
		bundle.put( TENSHIEARTHQUAKE,		tenshiEarthquake );
		bundle.put( BORDERCOUNT,		bordercount );

		bundle.put( MAXHP_DOWN, maxHP_change);
		bundle.put( LIFE_COUNT, life_count);
		bundle.put( BOMB_COUNT, bomb_count);
		bundle.put( DISMANTLE_COUNT, dismantle_count);

		bundle.put( SEIJA_ROULETTE, seija_roulette);

		bundle.put( DURATION,	duration );

		bundle.put( POWERFULRES,powerfulres );
		bundle.put( COOLRES,	coolres );
		bundle.put( PURERES,	pureres );
		bundle.put( HAPPYRES,	happyres );

		bundle.put( RANDOMENCOUNTERTRACK,	randomencountertrack );
		bundle.put( TIMETRACKBUFF,	timetrackbuff );
		bundle.put( TIMETRACKSTRUP,	timetrackstrup );

		bundle.put( POWER,	power );
		bundle.put( VALUE,	value );
		bundle.put( LIFE,	life );
		bundle.put( LIFEFRAGMENT,	lifefragment );
		bundle.put( SPELLCARD,	spellcard );
		bundle.put( SPELLCARDFRAGMENT,	spellcardfragment );

		bundle.put( TENSHIATTACKSTEP, tenshiattackstep);
		bundle.put( EIRINELIXIRCOUNT, eirinelixircount);

		bundle.put( NEXTVALUE,	nextvalue );

		bundle.put( LIFEFRAGMENTKILL,	lifefragmentkill );
		bundle.put( SPELLCARDFRAGMENTKILL,	spellcardfragmentkill );

		bundle.put( HITORICOUNT,	hitoricount );
		bundle.put( HITORILEFTTIME,	hitorilefttime );

		bundle.put( DIFFICULTY,	difficulty );

		bundle.put( IDENTIFY_USE,	identify_use );
		bundle.put( FATE_USE,	fate_use );
		bundle.put( EARTH_USE,	earth_use );
		bundle.put( EXORCISM_USE,	exorcism_use );
		bundle.put( TRANSMUTE_USE,	transmute_use );

		bundle.put( DOUBLE_SPEED_UPGRADE,	double_speed_upgrade );
		bundle.put( EXTERMINATION_NUMBER,	extermination_number );

		bundle.put( ELIXIRTRIGGER, elixir_trigger);

		bundle.put( REMI_COUNTDOWN, remi_countdown);

		bundle.put( NO_KILLING_QUALIFIED, qualifiedForNoKilling );

		bundle.put( SCORELIFE1,	scorelife1 );
		bundle.put( SCORELIFE2,	scorelife2 );
		bundle.put( SCORELIFE3,	scorelife3 );
		bundle.put( SCORELIFE4,	scorelife4 );
		bundle.put( SCORELIFE5,	scorelife5 );
		bundle.put( SCORELIFE6,	scorelife6 );
		bundle.put( SCORELIFE7,	scorelife7 );
		bundle.put( SCORELIFE8,	scorelife8 );

		bundle.put( LIFELOSE,	lifelose );
		bundle.put( SPELLCARDUSE,	spellcarduse );
		bundle.put( TORCH_USE, torch_use);

		bundle.put( EXTERMINATED_ENEMY, exterminatedEnemy );
	}

	public static void restoreFromBundle( Bundle bundle ) {
		goldCollected	= bundle.getInt( GOLD );
		goldPickedup	= bundle.getInt( GOLDPICKEDUP );
		highestFloor = bundle.getInt( DEEPEST );
		highestAscent   = bundle.getInt( HIGHEST );
		enemiesSlain	= bundle.getInt( SLAIN );
		foodEaten		= bundle.getInt( FOOD );
		itemsCrafted    = bundle.getInt( ALCHEMY );
		toyohimesKilled	= bundle.getInt( TOYOHIMES );
		yorihimesKilled	= bundle.getInt( YORIHIMES );
		kisumesKilled	= bundle.getInt( KISUMES );
		shopkeepersKilled	= bundle.getInt( SHOPKEEPERS );
		murasasKilled = bundle.getInt(MURASAS);
		moon_Count = bundle.getInt( MOON_COUNT );
		trap_act_count = bundle.getInt( TRAP_ACT_COUNT );
		bamboo = bundle.getInt( BAMBOO );
		nameless = bundle.getInt( NAMELESS );
		chimata = bundle.getInt( CHIMATA );
		floor30 = bundle.getInt( FLOOR30 );

		progressScore   = bundle.getInt( PROG_SCORE );
		heldItemValue   = bundle.getInt( ITEM_VAL );
		treasureScore   = bundle.getInt( TRES_SCORE );
		floorsExplored.clear();
		for (int i = 1; i < 99; i++){
			if (bundle.contains( FLR_EXPL+i )){
				floorsExplored.put(i, bundle.getBoolean( FLR_EXPL+i ));
			}
		}
		exploreScore    = bundle.getInt( EXPL_SCORE );
		chalMultiplier  = bundle.getFloat( CHAL_MULT );
		totalScore      = bundle.getInt( TOTAL_SCORE );

		upgradesUsed    = bundle.getInt( UPGRADES );
		sneakAttacks    = bundle.getInt( SNEAKS );
		thrownAssists   = bundle.getInt( THROWN );

		cardDraw   = bundle.getInt( CARDDRAW );
		cardDrawalt   = bundle.getInt( CARDDRAWALT );

		timeReset   = bundle.getInt( TIMERESET );
		tenshiEarthquake   = bundle.getInt( TENSHIEARTHQUAKE );
		bordercount   = bundle.getInt( BORDERCOUNT );

		maxHP_change = bundle.getInt( MAXHP_DOWN );
		life_count = bundle.getInt(LIFE_COUNT);
		bomb_count = bundle.getInt(BOMB_COUNT);
		dismantle_count = bundle.getInt(DISMANTLE_COUNT);

		seija_roulette = bundle.getInt(SEIJA_ROULETTE);

		duration		= bundle.getFloat( DURATION );

		powerfulres		= bundle.getInt( POWERFULRES );
		coolres		= bundle.getInt( COOLRES );
		pureres		= bundle.getInt( PURERES );
		happyres		= bundle.getInt( HAPPYRES );

		randomencountertrack		= bundle.getInt( RANDOMENCOUNTERTRACK );
		timetrackbuff		= bundle.getInt( TIMETRACKBUFF );
		timetrackstrup		= bundle.getInt( TIMETRACKSTRUP );

		power		= bundle.getInt( POWER );
		value		= bundle.getInt( VALUE );
		life		= bundle.getInt( LIFE );
		lifefragment		= bundle.getInt( LIFEFRAGMENT );
		spellcard		= bundle.getInt( SPELLCARD );
		spellcardfragment		= bundle.getInt( SPELLCARDFRAGMENT );

		tenshiattackstep = bundle.getInt( TENSHIATTACKSTEP );
		eirinelixircount = bundle.getInt( EIRINELIXIRCOUNT );

		nextvalue		= bundle.getInt( NEXTVALUE );

		lifefragmentkill		= bundle.getInt( LIFEFRAGMENTKILL );
		spellcardfragmentkill		= bundle.getInt( SPELLCARDFRAGMENTKILL );

		hitoricount		= bundle.getInt( HITORICOUNT );
		hitorilefttime		= bundle.getInt( HITORILEFTTIME );

		difficulty		= bundle.getInt( DIFFICULTY );

		double_speed_upgrade	= bundle.getInt( DOUBLE_SPEED_UPGRADE );
		extermination_number	= bundle.getInt( EXTERMINATION_NUMBER );

		identify_use	= bundle.getBoolean( IDENTIFY_USE );
		fate_use		= bundle.getBoolean( FATE_USE );
		earth_use		= bundle.getBoolean( EARTH_USE );
		exorcism_use		= bundle.getBoolean( EXORCISM_USE );
		transmute_use		= bundle.getBoolean( TRANSMUTE_USE );

		elixir_trigger = bundle.getBoolean( ELIXIRTRIGGER );

		remi_countdown = bundle.getBoolean( REMI_COUNTDOWN );

		qualifiedForNoKilling = bundle.getBoolean( NO_KILLING_QUALIFIED );

		scorelife1	= bundle.getBoolean( SCORELIFE1 );
		scorelife2	= bundle.getBoolean( SCORELIFE2 );
		scorelife3	= bundle.getBoolean( SCORELIFE3 );
		scorelife4	= bundle.getBoolean( SCORELIFE4 );
		scorelife5	= bundle.getBoolean( SCORELIFE5 );
		scorelife6	= bundle.getBoolean( SCORELIFE6 );
		scorelife7	= bundle.getBoolean( SCORELIFE7 );
		scorelife8	= bundle.getBoolean( SCORELIFE8 );

		lifelose	= bundle.getBoolean( LIFELOSE );
		spellcarduse	= bundle.getBoolean( SPELLCARDUSE );
		torch_use = bundle.getBoolean( TORCH_USE );

		exterminatedEnemy = bundle.getClass( EXTERMINATED_ENEMY );
	}

	public static void preview( GamesInProgress.Info info, Bundle bundle ){
		info.goldCollected  = bundle.getInt( GOLD );
		info.maxFloor = bundle.getInt( DEEPEST );
	}
}