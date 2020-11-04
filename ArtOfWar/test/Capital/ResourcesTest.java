package Capital;

import javafx.scene.layout.GridPane;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.FormatFlagsConversionMismatchException;

/**
 * Resources Tester.
 *
 * @author <Xiaoyue Zhang>
 * @since <pre>Nov 3 2020</pre>
 * @version 1.0
 */
public class ResourcesTest {

    private final int GOLD = 50;
    private final int FOOD = 200;
    Resources defaultResources;
    Resources customizeResources;

    @Before
    public void before() throws Exception {
        defaultResources = new Resources();
        customizeResources = new Resources(GOLD, FOOD);
    }

    /**
     * Method: getGold()
     * */
    @Test
    public void testGetGold() throws Exception {
        assertEquals("Test Failed: Default Constructor Gold Getter", 100, defaultResources.getGold());
        assertEquals("Test Failed: Customize Constructor Gold Getter", GOLD, customizeResources.getGold());
    }

    /**
     * Method: getFood()
     * */
    @Test
    public void testGetFood() throws Exception {
        assertEquals("Test Failed: Default Constructor Food Getter", 100, defaultResources.getFood());
        assertEquals("Test Failed: Customize Constructor Food Getter", FOOD, customizeResources.getFood());
    }

    /**
     * Method: setGold(int newGoldValue)
     * */
    @Test
    public void testSetGold() throws Exception {
        defaultResources.setGold(GOLD);
        assertEquals("Test Failed: Gold Setter", GOLD, defaultResources.getGold());
    }

    /**
     * Method: setFood(int newFoodValue)
     * */
    @Test
    public void testSetFood() throws Exception {
        defaultResources.setFood(FOOD);
        assertEquals("Test Failed: Gold Setter", FOOD, defaultResources.getFood());
    }
}
