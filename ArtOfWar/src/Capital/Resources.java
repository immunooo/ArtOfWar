package Capital;

public class Resources {
int gold, food;
	
	/**
	 * Default constructor sets gold and food to 100
	 */
	public Resources() {
		gold = 100;
		food = 100;
	}
	/**
	 * Constructor with set values 
	 * @param gold
	 * @param food
	 */
	public Resources(int gold, int food) {
		this.gold = gold;
		this.food = food;
	}
	/**
	 * This method returns the gold
	 * @return gold
	 */ 
	public int getGold() {
		return gold;
	}
	/**
	 * This method sets a new value for gold
	 * @param newGoldValue
	 */
	public void setGold(int newGoldValue) {
		gold = newGoldValue;
	}
	/**
	 * This method returns the amount of food left
	 * @return
	 */
	public int getFood() {
		return food;
	}
	/**
	 * This method sets a new value for food
	 * @param newFoodValue
	 */
	public void setFood(int newFoodValue) {
		food = newFoodValue;
	}
	
}
