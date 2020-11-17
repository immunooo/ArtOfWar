package Events;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * RandomEventList : A class to create a List of Events
 *
 * Things
 *
 * @author Nathan Potraz, Elijah Pichler
 * @version 1.0
 */
public class RandomEventList {
    private ArrayList<TestEvent> EventList = new ArrayList<>();
    private Scanner inputFile;
    private String currentLine;
    private TestEvent currentEvent = new TestEvent();

    /**
     * Creates an ArrayList of Random Events from a text file
     * @param fileName The name of the text file
     */
    public RandomEventList(String fileName) {
        try { //Makes sure that the file exists.
            if (!fileName.equals("randomevents.txt")) {
                throw new FileNotFoundException();
            }

            inputFile = new Scanner(new File(fileName));

        } catch(FileNotFoundException e) { //Throws FileNotFound if the file cannot be found
            System.out.println("File does not Exist!");
        }
//        for(int i = 0; i < 9; i++)
//            nextLine(); //Skips the template of the text file
        
        // skips the first line 
        nextLine();
        int lineNumber = 0; //For testing
        String command; //The command of the line so the Event data is added correctly
        int colonIndex;

        while(hasMoreCommands()) {
            nextLine();
            lineNumber++; //For testing

            if(getCurrentLine().equals("/~/")) { //Checks to see if the end of the Event has been reached
                EventList.add(currentEvent); //Adds it to the EventList
                currentEvent = new TestEvent();
                //System.out.println(currentEvent); //For Testing
            } else { //Still in the process of creating an Event
                colonIndex = currentLine.indexOf(':'); //Checks the index of the command colon

                command = currentLine.substring(0, colonIndex); //Grabs the data type;
                inputEventData(command);
            }
            //System.out.println("Command: " + command); //For Testing
        }
        EventList.add(currentEvent); //Adds the final event to the EventList
        System.out.println(currentEvent + "\n"); //For Testing
        System.out.println(EventList); //For Testing
    }

    /**
     * Checks to see if the text file has more lines
     * @return True if there are more lines, false if not
     */
    public boolean hasMoreCommands()  {
        return inputFile.hasNextLine();
    }

    /**
     * Returns the line that is currently being parsed
     * @return the Line in the file
     */
    public String getCurrentLine() {
        return currentLine;
    }

    /**
     * Updates the current line to the next line
     */
    public void nextLine() {
        currentLine = inputFile.nextLine();
    }

    /**
     * Inputs the data from the Events in the text file and sets the currentEvent data to the data in the file
     * @param command The Field in TestEvent that is being updated
     */
    public void inputEventData(String command) {
        //Grabs the String that is inside of the quotations
        //Add if statement for dialog and possibly choices since they're going to have multiple inputs
    	
    	String data = "";
    	if(command.equals("difficulty") || command.equals("location"))
            data = currentLine.substring(currentLine.indexOf('"')+1, currentLine.length()-1);

        //System.out.println("Command: " + command + ", Data: " + data); //For Testing

        switch(command) {
            case "dialog":
            	ArrayList<String> dialogList = new ArrayList<>(); //list of all dialogs
            	parsingLogic(dialogList, currentLine, data);
            	currentEvent.setDialog(dialogList);
                break;
            case "picture":
                ArrayList<String> pictureList = new ArrayList<>();
                parsingLogic(pictureList, currentLine, data);
                currentEvent.setPicture(pictureList);
                break;
            case "choices":
            	ArrayList<String> choiceList = new ArrayList<>();
                parsingLogic(choiceList, currentLine, data);
                currentEvent.setChoices(choiceList);
                break;
            case "resource1":
            	String[] resources1 = new String[3];
            	resourceParsing(resources1,currentLine);
            	currentEvent.mResources.add(resources1);
                break;
            case "resource2":
            	String[] resources2 = new String[3];
            	resourceParsing(resources2,currentLine);
            	currentEvent.mResources.add(resources2);
                break;
            case "resource3":
            	String[] resources3 = new String[3];
            	resourceParsing(resources3,currentLine);
            	currentEvent.mResources.add(resources3);
                break;
            case "difficulty":
            	currentEvent.setDifficulty(data);
            	break;
            case "location":
            	currentEvent.setLocation(data);
            	break;
            	
            default:
                throw new IllegalArgumentException();

        }
    }
    
    /**
     * This method is a helper method for parsing
     * @param list The arraylist for current command
     * @param line current line of code
     */
    public void parsingLogic(ArrayList<String> list, String currentLine, String data) {
    	boolean flag = true;

        // currentLine = (dialog: "hi", "hello", "bye") ~~~ word =("hi", "hello", "bye")

        String word = currentLine.substring(currentLine.indexOf(":")+2);
        word = word.replaceAll("\"", ""); //word = hi, hello, bye

        while(flag) {
            if(word.indexOf(",") != -1) {
                list.add(word.substring(0,word.indexOf(",")));
                word = word.substring(word.indexOf(",")+2);
            } else { 
                list.add(word);
                flag = false;
            }


        }
        

        //Making data equal to the items within the arraylist
        data = "[";
        for(int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1)
                data += list.get(i) + "]";
            else
                data += list.get(i) + ",";
        }
    	
    }
    
    public void resourceParsing(String[] list, String currentLine) {
    	int indexTracker = 0;
    	boolean flag = true;
    	String word = currentLine.substring(currentLine.indexOf(":")+2);
        word = word.replaceAll("\"", ""); //word = hi, hello, bye
        
    	while(flag) {
            if(word.indexOf(",") != -1) {
                list[indexTracker++] = word.substring(0,word.indexOf(","));
                word = word.substring(word.indexOf(",")+2);
                
            } else {
                list[indexTracker] = word;
                flag = false;
            }
    	}
    }

    @Override
    public String toString() {
        String output = "";
        output += EventList.get(0);
        for(int i = 1; i < EventList.size(); i++) {
            output += ",\n";
            output += EventList.get(i);
        }

        return output;
    }

    /**
     * Purely for Testing purposes and getting inputting a file working. Not at all part of the
     * final product
     */
    public class TestEvent {
        private ArrayList<String> mDialog;
        private ArrayList<String> mPicture;
        private ArrayList<String> mChoices;
        private ArrayList<String[]> mResources;
        private String mDifficulty;
        private String mLocation;

        public TestEvent(ArrayList<String> dialog, ArrayList<String> picture, ArrayList<String> choices,  ArrayList<String[]> resources, String difficulty, String location) {
            mDialog = dialog;
            mPicture = picture;
            mChoices = choices;
            mResources = resources;
            mDifficulty = difficulty;
            mLocation = location;
        }
        public TestEvent() {
            this(null, null, null, null, null, null);
        }

        public ArrayList<String> getDialog() {
            return mDialog;
        }

        public void setDialog(ArrayList<String> dialog) {
            mDialog = dialog;
        }

        public ArrayList<String> getPicture() {
            return mPicture;
        }

        public void setPicture(ArrayList<String> picture) {
            mPicture = picture;
        }


        public ArrayList<String> getChoices() {
            return mChoices;
        }

        public void setChoices(ArrayList<String> choices) {
            mChoices = choices;
        }


        public ArrayList<String[]> getResources() {
            return mResources;
        }

        public void setResources(ArrayList<String[]> resources) {
            mResources = resources;
        }
        
        public String getDifficulty() {
        	return mDifficulty;
        }
        
        public void setDifficulty(String difficulty) {
        	mDifficulty = difficulty;
        }
        
        public String getLocation() {
        	return mDifficulty;
        }
        
        public void setLocation(String location) {
        	mLocation = location;
        }

        

        @Override
        public String toString() {
        	//Making mResouces[][] into a String
        	String resources = "[";
        	for(int i = 0; i < mResources.size(); i++) 
        		for(int j = 0; j <3; j++) {
        			if(i == 2 && j == 2) 
        				resources += mResources.get(i)[j] + "]";
        			resources += mResources.get(i)[j] + ", ";
        		}
        		
        	
            return "[" +
                    mDialog.toString() +
                    ", " + mPicture.toString() +
                    ", " + mChoices.toString() +
                    ", " + mChoices +
                    ", " + resources +
                    ", " + mDifficulty +
                    ", " + mLocation +
                    ']';
        }
    }
    //For testing the object builder
    public static void main(String[] args) {
        String fileName = "randomevents.txt";
        RandomEventList list = new RandomEventList(fileName);

        System.out.println(list);
    }
}


