package Capital;

public class Army {
	int size, morale, formation;
	Resources r;
	
	
	/**
	 * Default constructor
	 */
	public Army() {
		size = 100;
		morale = 100;
		formation = 100;
		r = new Resources();
	} 
	/**
	 * Constructor with set values;
	 * @param size
	 * @param morale
	 * @param formation
	 * @param gold
	 * @param food
	 */
	public Army(int size, int morale, int formation, int gold, int food) {
		this.size = size;
		this.morale = morale;
		this.formation = formation;
		r.gold = gold;
		r.food = food;
	}
	/**
	 * This method returns the size of the army
	 * @return size
	 */
	
	public int getApproximateSize() {
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
}
