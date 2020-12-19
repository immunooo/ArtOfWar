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
public class Event {
	
	private Dialogue d;
	private HashMap<String, Integer[]> choicesMap;
	private ArrayList<String> choices;
 	private ArrayList<String> mPicture = new ArrayList<>();
	private int difficulty;
	private String location;
	private ArrayList<Integer[]> mResources; 
	private boolean eventComplete;
	private String[] choiceDialog;
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
	public Event(Dialogue d, ArrayList<String> choices,ArrayList<String> mPicture, ArrayList<Integer[]> resourceModifiers, int difficulty, String location,String[] choiceDialog) {
		this.d = d;
		this.choices= choices;
		this.mResources = resourceModifiers;
		this.difficulty = difficulty;
		this.location = location;
		this.choicesMap = new HashMap<String, Integer[]>();
		this.eventComplete = false;
		this.choiceDialog = choiceDialog;
		
		if(choices.size() != resourceModifiers.size()) {
			throw new Error("Choices and resource modifier have different lengths.");
		}
		
		//Loads the hash map with choice text as key and the resource modifiers as the value
		for(int i = 0; i < choices.size(); i++) {
			//Throws error if resource modifiers doesnt have a size of 4
			if(resourceModifiers.get(i).length != 4) {
				throw new Error("Resources modifiers has incorrect amount of values.");
			}
			
			choicesMap.put(choices.get(i), resourceModifiers.get(i));
		}
		
	}
	  public Event() {
          this(null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), -1, null,null);
      }

	public Event clone() {
		Dialogue dialogueClone =  d.clone();
		
		ArrayList<String> choicesClone = new ArrayList<>();
		ArrayList<Integer[]> resourceModifiersClone = new ArrayList<>();
		for(String s: choices) {
			choices.add(s.toString());
			resourceModifiersClone.add(choicesMap.get(s));
		}
		
		return new Event(dialogueClone, choicesClone, null,resourceModifiersClone, difficulty, location.toString(),choiceDialog);
		
		
		
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
     
     public boolean DialogueIsEmpty()
     {
    	 return d.isEmpty();
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
     
     public void setChoiceDialog(String[] arr) {
    	choiceDialog = arr;
     }
     
     public String[] getChoiceDialog() {
    	 return choiceDialog;
     }

     public ArrayList<Integer[]> getResources() {
         return mResources;
     }

     public void setResources(ArrayList<Integer[]> resources) {
         mResources = resources;
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
	  public String toString() {
      	//Making mResouces[][] into a String
		  StringBuilder sb = new StringBuilder();
		  sb.append("[");
		  for(int i = 0; i<choiceDialog.length; i++) {
			  sb.append(choiceDialog[i] + ", ");
		  }
		  sb.deleteCharAt(sb.length() -1);//Deletes the last comma in the Stringbuilder
		  sb.append("]");
		  
		  
		  
		  
      	String resources = "{[";
      	for(int i = 0; i < mResources.size(); i++) {
      	    resources += mResources.get(i)[0];
              for (int j = 1; j < 4; j++) {
                  resources += ", " + mResources.get(i)[j];
              }
              if(i != 2)
                  resources += "], [";
          }
      	resources += "]}";
      		
      	
          return "{" +
                  d.toString() +
                  
                  ", " + choices.toString() +
                  ", " + sb.toString() +
                  ", " + resources +
                  ", " + difficulty +
                  ", " + location +
                  "}";
      }
  }


