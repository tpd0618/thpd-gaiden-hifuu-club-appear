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

package com.touhoupixel.touhoupixeldungeongaiden.items;

import com.touhoupixel.touhoupixeldungeongaiden.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.HollowArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.IroncladArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.JokerArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.KeikiArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.KogasaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MaiArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MorfonicaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MorganaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MurasaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.NarumiArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.NitoriArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.OkinaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.GoldenDragonArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.HecatiaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MarisaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SakuyaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.MaxwellArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.AyaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.RumiaArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SanaeArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SatonoArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.SharkArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.ToyohimeArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.YorihimeArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.armor.YuyukoArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.AlchemistsToolkit;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.CloakOfShadows;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.EtherealChains;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.HornOfPlenty;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.KaguyaHDChest;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.MasterThievesArmband;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.SandalsOfNature;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TalismanOfForesight;
import com.touhoupixel.touhoupixeldungeongaiden.items.artifacts.TimekeepersHourglass;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Food;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.MysteryMeat;
import com.touhoupixel.touhoupixeldungeongaiden.items.food.Pasty;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.CamouflageHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.CleansingHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.DragonHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.HealingHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.Herb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.LevitationHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.RejuvenationHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.PurityHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.ReachHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.HeavenHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.PeaceHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.SwiftnessHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.herbs.SpearheadHerb;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfDoubleSpeed;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfEarthenArmor;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfFrost;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHaste;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfLiquidFlame;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfMagicalSight;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfPanacea;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfPowerUp;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfParalyticGas;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfPurity;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfRandomLife;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfShielding;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfShroudingFog;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfStamina;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfStrength;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfSuperUnlucky;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfToxicGas;
import com.touhoupixel.touhoupixeldungeongaiden.items.potions.PotionOfYingYang;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfAccuracy;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfElements;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfEnergy;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfEvasion;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfSharpshooting;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfTenacity;
import com.touhoupixel.touhoupixeldungeongaiden.items.rings.RingOfWealth;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfAnchor;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfEarth;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfFate;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfFixer;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfIdentify;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfLullaby;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfMirrorImage;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfNamelessStory;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfRage;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfExorcism;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfRetribution;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfTerror;
import com.touhoupixel.touhoupixeldungeongaiden.items.scrolls.ScrollOfTransmutation;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfFear;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfAggression;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfBlink;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfClairvoyance;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfDeepSleep;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfDisarming;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfEnchantment;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfFlock;
import com.touhoupixel.touhoupixeldungeongaiden.items.stones.StoneOfShock;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.BindingTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.CirnoTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.DoremyTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.HouraiTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.SevenDaysTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.DebilitationTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.EnragingTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.SuikaTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.TransientTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.InaccurateTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.KameTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.DisorientationTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.BackdoorTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.NightingaleTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.SwapTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.Talisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.CutterTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.talismans.YuumaTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.EgoRock;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.AkyuuBrush;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ArisaKeyboard;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ChimataCloak;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.DenjiChainsaw;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.EveKeytar;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.FullMoonScythe;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.Grayswandir;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.GreenBamboo;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.HecatiaStar;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.HinaRibbon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.IdolStick;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.JeweledBranch;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KanonDrumstick;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KokoroFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KyoukoBroom;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.QuintessentialFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MurasaDipper;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.NazrinRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RanTail;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RandomStar;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RingoDango;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SagumeWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.ToramaruSpear;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.TurnaboutCloak;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.UtsuhoRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.VentoraStick;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YachieHorn;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YukariUmbrella;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KoakumaWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.CirnoWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.AyaFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.TenguSmartphone;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.SeiranHammer;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.JunkoSword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.BigYingYangOrb;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.KogasaUmbrella;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.EikiRod;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MeleeWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.MystiaWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YoumuHalfPhantom;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.RemiliaWing;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.TenguShortsword;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YukinaMic;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.melee.YuyukoFoldingFan;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.AyaDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.KunaiDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MedicineDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MerlinDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ReisenDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ScaleDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.EikiDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.StarDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.BulletDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ShardDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.MissileWeapon;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.FlameDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.LunaticDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.TewiDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.RiceDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.ReimuTalisman;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.InvertDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.CirnoDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.YoumuDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.items.weapon.danmaku.YuukaDanmaku;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Blindweed;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Dreamfoil;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Earthroot;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Fadeleaf;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Firebloom;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Icecap;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Plant;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Rotberry;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sorrowmoss;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Starflower;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Stormvine;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Sungrass;
import com.touhoupixel.touhoupixeldungeongaiden.plants.Swiftthistle;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 6, 6, MeleeWeapon.class),

		ARMOR	( 6, 6, Armor.class ),

		MISSILE ( 6, 6, MissileWeapon.class ),

		RING	( 1, 0, Ring.class ),
		ARTIFACT( 0, 1, Artifact.class),

		FOOD	( 0, 0, Food.class ),
		HERB	( 4, 4, Herb.class ),

		POTION	( 6, 6, Potion.class ),
		SEED	( 1, 1, Plant.Seed.class ),

		SCROLL	( 6, 6, Scroll.class ),
		STONE   ( 1, 1, Runestone.class),
		TALISMAN( 3, 3, Talisman.class),

		GOLD	( 5, 4, Gold.class );

		public Class<?>[] classes;

		//some item types use a deck-based system, where the probs decrement as items are picked
		// until they are all 0, and then they reset. Those generator classes should define
		// defaultProbs. If defaultProbs is null then a deck system isn't used.
		//Artifacts in particular don't reset, no duplicates!
		public float[] probs;
		public float[] defaultProbs = null;

		//game has two decks of 35 items for overall category probs
		//one deck has a ring and extra armor, the other has an artifact and extra thrown weapon
		public float firstProb;
		public float secondProb;
		public Class<? extends Item> superClass;

		private Category( float firstProb, float secondProb, Class<? extends Item> superClass ) {
			this.firstProb = firstProb;
			this.secondProb = secondProb;
			this.superClass = superClass;
		}

		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}

			//items without a category-defined order are sorted based on the spritesheet
			return Short.MAX_VALUE+item.image();
		}

		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };

			POTION.classes = new Class<?>[]{
					PotionOfDoubleSpeed.class,
					PotionOfEarthenArmor.class,
					PotionOfExperience.class,
					PotionOfFrost.class,
					PotionOfHaste.class,
					PotionOfHealing.class,
					PotionOfInvisibility.class,
					PotionOfLevitation.class,
					PotionOfLightHealing.class,
					PotionOfLiquidFlame.class,
					PotionOfMagicalSight.class,
					PotionOfMindVision.class,
					PotionOfPanacea.class,
					PotionOfParalyticGas.class,
					PotionOfPowerUp.class,
					PotionOfPurity.class,
					PotionOfRandomLife.class,
					PotionOfShielding.class,
					PotionOfShroudingFog.class,
					PotionOfStamina.class,
					PotionOfStrength.class,
					PotionOfSuperUnlucky.class,
					PotionOfToxicGas.class,
					PotionOfYingYang.class};
			POTION.defaultProbs = new float[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5};
			POTION.probs = POTION.defaultProbs.clone();

			SEED.classes = new Class<?>[]{
					Rotberry.Seed.class, //quest item
					Sungrass.Seed.class,
					Fadeleaf.Seed.class,
					Icecap.Seed.class,
					Firebloom.Seed.class,
					Sorrowmoss.Seed.class,
					Swiftthistle.Seed.class,
					Blindweed.Seed.class,
					Stormvine.Seed.class,
					Earthroot.Seed.class,
					Dreamfoil.Seed.class,
					Starflower.Seed.class};
			SEED.defaultProbs = new float[]{ 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2 };
			SEED.probs = SEED.defaultProbs.clone();

			SCROLL.classes = new Class<?>[]{
					ScrollOfIdentify.class,
					ScrollOfAnchor.class,
					ScrollOfFate.class,
					ScrollOfEarth.class,
					ScrollOfFixer.class,
					ScrollOfNamelessStory.class,
					ScrollOfExorcism.class,
					ScrollOfMirrorImage.class,
					ScrollOfLullaby.class,
					ScrollOfRage.class,
					ScrollOfRetribution.class,
					ScrollOfTerror.class,
					ScrollOfTransmutation.class};
			SCROLL.defaultProbs = new float[]{ 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2};
			SCROLL.probs = SCROLL.defaultProbs.clone();

			STONE.classes = new Class<?>[]{
					StoneOfEnchantment.class,
					StoneOfDisarming.class,
					StoneOfFlock.class,
					StoneOfShock.class,
					StoneOfBlink.class,
					StoneOfDeepSleep.class,
					StoneOfClairvoyance.class,
					StoneOfAggression.class,
					StoneOfFear.class
			};
			STONE.defaultProbs = new float[]{ 0, 4, 4, 4, 4, 4, 4, 4, 4 };
			STONE.probs = STONE.defaultProbs.clone();

			TALISMAN.classes = new Class<?>[]{
					BindingTalisman.class,
					SevenDaysTalisman.class,
					EnragingTalisman.class,
					TransientTalisman.class,
					InaccurateTalisman.class,
					KameTalisman.class,
					SwapTalisman.class,
					DebilitationTalisman.class,
					DisorientationTalisman.class,
					NightingaleTalisman.class,
					BackdoorTalisman.class,
					CutterTalisman.class,
					CirnoTalisman.class,
					DoremyTalisman.class,
					HouraiTalisman.class,
					SuikaTalisman.class,
					YuumaTalisman.class
			};
			TALISMAN.defaultProbs = new float[]{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			TALISMAN.probs = TALISMAN.defaultProbs.clone();

			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};

			WEAPON.classes = new Class<?>[]{
					TenguShortsword.class,
					KogasaUmbrella.class,
					MystiaWing.class,
					KoakumaWing.class,
					RingoDango.class,
					KyoukoBroom.class,
					NazrinRod.class,
					KokoroFan.class,
					EikiRod.class,
					YoumuHalfPhantom.class,
					JeweledBranch.class,
					HinaRibbon.class,
					AkyuuBrush.class,
					HecatiaStar.class,
					GreenBamboo.class,
					UtsuhoRod.class,
					CirnoWing.class,
					RemiliaWing.class,
					RanTail.class,
					YukariUmbrella.class,
					MurasaDipper.class,
					YachieHorn.class,
					YuyukoFoldingFan.class,
					TurnaboutCloak.class,
					IdolStick.class,
					BigYingYangOrb.class,
					TenguSmartphone.class,
					SeiranHammer.class,
					JunkoSword.class,
					AyaFan.class,
					ChimataCloak.class,
					Grayswandir.class,
					FullMoonScythe.class,
					SagumeWing.class,
					ToramaruSpear.class,
					RandomStar.class,
					ArisaKeyboard.class,
					EveKeytar.class,
					YukinaMic.class,
					KanonDrumstick.class,
					VentoraStick.class,
					QuintessentialFan.class,
					DenjiChainsaw.class};
			WEAPON.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

			//see Generator.randomArmor
			ARMOR.classes = new Class<?>[]{};
			ARMOR.probs = new float[]{};

			ARMOR.classes = new Class<?>[]{
					MarisaArmor.class,
					NitoriArmor.class,
					SakuyaArmor.class,
					NarumiArmor.class,
					RumiaArmor.class,
					MaxwellArmor.class,
					SharkArmor.class,
					SanaeArmor.class,
					YorihimeArmor.class,
					GoldenDragonArmor.class,
					MorfonicaArmor.class,
					HecatiaArmor.class,
					SatonoArmor.class,
					MaiArmor.class,
					AyaArmor.class,
					ToyohimeArmor.class,
					YuyukoArmor.class,
					OkinaArmor.class,
					KeikiArmor.class,
					HollowArmor.class,
					IroncladArmor.class,
					KogasaArmor.class,
					MurasaArmor.class,
					MorganaArmor.class,
					JokerArmor.class
			};
			ARMOR.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

			//see Generator.randomMissile
			MISSILE.classes = new Class<?>[]{};
			MISSILE.probs = new float[]{};

			MISSILE.classes = new Class<?>[]{
					ReimuTalisman.class,
					FlameDanmaku.class,
					KunaiDanmaku.class,
					RiceDanmaku.class,
					ScaleDanmaku.class,
					EgoRock.class,
					BulletDanmaku.class,
					CirnoDanmaku.class,
					MerlinDanmaku.class,
					ShardDanmaku.class,
					YoumuDanmaku.class,
					AyaDanmaku.class,
					InvertDanmaku.class,
					ReisenDanmaku.class,
					StarDanmaku.class,
					YuukaDanmaku.class,
					EikiDanmaku.class,
					LunaticDanmaku.class,
					MedicineDanmaku.class,
					TewiDanmaku.class
			};
			MISSILE.probs = new float[]{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };

			HERB.classes = new Class<?>[]{
					CamouflageHerb.class,
					CleansingHerb.class,
					DragonHerb.class,
					HealingHerb.class,
					LevitationHerb.class,
					RejuvenationHerb.class,
					ReachHerb.class,
					HeavenHerb.class,
					PeaceHerb.class,
					SwiftnessHerb.class,
					PurityHerb.class,
					SpearheadHerb.class };
			HERB.probs = new float[]{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

			RING.classes = new Class<?>[]{
					RingOfAccuracy.class,
					RingOfEvasion.class,
					RingOfElements.class,
					RingOfEnergy.class,
					RingOfSharpshooting.class,
					RingOfTenacity.class,
					RingOfWealth.class};
			RING.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1 };

			ARTIFACT.classes = new Class<?>[]{
					CloakOfShadows.class,
					HornOfPlenty.class,
					MasterThievesArmband.class,
					SandalsOfNature.class,
					TalismanOfForesight.class,
					TimekeepersHourglass.class,
					AlchemistsToolkit.class,
					EtherealChains.class,
					KaguyaHDChest.class
			};
			ARTIFACT.defaultProbs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1};
			ARTIFACT.probs = ARTIFACT.defaultProbs.clone();
		}
	}

	private static boolean usingFirstDeck = false;
	private static HashMap<Category,Float> categoryProbs = new LinkedHashMap<>();

	public static void fullReset() {
		usingFirstDeck = Random.Int(2) == 0;
		generalReset();
		for (Category cat : Category.values()) {
			reset(cat);
		}
	}

	public static void generalReset(){
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, usingFirstDeck ? cat.firstProb : cat.secondProb );
		}
	}

	public static void reset(Category cat){
		if (cat.defaultProbs != null) cat.probs = cat.defaultProbs.clone();
	}

	public static Item random() {
		Category cat = Random.chances( categoryProbs );
		if (cat == null){
			usingFirstDeck = !usingFirstDeck;
			generalReset();
			cat = Random.chances( categoryProbs );
		}
		categoryProbs.put( cat, categoryProbs.get( cat ) - 1);
		return random( cat );
	}

	public static Item random( Category cat ) {
		switch (cat) {
			case WEAPON:
				return randomWeapon();
			case ARMOR:
				return randomArmor();
			case MISSILE:
				return randomMissile();
			case ARTIFACT:
				Item item = randomArtifact();
				//if we're out of artifacts, return a ring instead.
				return item != null ? item : random(Category.RING);
			default:
				int i = Random.chances(cat.probs);
				if (i == -1) {
					reset(cat);
					i = Random.chances(cat.probs);
				}
				if (cat.defaultProbs != null) cat.probs[i]--;
				return ((Item) Reflection.newInstance(cat.classes[i])).random();
		}
	}

	//overrides any deck systems and always uses default probs
	public static Item randomUsingDefaults( Category cat ){
		if (cat.defaultProbs == null) {
			return random(cat); //currently covers weapons/armor/missiles
		} else {
			return ((Item) Reflection.newInstance(cat.classes[Random.chances(cat.defaultProbs)])).random();
		}
	}

	public static Item random( Class<? extends Item> cl ) {
		return Reflection.newInstance(cl).random();
	}

	public static MeleeWeapon randomWeapon() {
		Category c = Category.WEAPON;
		MeleeWeapon w = (MeleeWeapon)Reflection.newInstance(c.classes[Random.chances(c.probs)]);
		w.random();
		return w;
	}

	public static Armor randomArmor() {
		Category c2 = Category.ARMOR;
		Armor w2 = (Armor)Reflection.newInstance(c2.classes[Random.chances(c2.probs)]);
		w2.random();
		return w2;
	}

	public static MissileWeapon randomMissile() {
		Category c3 = Category.MISSILE;
		MissileWeapon w3 = (MissileWeapon)Reflection.newInstance(c3.classes[Random.chances(c3.probs)]);
		w3.random();
		return w3;
	}

	//enforces uniqueness of artifacts throughout a run.
	public static Artifact randomArtifact() {

		Category cat = Category.ARTIFACT;
		int i = Random.chances( cat.probs );

		//if no artifacts are left, return null
		if (i == -1){
			return null;
		}

		cat.probs[i]--;
		return (Artifact) Reflection.newInstance((Class<? extends Artifact>) cat.classes[i]).random();

	}

	public static boolean removeArtifact(Class<?extends Artifact> artifact) {
		Category cat = Category.ARTIFACT;
		for (int i = 0; i < cat.classes.length; i++){
			if (cat.classes[i].equals(artifact) && cat.probs[i] > 0) {
				cat.probs[i] = 0;
				return true;
			}
		}
		return false;
	}
	public static void removeTimekeeperHourglass(){
		Category.ARTIFACT.probs[7] = 0;
	}

	private static final String FIRST_DECK = "first_deck";
	private static final String GENERAL_PROBS = "general_probs";
	private static final String CATEGORY_PROBS = "_probs";

	public static void storeInBundle(Bundle bundle) {
		bundle.put(FIRST_DECK, usingFirstDeck);

		Float[] genProbs = categoryProbs.values().toArray(new Float[0]);
		float[] storeProbs = new float[genProbs.length];
		for (int i = 0; i < storeProbs.length; i++){
			storeProbs[i] = genProbs[i];
		}
		bundle.put( GENERAL_PROBS, storeProbs);

		for (Category cat : Category.values()){
			if (cat.defaultProbs == null) continue;
			boolean needsStore = false;
			for (int i = 0; i < cat.probs.length; i++){
				if (cat.probs[i] != cat.defaultProbs[i]){
					needsStore = true;
					break;
				}
			}

			if (needsStore){
				bundle.put(cat.name().toLowerCase() + CATEGORY_PROBS, cat.probs);
			}
		}
	}

	public static void restoreFromBundle(Bundle bundle) {
		fullReset();

		usingFirstDeck = bundle.getBoolean(FIRST_DECK);

		if (bundle.contains(GENERAL_PROBS)){
			float[] probs = bundle.getFloatArray(GENERAL_PROBS);
			for (int i = 0; i < probs.length; i++){
				categoryProbs.put(Category.values()[i], probs[i]);
			}
		}

		for (Category cat : Category.values()){
			if (bundle.contains(cat.name().toLowerCase() + CATEGORY_PROBS)){
				float[] probs = bundle.getFloatArray(cat.name().toLowerCase() + CATEGORY_PROBS);
				if (cat.defaultProbs != null && probs.length == cat.defaultProbs.length){
					cat.probs = probs;
				}
			}
		}

	}
}