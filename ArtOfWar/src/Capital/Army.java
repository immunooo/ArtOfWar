package Capital;

import Events.Combat;

import java.util.Objects;

public class Army {
	int size, morale, formation;
	Resources r = new Resources(); //Added this because without, the copy constructor 'r' is never initialized and the object is null;
	
	
	/**
	 * Default constructor
	 */
	public Army() {
		size = 100;
		morale = 100;
		formation = 0;
		//r = new Resources(); //is now redundant because of the instance field
	}
	/**
	 * Constructor with set values;
	 * @param size
	 * @param morale
	 * @param gold
	 * @param food
	 */
	public Army(int size, int morale, int gold, int food) {

		this.size = size;
		this.morale = morale;
		r.gold = gold;
		r.food = food;
		formation = 0; //Will be handled in the Combat.java class
	}
	/**
	 * This method returns the size of the army
	 * @return size
	 */
	
	public int getApproximateSize() {
		return size;
	}

	/**
	 * Returns the exact size of the army (In case Approximate Size ends up changing)
	 * @return Exact size of the army
	 */
	public int getSize() {
		return size;
	}
	
	/** 
	 * This method sets a new value for army size 
	 * @param newSize 
	 */
	 
	public void setSize(int newSize) {
		size = newSize;  
	}  
	
	/**
	 * This method returns the morale of the army
	 * @return morale
	 */
	
	public int getMorale() {
		return morale;
	}
	
	/** 
	 * This method sets a new value for morale
	 * @param morale 
	 */
	public void setMorale(int morale) {
		this.morale = morale;
		
	}
	
	/**
	 * This method returns the formation of the army
	 * @return formation
	 */
	
	public int getFormation() {
		return formation;
	}
	
	/**
	 * This method sets a new value for formation
	 * @param newFormation
	 */
	public void setFormation(int newFormation) {
		formation = newFormation;
	}
	
	/**
	 * This method returns the resources of the army
	 * @return resources
	 */
	public Resources getResources() {
		return r;
	}

	public void setGold(int gold) { //Added to be able to update gold from Combat.java
		this.r.gold = gold;
	}

	public void setFood(int food) { //Added to be able to update food from Combat.java
		this.r.food = food;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Army army = (Army) o;
		return size == army.size &&
				morale == army.morale &&
				formation == army.formation &&
				Objects.equals(r, army.r);
	}

	@Override
	public String toString() {
		return "[" +
				"Army Size = " + size +
				", Morale = " + morale +
				", Formation = " + formation +
				", Gold = " + r.gold +
				", Food = " + r.food +
				']';
	}
}
