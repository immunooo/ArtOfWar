package Events;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DialogueTest {
	
	public static final ArrayList<String> Text_Values = new ArrayList<String>();
	public static final String[] Text_Values2 = {"One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Eleven,"};
	public static final ArrayList<String> Picture_Values = new ArrayList<String>();
	public static final String[] Picture_Values2 = {"path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11"};
	
	public Dialogue setUp() { //for a filled Dialogue
		for (int i = 0; i < Text_Values2.length; i++) {
			Text_Values.add(Text_Values2[i]);
			Picture_Values.add(Picture_Values2[i]);
		}
		Dialogue test = new Dialogue(Text_Values, Picture_Values);
		return test;
	}

	@Test
	public void testGetText() {
		Dialogue test = setUp();
		assertEquals(test.getText(), Text_Values);
	}

	@Test
	public void testGetPicture() {
		Dialogue test = setUp();
		assertEquals(test.getPicture(), Picture_Values);
	}

	@Test
	public void testOffer() {
		Dialogue test = setUp();
		String text = "hello world";
		String picture = "picture";
		test.Offer(text, picture);
		 assertTrue(test.getText().contains(text));
		 assertTrue(test.getPicture().contains(picture));
	}

	@Test
	public void testPeek() {
		Dialogue test = new Dialogue();
		String text = "Hello";
		String Picture = "World";
		test.Offer(text, Picture);
		String[] tester = {"Hello", "World"};
		String[] test2 = test.peek();
		for(int i = 0; i < test2.length; i++) {
			
		assertEquals(test2[i], tester[i]);
		}
	}

	@Test
	public void testPoll() {
		Dialogue test = new Dialogue();
		String text = "Hello";
		String Picture = "World";
		test.Offer(text, Picture);
		test.poll();
		assertTrue(test.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		Dialogue test = new Dialogue();
		assertTrue(test.isEmpty());
	}

	@Test
	public void testRemove() {
		Dialogue test = new Dialogue();
		String text = "Hello";
		String Picture = "World";
		test.Offer(text, Picture);
		test.remove();
		assertTrue(test.isEmpty());
	}

}