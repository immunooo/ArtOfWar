package Events;

import Capital.*;
import Events.Combat.Enemy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * CombatTest : JUnit test class for a Combat interface implementation.
 *
 * @author A. Haskins
 * @version 1.0
 */

public class CombatTest {

	private static final Army[] PLAYER_ARMY = { new Army(25, 100, 0, 0), new Army(10, 50, 0, 0) };
	private static final Enemy[] ENEMY_ARMY = { new Enemy(100, 0, "Zartop"), new Enemy(80, 0, "Vorihsab") };
	private static final int[] MORALE_VALUES = { 30, 40, 50, 60 };
	private static final int[] SIZES = { 25, 35, 65, 75 };
	private static final int[] ATTACK_STYLES = { 0, 1, 2 };
	private static final int[] FORMATION_TYPES = { 0, 1, 2, 3 };
	private static final int[] TERRAIN_TYPES = { 0, 1, 2, 3 };
	private static final boolean[] BOOLEANS = { true, false, true, false };
	private static final int[] AMBUSH_THRESHOLDS = { 50, 50, 70, 30 };
	private static final String[] GENERAL_NAMES = { "Setay", "Ihcculreb", "Tanbi", "Gnahz" };
	private static final int[] ATTACK_VALUES = { 2, 3, 5, 7 };
	private static final int[] GOLD_REWARDS = { 10, 20, 30, 40 };
	private static final int[] FOOD_REWARDS = { 10, 20, 30, 40 };
	private static final int[] GOLD_COSTS = { 5, 8, 10, 12 };
	private static final int[] FOOD_COSTS = { 5, 8, 10, 12 };

	private static final Army FALSE_PLAYER_ARMY = new Army(30, 60, 0, 0);
	private static final Enemy FALSE_ENEMY_ARMY = new Enemy(50, 0, "Swindlebint");
	private static final int FALSE_NUMBER = 900;
	private static final String FALSE_STRING = "Sniksah";
	
	@Test
	public void testGetSetPlayerArmy() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertEquals("Error: Player values do not match", PLAYER_ARMY[0], comTest.getPlayerArmy());
		assertNotEquals("Error: Player values should not match, but do",
				FALSE_PLAYER_ARMY, comTest.getPlayerArmy());
		
		comTest.setPlayerArmy(PLAYER_ARMY[1]);
		
		assertEquals("Error: Player values do not match", PLAYER_ARMY[1], comTest.getPlayerArmy());
		assertNotEquals("Error: Player values should not match, but do",
				FALSE_PLAYER_ARMY, comTest.getPlayerArmy());
		
	}
	
	@Test
	public void testGetSetEnemyArmy() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertEquals("Error: Enemy values do not match", ENEMY_ARMY[0], comTest.getEnemyArmy());
		assertNotEquals("Error: Enemy values should not match, but do",
				FALSE_ENEMY_ARMY, comTest.getEnemyArmy());
		
		comTest.setEnemyArmy(ENEMY_ARMY[1]);
		
		assertEquals("Error: Enemy values do not match", ENEMY_ARMY[1], comTest.getEnemyArmy());
		assertNotEquals("Error: Enemy values should not match, but do",
				FALSE_ENEMY_ARMY, comTest.getEnemyArmy());
	}
	
	@Test
	public void testGetSetMorale() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < MORALE_VALUES.length; i++) {
			comTest.setMorale(MORALE_VALUES[i]);
			
			assertEquals("Error: Morale values do not match", 
					MORALE_VALUES[i], comTest.getMorale());
			assertNotEquals("Error: Morale values should not match, but do", 
					FALSE_NUMBER, comTest.getMorale());
		}
	}
	
	@Test
	public void testGetSetPlayerSize() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < SIZES.length; i++) {
			comTest.setPlayerSize(SIZES[i]);
			
			assertEquals("Error: Player size values do not match", SIZES[i], comTest.getPlayerSize());
			assertNotEquals("Error: Player size values should not match, but do", 
					FALSE_NUMBER, comTest.getPlayerSize());
		}
	}
	
	@Test
	public void testGetSetEnemySize() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < SIZES.length; i++) {
			comTest.setEnemySize(SIZES[i]);
			
			assertEquals("Error: Enemy size values do not match", SIZES[i], comTest.getEnemySize());
			assertNotEquals("Error: Enemy size values should not match, but do", 
					FALSE_NUMBER, comTest.getEnemySize());
		}
	}
	
	@Test
	public void testGetSetPlayerAttackStyle() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < ATTACK_STYLES.length; i++) {
			comTest.setPlayerAS(ATTACK_STYLES[i]);
			
			assertEquals("Error: Player attack style values do not match", 
					ATTACK_STYLES[i], comTest.getPlayerAS());
			assertNotEquals("Error: Player attack style values should not match, but do", 
					FALSE_NUMBER, comTest.getPlayerAS());
		}
	}
	
	@Test
	public void testGetSetEnemyAttackStyle() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < ATTACK_STYLES.length; i++) {
			comTest.setEnemyAS(ATTACK_STYLES[i]);
			
			assertEquals("Error: Enemy attack style values do not match", 
					ATTACK_STYLES[i], comTest.getEnemyAS());
			assertNotEquals("Error: Enemy attack style values should not match, but do", 
					FALSE_NUMBER, comTest.getEnemyAS());
		}
	}
	
	@Test
	public void testGetSetPlayerFormation() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < FORMATION_TYPES.length; i++) {
			comTest.setPlayerFormation(FORMATION_TYPES[i]);
			
			assertEquals("Error: Player formation values do not match", 
					FORMATION_TYPES[i], comTest.getPlayerFormation());
			assertNotEquals("Error: Player formation values should not match, but do", 
					FALSE_NUMBER, comTest.getPlayerFormation());
		}
	}
	
	@Test
	public void testGetSetTerrain() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < TERRAIN_TYPES.length; i++) {
			comTest.setTerrain(TERRAIN_TYPES[i]);
			
			assertEquals("Error: Terrain values do not match", TERRAIN_TYPES[i], comTest.getTerrain());
			assertNotEquals("Error: Terrain values should not match, but do", 
					FALSE_NUMBER, comTest.getTerrain());
		}
	}
	
	@Test
	public void testGetSetHasCover() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertEquals("Error: Has cover values do not match", BOOLEANS[0], comTest.isHasCover());
		assertNotEquals("Error: Has cover values should not match, but do",
				BOOLEANS[3], comTest.isHasCover());
		
		comTest.setHasCover(BOOLEANS[1]);
		
		assertEquals("Error: Has cover values do not match", BOOLEANS[1], comTest.isHasCover());
		assertNotEquals("Error: Has cover values should not match, but do",
				BOOLEANS[2], comTest.isHasCover());
	}
	
	@Test
	public void testGetSetInCover() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertEquals("Error: In cover values do not match", BOOLEANS[1], comTest.isInCover());
		assertNotEquals("Error: In cover values should not match, but do",
				BOOLEANS[2], comTest.isInCover());
		
		comTest.setInCover(BOOLEANS[0]);
		
		assertEquals("Error: In cover values do not match", BOOLEANS[2], comTest.isInCover());
		assertNotEquals("Error: In cover values should not match, but do",
				BOOLEANS[3], comTest.isInCover());
	}
	
	@Test
	public void testGetSetFailedCover() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertEquals("Error: Failed cover values do not match", BOOLEANS[1], comTest.isFailedCover());
		assertNotEquals("Error: Failed cover values should not match, but do",
				BOOLEANS[2], comTest.isFailedCover());
		
		comTest.setFailedCover(BOOLEANS[0]);
		
		assertEquals("Error: Failed cover values do not match", BOOLEANS[2], comTest.isFailedCover());
		assertNotEquals("Error: Failed cover values should not match, but do",
				BOOLEANS[3], comTest.isFailedCover());
	}
	
	@Test
	public void testGetSetAmbushThreshold() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < AMBUSH_THRESHOLDS.length; i++) {
			comTest.setAmbushThreshold(AMBUSH_THRESHOLDS[i]);
			
			assertEquals("Error: Ambush threshold values do not match", 
					AMBUSH_THRESHOLDS[i], comTest.getAmbushThreshold());
			assertNotEquals("Error: Ambush threshold values should not match, but do", 
					FALSE_NUMBER, comTest.getAmbushThreshold());
		}
	}
	
	@Test
	public void testGetSetGeneralName() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < GENERAL_NAMES.length; i++) {
			comTest.setGeneralName(GENERAL_NAMES[i]);
			
			assertEquals("Error: General name values do not match", 
					GENERAL_NAMES[i], comTest.getGeneralName());
			assertNotEquals("Error: General name values should not match, but do", 
					FALSE_STRING, comTest.getGeneralName());
		}
	}
	
	@Test
	public void testGetSetPlayerAttack() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < ATTACK_VALUES.length; i++) {
			comTest.setPlayerAttack(ATTACK_VALUES[i]);
			
			assertEquals("Error: Player attack values do not match", 
					ATTACK_VALUES[i], comTest.getPlayerAttack());
			assertNotEquals("Error: Player attack values should not match, but do", 
					FALSE_NUMBER, comTest.getPlayerAttack());
		}
	}
	
	@Test
	public void testGetSetEnemyAttack() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < ATTACK_VALUES.length; i++) {
			comTest.setEnemyAttack(ATTACK_VALUES[i]);
			
			assertEquals("Error: Enemy attack values do not match", 
					ATTACK_VALUES[i], comTest.getEnemyAttack());
			assertNotEquals("Error: Enemy attack values should not match, but do", 
					FALSE_NUMBER, comTest.getEnemyAttack());
		}
	}
	
	@Test
	public void testGetSetHasFled() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertEquals("Error: Has fled values do not match", BOOLEANS[1], comTest.isHasFled());
		assertNotEquals("Error: Has fled values should not match, but do",
				BOOLEANS[2], comTest.isHasFled());
		
		comTest.setHasFled(BOOLEANS[0]);
		
		assertEquals("Error: Has fled values do not match", BOOLEANS[2], comTest.isHasFled());
		assertNotEquals("Error: Has fled values should not match, but do",
				BOOLEANS[3], comTest.isHasFled());
	}
	
	@Test
	public void testGetSetGoldReward() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < GOLD_REWARDS.length; i++) {
			comTest.setGoldReward(GOLD_REWARDS[i]);
			
			assertEquals("Error: Gold reward values do not match", 
					GOLD_REWARDS[i], comTest.getGoldReward());
			assertNotEquals("Error: Gold reward values should not match, but do", 
					FALSE_NUMBER, comTest.getGoldReward());
		}
	}
	
	@Test
	public void testGetSetFoodReward() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < FOOD_REWARDS.length; i++) {
			comTest.setFoodReward(FOOD_REWARDS[i]);
			
			assertEquals("Error: Food reward values do not match", 
					FOOD_REWARDS[i], comTest.getFoodReward());
			assertNotEquals("Error: Food reward values should not match, but do", 
					FALSE_NUMBER, comTest.getFoodReward());
		}
	}
	
	@Test
	public void testGetSetGoldCost() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < GOLD_COSTS.length; i++) {
			comTest.setGoldCost(GOLD_COSTS[i]);
			
			assertEquals("Error: Gold cost values do not match", 
					GOLD_COSTS[i], comTest.getGoldCost());
			assertNotEquals("Error: Gold cost values should not match, but do", 
					FALSE_NUMBER, comTest.getGoldCost());
		}
	}
	
	@Test
	public void testGetSetFoodCost() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < FOOD_COSTS.length; i++) {
			comTest.setFoodCost(FOOD_COSTS[i]);
			
			assertEquals("Error: Food cost values do not match", 
					FOOD_COSTS[i], comTest.getFoodCost());
			assertNotEquals("Error: Food cost values should not match, but do", 
					FALSE_NUMBER, comTest.getFoodCost());
		}
	}
	
	@Test
	public void testCopyConstructor() {
		Combat original = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		Combat c = new Combat(original);
		
		assertEquals(original.getPlayerArmy(), c.getPlayerArmy());
		assertEquals(original.getEnemyArmy(), c.getEnemyArmy());
		assertEquals(original.getTerrain(), c.getTerrain());
		assertEquals(original.isHasCover(), c.isHasCover());
		assertEquals(original.getAmbushThreshold(), c.getAmbushThreshold());
		assertEquals(original.getGoldReward(), c.getGoldReward());
		assertEquals(original.getFoodReward(), c.getFoodReward());
		assertEquals(original.getGoldCost(), c.getGoldCost());
		assertEquals(original.getFoodCost(), c.getFoodCost());
	}
	
	@Test
	public void testEquals() {
		Combat a = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		Combat b = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertTrue("Error: Objects are not equal", a.equals(b));
	}
	
	@Test
	public void testFlee() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		comTest.flee();
		
		assertEquals("Error: User input values do not match", 3, comTest.getUserInput());
		assertEquals("Error: Has fled values do not match", true, comTest.isHasFled());
		assertEquals("Error: Player army values do not match", 25, comTest.getPlayerArmy().getSize());
		assertEquals("Error: Morale values do not match", 100, comTest.getPlayerArmy().getMorale());
	}
	
	@Test
	public void testRoll() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		
		
		for(int i = 0; i < 10; i++) {
			int result = comTest.roll();
			
			assertTrue("Error: Roll returned int is not within boundaries", 
					result > 0 && result < 22);
		}
	}
	
	@Test
	public void testPlayerAttackMethod() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for(int i = 0; i < 10; i++) {
			
			int result = comTest.playerAttack();
			
			assertTrue("Error: Player attack returned int is not within boundaries", 
					result > -1 && result < 22);
		}
	}
	
	@Test
	public void testEnemyAttackMethod() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for(int i = 0; i < 10; i++) {
			
			int result = comTest.enemyAttack();
			
			assertTrue("Error: Enemy attack returned int is not within boundaries", 
					result > -1 && result < 22);
		}
	}
	
	@Test
	public void testPlayerMoraleMethod() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				BOOLEANS[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		comTest.playerMorale();
		
		assertEquals("Error: Player morale values do not match", 100, comTest.getMorale());
	}
	
	
}