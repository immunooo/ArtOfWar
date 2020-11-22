package Events;

import java.util.ArrayList;
/**
 * Dialogue : a class responsible for holding text and a file location to a picture
 * 
 * Note : this class is a double queue.
 *
 * @author Joseph Berlucchi
 * @version 1.0
 */
public class Dialogue {
	
	private ArrayList<String> text;
	private ArrayList<String> picture;
	
	/**
	 * Constructor of Dialogue class, initializes the two array lists.
	 * 
	 */
	public Dialogue() {
		text = new ArrayList<String>();
		picture = new ArrayList<String>();
	}
	
	
	public Dialogue(ArrayList<String> text, ArrayList<String> picture) {
		this.text = text;
		this.picture = picture;
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public ArrayList<String> getPicture() {
		return picture;
	}
	/**
	 * Inserts new dialogue into the queue 
	 * @param text of the dialogue
	 * @param picture of the dialogue
	 */
	public void Offer(String text, String picture) {
		this.text.add(text);
		this.picture.add(picture);
	}
	
	/**
	 * Returns the top most element without deletion
	 * 
	 * @return a array of size 2 with 0 = dialogue and 1 = picture. Null denotes not dialogue left.
	 */
	public String[] peek() {
		String topText, topPic;
		
		if(isEmpty()) {
			return null;
		}
		
		topText = text.get(0);
		topPic = picture.get(0);
		
		String[] dialogue = {topText, topPic};
		
		return dialogue;
	}
	
	/**
	 * Returns the top most element with deletion
	 * 
	 * @return a array of size 2 with 0 = dialogue and 1 = picture. Null denotes not dialogue left.
	 */
	public String[] poll() {
		try {
			return peek();
		} finally {
			remove();
		}
	}
	
	/**
	 * Determines whether the array list is empty
	 * 
	 * @return whether the queue is empty
	 */
	public boolean isEmpty() {
		return text.size() == 0;
	}
	
	/**
	 * Removes the Strings on top of the queue
	 */
	public void remove() {
		if(!isEmpty()) {
			text.remove(0);
			picture.remove(0);
		}
	}

}
