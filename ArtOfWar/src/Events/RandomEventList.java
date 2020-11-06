package Events;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * RandomEventList : A class to create a List of Events
 *
 * Things
 *
 * @author Nathan Potraz
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
            if (!fileName.equals("events.txt")) {
                throw new FileNotFoundException();
            }

            inputFile = new Scanner(new File(fileName));

        } catch(FileNotFoundException e) { //Throws FileNotFound if the file cannot be found
            System.out.println("File does not Exist!");
        }
        for(int i = 0; i < 9; i++)
            nextLine(); //Skips the template of the text file

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
        //System.out.println(currentEvent + "\n"); //For Testing
        //System.out.println(EventList); //For Testing
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
        String data = currentLine.substring(currentLine.indexOf('"')+1, currentLine.length()-1);
        //System.out.println("Command: " + command + ", Data: " + data); //For Testing

        switch(command) {
            case "name":
                currentEvent.setName(data);
                break;
            case "description":
                currentEvent.setDescription(data);
                break;
            case "difficulty":
                int difficulty = Integer.parseInt(data);

                if(difficulty > 2 || difficulty < 0) //Makes sure that it's a valid difficulty number
                    throw new IllegalArgumentException();

                currentEvent.setDifficulty(difficulty);
                break;
            case "reward":
                currentEvent.setReward(data);
                break;
            case "completed":
                data = data.toLowerCase();
                boolean complete = Boolean.parseBoolean(data);

                currentEvent.setCompleted(complete);
                break;
            default:
                throw new IllegalArgumentException();

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
        private String mName;
        private String mDescription;
        private int mDifficulty;
        private String mReward;
        private boolean mCompleted;

        public TestEvent(String name, String description, int difficulty, String reward, boolean completed) {
            mName = name;
            mDescription = description;
            mDifficulty = difficulty;
            mReward = reward;
            mCompleted = completed;
        }
        public TestEvent() {
            this(null, null, 0, null, false);
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public int getDifficulty() {
            return mDifficulty;
        }

        public void setDifficulty(int difficulty) {
            mDifficulty = difficulty;
        }

        public String getReward() {
            return mReward;
        }

        public void setReward(String reward) {
            mReward = reward;
        }

        public boolean isCompleted() {
            return mCompleted;
        }

        public void setCompleted(boolean completed) {
            mCompleted = completed;
        }

        @Override
        public String toString() {
            return "[" +
                    mName +
                    ", " + mDescription +
                    ", " + mDifficulty +
                    ", " + mReward +
                    ", " + mCompleted +
                    ']';
        }
    }
    //For testing the object builder
    public static void main(String[] args) {
        String fileName = "events.txt";
        RandomEventList list = new RandomEventList(fileName);

        System.out.println(list);
    }
}


