package CapitalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Capital.Resources;

class ResourcesTest {
	
	// Resource objects to be tested
	static final Resources[] RESOURCE_OBJECTS = {	new Resources(10, 100),
													new Resources(1, 0),
													new Resources(-1, 0),
													new Resources(-10, -20),
													new Resources(2000, 2001),
													new Resources(-2001, -2000)};
	// Gold values of resource objects
	static final int[] GOLD_VALUES = { 10, 1, -1, -10, 2000, -2001};
	
	// Food values of resource objects 
	static final int[] FOOD_VALUES = { 100, 0, 0, -20, 2001, -2000};
	
	// Tests default values of object
	@Test
	void testDefaultConstructor() {
		Resources test = new Resources();
		assertEquals("Default values do not match", 100, test.getFood());
		assertEquals("Default values do not match", 100, test.getGold());
	}

	// Tests constructor of Resources class
	@Test
	void testFullConstructor() {
		for(int i = 0; i < RESOURCE_OBJECTS.length; i++) {
			assertEquals("Constructor values do not match", GOLD_VALUES[i], RESOURCE_OBJECTS[i].getGold());
			assertEquals("Constructor values do not match", FOOD_VALUES[i], RESOURCE_OBJECTS[i].getFood());
		}
	}
	
	// Tests for getters/setters below

	@Test
	void testGetGold() {
		for(int i = 0; i < RESOURCE_OBJECTS.length; i++) {
			assertEquals("Gold values do not match", GOLD_VALUES[i], RESOURCE_OBJECTS[i].getGold());
		}
	}

	@Test
	void testSetGold() {
		Resources test = new Resources();
		
		for(int i = 0; i < GOLD_VALUES.length; i++) {
			test.setGold(GOLD_VALUES[i]);
			int expected = GOLD_VALUES[i];
			assertEquals("Set gold does not match", expected, test.getGold());
		}
		
	}

	@Test
	void testGetFood() {
		for(int i = 0; i < RESOURCE_OBJECTS.length; i++) {
			assertEquals("Food values do not match", FOOD_VALUES[i], RESOURCE_OBJECTS[i].getFood());
		}
	}

	@Test
	void testSetFood() {
		Resources test = new Resources();
		
		for(int i = 0; i < FOOD_VALUES.length; i++) {
			test.setFood(FOOD_VALUES[i]);
			int expected = FOOD_VALUES[i];
			assertEquals("Set gold does not match", expected, test.getFood());
		}
	}

}
