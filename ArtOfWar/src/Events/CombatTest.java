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
	private static final int[] ATTACK_STYLES = { 0, 1, 2 };
	private static final int[] FORMATION_TYPES = { 0, 1, 2, 3 };
	private static final int[] TERRAIN_TYPES = { 0, 1, 2, 3 };
	private static final boolean[] HAS_COVER = { true, false, true, false };
	private static final int[] AMBUSH_THRESHOLDS = { 50, 50, 70, 30 };
	private static final int[] GOLD_REWARDS = { 10, 20, 30, 40 };
	private static final int[] FOOD_REWARDS = { 10, 20, 30, 40 };
	private static final int[] GOLD_COSTS = { 5, 8, 10, 12 };
	private static final int[] FOOD_COSTS = { 5, 8, 10, 12 };

	private static final Army FALSE_PLAYER_ARMY = new Army(10, 50, 0, 0);
	private static final Enemy FALSE_ENEMY_ARMY = new Enemy(50, 0, "Swindlebint");
	private static final int FALSE_NUMBER = 900;

	@Test
	public void testGetSetPlayerArmy() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				HAS_COVER[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
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
				HAS_COVER[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
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
	public void testGetSetTerrain() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				HAS_COVER[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
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
				HAS_COVER[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		assertEquals("Error: Cover values do not match", HAS_COVER[0], comTest.isHasCover());
		assertNotEquals("Error: Cover values should not match, but do",
				HAS_COVER[3], comTest.isHasCover());
		
		comTest.setHasCover(HAS_COVER[1]);
		
		assertEquals("Error: Cover values do not match", HAS_COVER[1], comTest.isHasCover());
		assertNotEquals("Error: Cover values should not match, but do",
				HAS_COVER[2], comTest.isHasCover());
	}
	
	@Test
	public void testGetSetAmbushThreshold() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				HAS_COVER[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
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
	public void testGetSetGoldReward() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				HAS_COVER[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < GOLD_REWARDS.length; i++) {
			
			
			assertEquals("Error: Ambush threshold values do not match", 
					AMBUSH_THRESHOLDS[i], comTest.getAmbushThreshold());
			assertNotEquals("Error: Ambush threshold values should not match, but do", 
					FALSE_NUMBER, comTest.getAmbushThreshold());
		}
	}
	
	@Test
	public void testGetSetFoodReward() {
		Combat comTest = new Combat(PLAYER_ARMY[0], ENEMY_ARMY[0], TERRAIN_TYPES[0], 
				HAS_COVER[0], AMBUSH_THRESHOLDS[0], GOLD_REWARDS[0], 
				FOOD_REWARDS[0], GOLD_COSTS[0], FOOD_COSTS[0]);
		
		for (int i = 0; i < AMBUSH_THRESHOLDS.length; i++) {
			comTest.setAmbushThreshold(AMBUSH_THRESHOLDS[i]);
			
			assertEquals("Error: Ambush threshold values do not match", 
					AMBUSH_THRESHOLDS[i], comTest.getAmbushThreshold());
			assertNotEquals("Error: Ambush threshold values should not match, but do", 
					FALSE_NUMBER, comTest.getAmbushThreshold());
		}
	}
}