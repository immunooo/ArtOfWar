package Events;
import Capital.Army;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Event : a class responsible holding data used through an event.
 *
 * @author Joseph Berlucchi
 * @version 1.0
 */
public class StoryEvent {
	
	private Dialogue d;
	private HashMap<String, Integer[]> choicesMap;
	private ArrayList<String> choices;
 	private ArrayList<String> mPicture = new ArrayList<>();
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
	public StoryEvent(Dialogue d, ArrayList<String> choices,ArrayList<String> mPicture, int difficulty, String location ) {
		this.d = d;
		this.choices= choices;
		this.difficulty = difficulty;
		this.location = location;
		this.choicesMap = new HashMap<String, Integer[]>();
		this.eventComplete = false;
	}
	
	  public StoryEvent() {
          this(null, new ArrayList<>(), new ArrayList<>(), -1, null);
      }

	public StoryEvent clone() {
		Dialogue dialogueClone =  d.clone();
		
		ArrayList<String> choicesClone = new ArrayList<>();
		for(String s: choices) {
			choices.add(s.toString());
		}
		
		return new StoryEvent(dialogueClone, choicesClone, null, difficulty, location.toString());
		
		
		
	}

	 public Dialogue getDialogue() {
         return d;
     }
		/**
		 * This method returns the next dialogue prompt
		 * @return a array of size 2 where 0 = text and 1 = picture location
		 */
		
     public void setDialogue(Dialogue dialog) {
         d = dialog;
     }

     public ArrayList<String> getPicture() {
         return mPicture;
     }

     public void setPicture(ArrayList<String> picture) {
         mPicture = picture;
     }

 	/**
 	 * Returns the choices of the event.
 	 * @return choices for the event.
 	 */
     public ArrayList<String> getChoices() {
         return choices;
     }

     public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
     }

 	/**
 	 * Returns the difficulty of the event.
 	 * @return difficulty of the event.
 	 */
     public int getDifficulty() {
     	return difficulty;
     }
     
     public void setDifficulty(int difficulty) {
    	 this.difficulty = difficulty;
     }

 	/**
 	 * This method returns the location of the event.
 	 * @return location of the event.
 	 */
     public String getLocation() {
     	return location;
     }
     
     public void setLocation(String location) {
    	 this.location = location;
     }

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
	
	public boolean isEventComplete() {
		return eventComplete;
	}
	  public String toString() {
          return "{" +
                  d.toString() +
                  ", " + choices.toString() +
                  ", " + difficulty +
                  ", " + location +
                  "}";
      }
  }
