package com.touhoupixel.touhoupixeldungeongaiden;

import com.touhoupixel.touhoupixeldungeongaiden.actors.Actor;
import com.touhoupixel.touhoupixeldungeongaiden.actors.Char;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Amok;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Awareness;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MagicalSight;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.MindVision;
import com.touhoupixel.touhoupixeldungeongaiden.actors.buffs.RevealedArea;
import com.touhoupixel.touhoupixeldungeongaiden.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeongaiden.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeongaiden.items.Amulet;
import com.touhoupixel.touhoupixeldungeongaiden.items.Generator;
import com.touhoupixel.touhoupixeldungeongaiden.items.Heap;
import com.touhoupixel.touhoupixeldungeongaiden.items.Item;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TalismanOfForesight;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfRegrowth;
import com.touhoupixel.touhoupixeldungeongaiden.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.journal.Notes;
import com.touhoupixel.touhoupixeldungeongaiden.levels.DeadEndLevel;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level10;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level11;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level12;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level13;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level14;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level15;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level16;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level17;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level18;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level19;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level1HakureiShrine;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level2;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level20;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level3;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level4;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level5;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level6;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level7;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level8;
import com.touhoupixel.touhoupixeldungeongaiden.levels.Level9;
import com.touhoupixel.touhoupixeldungeongaiden.levels.RegularLevel;
import com.touhoupixel.touhoupixeldungeongaiden.levels.features.LevelTransition;
import com.touhoupixel.touhoupixeldungeongaiden.levels.rooms.secret.SecretRoom;
import com.touhoupixel.touhoupixeldungeongaiden.levels.rooms.special.SpecialRoom;
import com.touhoupixel.touhoupixeldungeongaiden.messages.Messages;
import com.touhoupixel.touhoupixeldungeongaiden.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeongaiden.ui.QuickSlotButton;
import com.touhoupixel.touhoupixeldungeongaiden.ui.Toolbar;
import com.touhoupixel.touhoupixeldungeongaiden.utils.BArray;
import com.touhoupixel.touhoupixeldungeongaiden.utils.DungeonSeed;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.SparseArray;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class Dungeon {

	//enum of items which have limited spawns, records how many have spawned
	//could all be their own separate numbers, but this allows iterating, much nicer for bundling/initializing.
	public static enum LimitedDrops {
		//limited world drops
		STRENGTH_CARDS,
		UPGRADE_CARDS,
		PATCHOULI_CARD,

		//alchemy
		COOKING_HP,
		BLAND_FRUIT_SEED;

		public int count = 0;

		//for items which can only be dropped once, should directly access count otherwise.
		public boolean dropped(){
			return count != 0;
		}
		public void drop(){
			count = 1;
		}

		public static void reset(){
			for (LimitedDrops lim : values()){
				lim.count = 0;
			}
		}

		public static void store( Bundle bundle ){
			for (LimitedDrops lim : values()){
				bundle.put(lim.name(), lim.count);
			}
		}

		public static void restore( Bundle bundle ){
			for (LimitedDrops lim : values()){
				if (bundle.contains(lim.name())){
					lim.count = bundle.getInt(lim.name());
				} else {
					lim.count = 0;
				}

			}
		}
	}

	public static int challenges;
	public static int mobsToChampion;

	public static Hero heroine;
	public static Level level;

	public static QuickSlot quickslot = new QuickSlot();

	public static int floor;
	public static int branch;

	public static int gold;
	public static int energy;

	public static HashSet<Integer> chapters;

	public static SparseArray<ArrayList<Item>> droppedItems;
	public static SparseArray<ArrayList<Item>> portedItems;

	//first variable is only assigned when game is started, second is updated every time game is saved
	public static int initialVersion;
	public static int version;

	public static boolean daily;
	public static String customSeedText = "";
	public static long seed;

	public static void init() {

		initialVersion = version = Game.versionCode;
		challenges = SPDSettings.challenges();
		mobsToChampion = -1;

		if (daily) {
			seed = SPDSettings.lastDaily();
			DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ROOT);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			customSeedText = format.format(new Date(seed));
		} else if (!SPDSettings.customSeed().isEmpty()){
			customSeedText = SPDSettings.customSeed();
			seed = DungeonSeed.convertFromText(customSeedText);
		} else {
			customSeedText = "";
			seed = DungeonSeed.randomSeed();
		}

		Actor.clear();
		Actor.resetNextID();

		//offset seed slightly to avoid output patterns
		Random.pushGenerator( seed+1 );

		Scroll.initLabels();
		Potion.initColors();
		Ring.initGems();

		SpecialRoom.initForRun();
		SecretRoom.initForRun();

		Generator.fullReset();

		Random.resetGenerators();

		Statistics.reset();
		Notes.reset();

		quickslot.reset();
		QuickSlotButton.reset();
		Toolbar.swappedQuickslots = false;

		floor = 1;
		branch = 0;

		gold = 5; //to buy an ability card on floor 1
		energy = 0;

		droppedItems = new SparseArray<>();
		portedItems = new SparseArray<>();

		LimitedDrops.reset();

		chapters = new HashSet<>();

		heroine = new Hero();
		heroine.live();

		Badges.reset();

		GamesInProgress.selectedClass.initHero(heroine);
	}

	public static boolean isChallenged( int mask ) {
		return (challenges & mask) != 0;
	}

	public static Level newLevel() {

		Dungeon.level = null;
		Actor.clear();

		if (floor > Statistics.highestFloor) {
			Statistics.highestFloor = floor;

			if (Statistics.qualifiedForNoKilling) {
				Statistics.completedWithNoKilling = true;
			} else {
				Statistics.completedWithNoKilling = false;
			}
		}

		Level level;
		switch (floor) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				level = new Level1HakureiShrine();
				break;
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				level = new Level2();
				break;
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
				level = new Level3();
				break;
			case 16:
			case 17:
			case 18:
			case 19:
			case 20:
				level = new Level4();
				break;
			case 21:
			case 22:
			case 23:
			case 24:
			case 25:
				level = new Level5();
				break;
			case 26:
			case 27:
			case 28:
			case 29:
			case 30:
				level = new Level6();
				break;
			case 31:
			case 32:
			case 33:
			case 34:
			case 35:
				level = new Level7();
				break;
			case 36:
			case 37:
			case 38:
			case 39:
			case 40:
				level = new Level8();
				break;
			case 41:
			case 42:
			case 43:
			case 44:
			case 45:
				level = new Level9();
				break;
			case 46:
			case 47:
			case 48:
			case 49:
			case 50:
				level = new Level10();
				break;
			case 51:
			case 52:
			case 53:
			case 54:
			case 55:
				level = new Level11();
				break;
			case 56:
			case 57:
			case 58:
			case 59:
			case 60:
				level = new Level12();
				break;
			case 61:
			case 62:
			case 63:
			case 64:
			case 65:
				level = new Level13();
				break;
			case 66:
			case 67:
			case 68:
			case 69:
			case 70:
				level = new Level14();
				break;
			case 71:
			case 72:
			case 73:
			case 74:
			case 75:
				level = new Level15();
				break;
			case 76:
			case 77:
			case 78:
			case 79:
			case 80:
				level = new Level16();
				break;
			case 81:
			case 82:
			case 83:
			case 84:
			case 85:
				level = new Level17();
				break;
			case 86:
			case 87:
			case 88:
			case 89:
			case 90:
				level = new Level18();
				break;
			case 91:
			case 92:
			case 93:
			case 94:
			case 95:
				level = new Level19();
				break;
			case 96:
			case 97:
			case 98:
			case 99:
				level = new Level20();
				break;
			default:
				level = new DeadEndLevel();
				Statistics.highestFloor--;
		}

		level.create();

		Statistics.qualifiedForNoKilling = !bossLevel();

		return level;
	}

	public static void resetLevel() {

		Actor.clear();

		level.reset();
		switchLevel( level, level.entrance() );
	}

	public static long seedCurFloor(){
		return seedForFloor(floor, branch);
	}

	public static long seedForFloor(int floor, int branch){
		int lookAhead = floor;
		lookAhead += 30*branch; //Assumes floor is always 1-30, and branch is always 0 or higher

		Random.pushGenerator( seed );

		for (int i = 0; i < lookAhead; i ++) {
			Random.Long(); //we don't care about these values, just need to go through them
		}
		long result = Random.Long();

		Random.popGenerator();
		return result;
	}

	public static boolean shopOnLevel() {
		return floor == 6 || floor == 16 || floor == 26 || floor == 36 || floor == 45 || floor == 65 || floor == 85;
	}

	public static boolean cardShopOnLevel() {
		return floor == 1 || floor == 11 || floor == 21 || floor == 31;
	}

	//public static boolean pandemoniumLevel() {
		//return floor == 50 || floor == 60 || floor == 70 || floor == 80 || floor == 90;
	//}

	public static boolean bossLevel() {
		return bossLevel(floor);
	}

	public static boolean bossLevel( int floor ) {
		return floor == 5 || floor == 10 || floor == 15 || floor == 20 || floor == 25 || floor == 30 || floor == 35 || floor == 40 || floor == 98;
	}

	public static int scalingFloor(){
		return floor;
	}

	public static boolean interfloorTeleportAllowed(){
		if (Dungeon.level.locked || (Dungeon.heroine != null && Dungeon.heroine.belongings.getItem(Amulet.class) != null)){
			return false;
		}
		return true;
	}

	public static void switchLevel( final Level level, int pos ) {

		if (pos == -2){
			LevelTransition t = level.getTransition(LevelTransition.Type.REGULAR_ENTRANCE);
			if (t != null) pos = t.cell();
		}

		if (pos < 0 || pos >= level.length() || (!level.passable[pos] && !level.avoid[pos])){
			pos = level.getTransition(null).cell();
		}

		PathFinder.setMapSize(level.width(), level.height());

		Dungeon.level = level;
		heroine.pos = pos;

		Mob.restoreAllies( level, pos );

		Actor.init();

		level.addRespawner();

		for(Mob m : level.mobs){
			if (m.pos == heroine.pos){
				//displace mob
				for(int i : PathFinder.NEIGHBOURS8){
					if (Actor.findChar(m.pos+i) == null && level.passable[m.pos + i]){
						m.pos += i;
						break;
					}
				}
			}
		}

		Light light = heroine.buff( Light.class );
		heroine.viewDistance = light == null ? level.viewDistance : Math.max( Light.DISTANCE, level.viewDistance );

		heroine.curAction = heroine.lastAction = null;

		observe();
		try {
			saveAll();
		} catch (IOException e) {
			ShatteredPixelDungeon.reportException(e);
			/*This only catches IO errors. Yes, this means things can go wrong, and they can go wrong catastrophically.
			But when they do the user will get a nice 'report this issue' dialogue, and I can fix the bug.*/
		}
	}

	public static void dropToChasm( Item item ) {
		int floor = Dungeon.floor - 1; //should minus this
		ArrayList<Item> dropped = Dungeon.droppedItems.get( floor );
		if (dropped == null) {
			Dungeon.droppedItems.put( floor, dropped = new ArrayList<>() );
		}
		dropped.add( item );
	}

	public static boolean strengthNeeded() {
		//2 POS each floor set
		int posLeftThisSet = 4 - (LimitedDrops.STRENGTH_CARDS.count - (floor / 5) * 4);
		if (posLeftThisSet <= 0) return false;

		int floorThisSet = (floor % 5);
		return Random.Int(5 - floorThisSet) < posLeftThisSet;
	}

	public static boolean upgradeNeeded() {
		int souLeftThisSet;
		souLeftThisSet = 4 - (LimitedDrops.UPGRADE_CARDS.count - (floor / 5) * 4);
		if (souLeftThisSet <= 0) return false;

		int floorThisSet = (floor % 5);
		return Random.Int(5 - floorThisSet) < souLeftThisSet;
	}

	public static boolean patchouliNeeded() {
		int asLeftThisSet = 1 - (LimitedDrops.PATCHOULI_CARD.count - (floor / 5));
		if (asLeftThisSet <= 0) return false;

		int floorThisSet = (floor % 5);
		return Random.Int(5 - floorThisSet) < asLeftThisSet;
	}

	private static final String INIT_VER	= "init_ver";
	private static final String VERSION		= "version";
	private static final String SEED		= "seed";
	private static final String CUSTOM_SEED	= "custom_seed";
	private static final String DAILY	    = "daily";
	private static final String CHALLENGES	= "challenges";
	private static final String MOBS_TO_CHAMPION	= "mobs_to_champion";
	private static final String HERO		= "hero";
	private static final String FLOOR       = "floor";
	private static final String BRANCH		= "branch";
	private static final String GOLD		= "gold";
	private static final String ENERGY		= "energy";
	private static final String DROPPED     = "dropped%d";
	private static final String PORTED      = "ported%d";
	private static final String LEVEL		= "level";
	private static final String LIMDROPS    = "limited_drops";
	private static final String CHAPTERS	= "chapters";
	private static final String QUESTS		= "quests";
	private static final String BADGES		= "badges";

	public static void saveGame( int save ) {
		try {
			Bundle bundle = new Bundle();

			bundle.put( INIT_VER, initialVersion );
			bundle.put( VERSION, version = Game.versionCode );
			bundle.put( SEED, seed );
			bundle.put( CUSTOM_SEED, customSeedText );
			bundle.put( DAILY, daily );
			bundle.put( CHALLENGES, challenges );
			bundle.put( MOBS_TO_CHAMPION, mobsToChampion );
			bundle.put( HERO, heroine);
			bundle.put( FLOOR, floor);
			bundle.put( BRANCH, branch );

			bundle.put( GOLD, gold );
			bundle.put( ENERGY, energy );

			for (int d : droppedItems.keyArray()) {
				bundle.put(Messages.format(DROPPED, d), droppedItems.get(d));
			}

			for (int p : portedItems.keyArray()){
				bundle.put(Messages.format(PORTED, p), portedItems.get(p));
			}

			quickslot.storePlaceholders( bundle );

			Bundle limDrops = new Bundle();
			LimitedDrops.store( limDrops );
			bundle.put ( LIMDROPS, limDrops );

			int count = 0;
			int ids[] = new int[chapters.size()];
			for (Integer id : chapters) {
				ids[count++] = id;
			}
			bundle.put( CHAPTERS, ids );

			SpecialRoom.storeRoomsInBundle( bundle );
			SecretRoom.storeRoomsInBundle( bundle );

			Statistics.storeInBundle( bundle );
			Notes.storeInBundle( bundle );
			Generator.storeInBundle( bundle );

			Scroll.save( bundle );
			Potion.save( bundle );
			Ring.save( bundle );

			MissileWeapon.saveTimeFreezeContainer( bundle );

			Actor.storeNextID( bundle );

			Bundle badges = new Bundle();
			Badges.saveLocal( badges );
			bundle.put( BADGES, badges );

			FileUtils.bundleToFile( GamesInProgress.gameFile(save), bundle);

		} catch (IOException e) {
			GamesInProgress.setUnknown( save );
			ShatteredPixelDungeon.reportException(e);
		}
	}

	public static void saveLevel( int save ) throws IOException {
		Bundle bundle = new Bundle();
		bundle.put( LEVEL, level );

		FileUtils.bundleToFile(GamesInProgress.floorFile( save, floor, branch ), bundle);
	}

	public static void saveAll() throws IOException {
		if (heroine != null && (heroine.isAlive())) {

			Actor.fixTime();
			updateLevelExplored();
			saveGame( GamesInProgress.curSlot );
			saveLevel( GamesInProgress.curSlot );

			GamesInProgress.set( GamesInProgress.curSlot, floor, challenges, seed, customSeedText, daily, heroine);

		}
	}

	public static void loadGame( int save ) throws IOException {
		loadGame( save, true );
	}

	public static void loadGame( int save, boolean fullLoad ) throws IOException {

		Bundle bundle = FileUtils.bundleFromFile( GamesInProgress.gameFile( save ) );

		//pre-1.3.0 saves
		if (bundle.contains(INIT_VER)){
			initialVersion = bundle.getInt( INIT_VER );
		} else {
			initialVersion = bundle.getInt( VERSION );
		}

		version = bundle.getInt( VERSION );

		seed = bundle.contains( SEED ) ? bundle.getLong( SEED ) : DungeonSeed.randomSeed();
		customSeedText = bundle.getString( CUSTOM_SEED );
		daily = bundle.getBoolean( DAILY );

		Actor.clear();
		Actor.restoreNextID( bundle );

		quickslot.reset();
		QuickSlotButton.reset();
		Toolbar.swappedQuickslots = false;

		Dungeon.challenges = bundle.getInt( CHALLENGES );
		Dungeon.mobsToChampion = bundle.getInt( MOBS_TO_CHAMPION );

		Dungeon.level = null;
		Dungeon.floor = -1;

		Scroll.restore( bundle );
		Potion.restore( bundle );
		Ring.restore( bundle );

		MissileWeapon.restoreTimeFreezeContainer( bundle );

		quickslot.restorePlaceholders( bundle );

		if (fullLoad) {

			LimitedDrops.restore( bundle.getBundle(LIMDROPS) );

			chapters = new HashSet<>();
			int ids[] = bundle.getIntArray( CHAPTERS );
			if (ids != null) {
				for (int id : ids) {
					chapters.add( id );
				}
			}

			//Bundle quests = bundle.getBundle( QUESTS );
			//if (!quests.isNull()) {
				//Wandmaker.Quest.restoreFromBundle( quests );
				//Imp.Quest.restoreFromBundle( quests );
			//} else {
				//Wandmaker.Quest.reset();
				//Imp.Quest.reset();
			//}

			SpecialRoom.restoreRoomsFromBundle(bundle);
			SecretRoom.restoreRoomsFromBundle(bundle);
		}

		Bundle badges = bundle.getBundle(BADGES);
		if (!badges.isNull()) {
			Badges.loadLocal( badges );
		} else {
			Badges.reset();
		}

		Notes.restoreFromBundle( bundle );

		heroine = null;
		heroine = (Hero)bundle.get( HERO );

		floor = bundle.getInt(FLOOR);
		branch = bundle.getInt( BRANCH );

		gold = bundle.getInt( GOLD );
		energy = bundle.getInt( ENERGY );

		Statistics.restoreFromBundle( bundle );
		Generator.restoreFromBundle( bundle );

		droppedItems = new SparseArray<>();
		portedItems = new SparseArray<>();
		for (int i=1; i <= 100; i++) {

			//dropped items
			ArrayList<Item> items = new ArrayList<>();
			if (bundle.contains(Messages.format( DROPPED, i )))
				for (Bundlable b : bundle.getCollection( Messages.format( DROPPED, i ) ) ) {
					items.add( (Item)b );
				}
			if (!items.isEmpty()) {
				droppedItems.put( i, items );
			}

			//ported items
			items = new ArrayList<>();
			if (bundle.contains(Messages.format( PORTED, i )))
				for (Bundlable b : bundle.getCollection( Messages.format( PORTED, i ) ) ) {
					items.add( (Item)b );
				}
			if (!items.isEmpty()) {
				portedItems.put( i, items );
			}
		}
	}

	public static Level loadLevel( int save ) throws IOException {

		Dungeon.level = null;
		Actor.clear();

		Bundle bundle = FileUtils.bundleFromFile( GamesInProgress.floorFile( save, floor, branch ));

		Level level = (Level)bundle.get( LEVEL );

		if (level == null){
			throw new IOException();
		} else {
			return level;
		}
	}

	public static void deleteGame( int save, boolean deleteLevels ) {

		if (deleteLevels) {
			String folder = GamesInProgress.gameFolder(save);
			for (String file : FileUtils.filesInDir(folder)){
				if (file.contains("depth")){
					FileUtils.deleteFile(folder + "/" + file);
				}
			}
		}

		FileUtils.overwriteFile(GamesInProgress.gameFile(save), 1);

		GamesInProgress.delete( save );
	}

	public static void preview( GamesInProgress.Info info, Bundle bundle ) {
		info.floor = bundle.getInt(FLOOR);
		info.version = bundle.getInt( VERSION );
		info.challenges = bundle.getInt( CHALLENGES );
		info.seed = bundle.getLong( SEED );
		info.customSeed = bundle.getString( CUSTOM_SEED );
		info.daily = bundle.getBoolean( DAILY );

		Hero.preview( info, bundle.getBundle( HERO ) );
		Statistics.preview( info, bundle );
	}

	public static void fail( Class cause ) {
		updateLevelExplored();
		Rankings.INSTANCE.submit( false, cause );
	}

	public static void win( Class cause ) {
		updateLevelExplored();
		heroine.belongings.identify();
		Rankings.INSTANCE.submit(true, cause);
	}

	public static void updateLevelExplored(){
		if (branch == 0 && level instanceof RegularLevel && !Dungeon.bossLevel()){
			Statistics.floorsExplored.put(floor, level.isLevelExplored(floor));
		}
	}

	//default to recomputing based on max hero vision, in case vision just shrank/grew
	public static void observe(){
		int dist = Math.max(Dungeon.heroine.viewDistance, 8);
		dist *= 1f;

		if (Dungeon.heroine.buff(MagicalSight.class) != null){
			dist = Math.max( dist, MagicalSight.DISTANCE );
		}

		observe( dist+1 );
	}

	public static void observe( int dist ) {

		if (level == null) {
			return;
		}

		level.updateFieldOfView(heroine, level.heroFOV);

		int x = heroine.pos % level.width();
		int y = heroine.pos / level.width();

		//left, right, top, bottom
		int l = Math.max( 0, x - dist );
		int r = Math.min( x + dist, level.width() - 1 );
		int t = Math.max( 0, y - dist );
		int b = Math.min( y + dist, level.height() - 1 );

		int width = r - l + 1;
		int height = b - t + 1;

		int pos = l + t * level.width();

		for (int i = t; i <= b; i++) {
			BArray.or( level.visited, level.heroFOV, pos, width, level.visited );
			pos+=level.width();
		}

		GameScene.updateFog(l, t, width, height);

		if (heroine.buff(MindVision.class) != null){
			for (Mob m : level.mobs.toArray(new Mob[0])){
				BArray.or( level.visited, level.heroFOV, m.pos - 1 - level.width(), 3, level.visited );
				BArray.or( level.visited, level.heroFOV, m.pos - 1, 3, level.visited );
				BArray.or( level.visited, level.heroFOV, m.pos - 1 + level.width(), 3, level.visited );
				//updates adjacent cells too
				GameScene.updateFog(m.pos, 2);
			}
		}

		if (heroine.buff(Awareness.class) != null){
			for (Heap h : level.heaps.valueList()){
				BArray.or( level.visited, level.heroFOV, h.pos - 1 - level.width(), 3, level.visited );
				BArray.or( level.visited, level.heroFOV, h.pos - 1, 3, level.visited );
				BArray.or( level.visited, level.heroFOV, h.pos - 1 + level.width(), 3, level.visited );
				GameScene.updateFog(h.pos, 2);
			}
		}

		for (TalismanOfForesight.CharAwareness c : heroine.buffs(TalismanOfForesight.CharAwareness.class)){
			Char ch = (Char) Actor.findById(c.charID);
			if (ch == null || !ch.isAlive()) continue;
			BArray.or( level.visited, level.heroFOV, ch.pos - 1 - level.width(), 3, level.visited );
			BArray.or( level.visited, level.heroFOV, ch.pos - 1, 3, level.visited );
			BArray.or( level.visited, level.heroFOV, ch.pos - 1 + level.width(), 3, level.visited );
			GameScene.updateFog(ch.pos, 2);
		}

		for (TalismanOfForesight.HeapAwareness h : heroine.buffs(TalismanOfForesight.HeapAwareness.class)){
			if (Dungeon.floor != h.floor) continue;
			BArray.or( level.visited, level.heroFOV, h.pos - 1 - level.width(), 3, level.visited );
			BArray.or( level.visited, level.heroFOV, h.pos - 1, 3, level.visited );
			BArray.or( level.visited, level.heroFOV, h.pos - 1 + level.width(), 3, level.visited );
			GameScene.updateFog(h.pos, 2);
		}

		for (RevealedArea a : heroine.buffs(RevealedArea.class)){
			if (Dungeon.floor != a.floor) continue;
			BArray.or( level.visited, level.heroFOV, a.pos - 1 - level.width(), 3, level.visited );
			BArray.or( level.visited, level.heroFOV, a.pos - 1, 3, level.visited );
			BArray.or( level.visited, level.heroFOV, a.pos - 1 + level.width(), 3, level.visited );
			GameScene.updateFog(a.pos, 2);
		}

		for (Char ch : Actor.chars()){
			if (ch instanceof WandOfWarding.Ward
					|| ch instanceof WandOfRegrowth.Lotus){
				x = ch.pos % level.width();
				y = ch.pos / level.width();

				//left, right, top, bottom
				dist = ch.viewDistance+1;
				l = Math.max( 0, x - dist );
				r = Math.min( x + dist, level.width() - 1 );
				t = Math.max( 0, y - dist );
				b = Math.min( y + dist, level.height() - 1 );

				width = r - l + 1;
				height = b - t + 1;

				pos = l + t * level.width();

				for (int i = t; i <= b; i++) {
					BArray.or( level.visited, level.heroFOV, pos, width, level.visited );
					pos+=level.width();
				}
				GameScene.updateFog(ch.pos, dist);
			}
		}

		GameScene.afterObserve();
	}

	//we store this to avoid having to re-allocate the array with each pathfind
	private static boolean[] passable;

	private static void setupPassable(){
		if (passable == null || passable.length != Dungeon.level.length())
			passable = new boolean[Dungeon.level.length()];
		else
			BArray.setFalse(passable);
	}

	public static PathFinder.Path findPath(Char ch, int to, boolean[] pass, boolean[] vis, boolean chars) {

		setupPassable();
		if (ch.flying || ch.buff( Amok.class ) != null) {
			BArray.or( pass, Dungeon.level.avoid, passable );
		} else {
			System.arraycopy( pass, 0, passable, 0, Dungeon.level.length() );
		}

		if (chars) {
			for (Char c : Actor.chars()) {
				if (vis[c.pos]) {
					passable[c.pos] = false;
				}
			}
		}

		return PathFinder.find( ch.pos, to, passable );

	}

	public static int findStep(Char ch, int to, boolean[] pass, boolean[] visible, boolean chars ) {

		if (Dungeon.level.adjacent( ch.pos, to )) {
			return Actor.findChar( to ) == null && (pass[to] || Dungeon.level.avoid[to]) ? to : -1;
		}

		setupPassable();
		if (ch.flying || ch.buff( Amok.class ) != null) {
			BArray.or( pass, Dungeon.level.avoid, passable );
		} else {
			System.arraycopy( pass, 0, passable, 0, Dungeon.level.length() );
		}

		if (chars){
			for (Char c : Actor.chars()) {
				if (visible[c.pos]) {
					passable[c.pos] = false;
				}
			}
		}

		return PathFinder.getStep( ch.pos, to, passable );

	}

	public static int flee( Char ch, int from, boolean[] pass, boolean[] visible, boolean chars ) {

		setupPassable();
		if (ch.flying) {
			BArray.or( pass, Dungeon.level.avoid, passable );
		} else {
			System.arraycopy( pass, 0, passable, 0, Dungeon.level.length() );
		}

		passable[ch.pos] = true;

		//only consider chars impassable if our retreat path runs into them
		int step = PathFinder.getStepBack( ch.pos, from, passable );
		while (step != -1 && Actor.findChar(step) != null){
			passable[step] = false;
			step = PathFinder.getStepBack( ch.pos, from, passable );
		}
		return step;

	}

}