package CapitalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import Capital.Army;

import org.junit.jupiter.api.Test;

class ArmyTest {
	
	// size, morale, gold, food
	static Army[] ARMIES = {	new Army(10, 11, 12, 13),
								new Army(100, 0, 100, 0),
								new Army(-11, -12, -13, -14)};
	static int[] SIZES = {10, 100, -11};
	static int[] MORALE_VALUES = {11, 0, -12};
	static int[] GOLD_VALUES = {12, 100, -13};
	static int[] FOOD_VALUES = {13, 0, -14};
	static int[] FORMATIONS = {1, 2, 3};

	@Test
	void testDefaultConstructor() {
		Army test = new Army();
													// expected, actual
		assertEquals("Default values do not match", 100, test.getSize());
		assertEquals("Default values do not match", 100, test.getMorale());
		assertEquals("Default values do not match", 100, test.getResources().getGold());
		assertEquals("Default values do not match", 100, test.getResources().getFood());
	}

	@Test
	void testFullConstructor() {
		for(int i = 0; i < ARMIES.length; i++) {
			assertEquals("Size values do not match", SIZES[i], ARMIES[i].getSize());
			assertEquals("Morale values do not match", MORALE_VALUES[i], ARMIES[i].getMorale());
			assertEquals("Gold values do not match", GOLD_VALUES[i], ARMIES[i].getResources().getGold());
			assertEquals("Food values do not match", FOOD_VALUES[i], ARMIES[i].getResources().getFood());
		}
	}

	@Test
	void testGetApproximateSize() {
		for(int i = 0; i < ARMIES.length; i++) {
			assertEquals("Size values do not match", SIZES[i], ARMIES[i].getApproximateSize());
		}
	}

	@Test
	void testGetSize() {
		for(int i = 0; i < ARMIES.length; i++) {
			assertEquals("Size values do not match", SIZES[i], ARMIES[i].getSize());
		}
	}

	@Test
	void testSetSize() {
		Army test = new Army();
		
		for(int i = 0; i < ARMIES.length; i++) {
			test.setSize(SIZES[i]);
			int expected = SIZES[i];
			assertEquals("Set gold does not match", expected, test.getSize());
		}
	}

	@Test
	void testGetMorale() {
		for(int i = 0; i < ARMIES.length; i++) {
			assertEquals("Size values do not match", MORALE_VALUES[i], ARMIES[i].getMorale());
		}
	}

	@Test
	void testSetMorale() {
		Army test = new Army();
		
		for(int i = 0; i < ARMIES.length; i++) {
			test.setMorale(MORALE_VALUES[i]);
			int expected = MORALE_VALUES[i];
			assertEquals("Set gold does not match", expected, test.getMorale());
		}
	}

	@Test
	void testGetFormation() {
		for(int i = 0; i < ARMIES.length; i++) {
			assertEquals("Size values do not match", 0, ARMIES[i].getFormation());
		}
	}

	@Test
	void testSetFormation() {
		Army test = new Army();
		
		for(int i = 0; i < ARMIES.length; i++) {
			test.setFormation(FORMATIONS[i]);
			int expected = FORMATIONS[i];
			assertEquals("Set gold does not match", expected, test.getFormation());
		}
	}

	@Test
	void testGetResources() {
		for(int i = 0; i < ARMIES.length; i++) {
			assertEquals("Size values do not match", GOLD_VALUES[i], ARMIES[i].getResources().getGold());
			assertEquals("Size values do not match", FOOD_VALUES[i], ARMIES[i].getResources().getFood());
		}
	}

}
