package Events;
import Capital.Army;
import java.util.HashMap;
/**
 * Event : a class responsible holding data used through an event.
 *
 * @author Joseph Berlucchi
 * @version 1.0
 */
public class Event {
	
	private Dialogue d;
	private HashMap<String, Integer[]> choicesMap;
	private String[] choices;
 	
	private int difficulty;
	private String location;
	
	private boolean eventComplete;
	
	/**
	 * Constructor method of the Events class
	 * 
	 * @param d is the dialogue object assosiated with the class
	 * @param choices is the array of text that are the choices
	 * @param resourceModifiers is the double array of integers which associates with the choices
	 * @param difficulty a positive integer which denotes difficulty (Higher the more difficult).
	 * @param location denotes the place where the event takes place
	 * 
	 * @throws Error if choices and resource modifier have different lengths or resource modifiers has incorrect amount of values
	 */
	public Event(Dialogue d, String[] choices, Integer[][] resourceModifiers, int difficulty, String location) {
		this.d = d;
		this.choices= choices;
		this.difficulty = difficulty;
		this.location = location;
		this.choicesMap = new HashMap<String, Integer[]>();
		this.eventComplete = false;
		
		if(choices.length != resourceModifiers.length) {
			throw new Error("Choices and resource modifier have different lengths.");
		}
		
		//Loads the hash map with choice text as key and the resource modifiers as the value
		for(int i = 0; i < choices.length; i++) {
			//Throws error if resource modifiers doesnt have a size of 4
			if(resourceModifiers[i].length != 4) {
				throw new Error("Resources modifiers has incorrect amount of values.");
			}
			
			choicesMap.put(choices[i], resourceModifiers[i]);
		}
		
	}

	/**
	 * Returns the choices of the event.
	 * @return choices for the event.
	 */
	public String[] getChoices() {
		return choices;
	}
	
	/**
	 * Returns the difficulty of the event.
	 * @return difficulty of the event.
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * This method returns the location of the event.
	 * @return location of the event.
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * This method returns the next dialogue prompt
	 * @return a array of size 2 where 0 = text and 1 = picture location
	 */
	public String[] getNextDialogue() {
		return d.poll();
	}
	
	/**
	 * This method updates an army class with the resource modifiers of the given choice.
	 * @param army of the user
	 * @param choice of the user
	 * 
	 * @return true if resources are updated, otherwise false.
	 */
	public boolean updateResources(Army army, String choice) {
		
		//Gets resource modifieres from the hash map
		Integer[] modifiers = choicesMap.get(choice);
		
		if(modifiers == null) {
			return false;
		}
		//0 = size, 1 = morale, 2 = food, 3 = gold
		army.setSize(army.getApproximateSize() + modifiers[0]);
		army.setMorale(army.getMorale() + modifiers[1]);
		army.getResources().setFood(army.getResources().getFood() +  modifiers[2]);
		army.getResources().setGold(army.getResources().getGold() +  modifiers[3]);
		eventComplete = true;
		return true;
		
	}
	
	public boolean isEventComplete() {
		return eventComplete;
	}
	
	
}
