package Capital;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Army Tester.
 *
 * @author <Xiaoyue Zhang>
 * @since <pre>Nov 3 2020</pre>
 * @version 1.0
 */
public class ArmyTest {

    private final int SIZE = 120;
    private final int MORALE = 130;
    private final int FORMATION = 140;
    private final int GOLD = 50;
    private final int FOOD = 200;
    private Army defaultArmy;
    private Army customizeArmy;

    @Before
    public void before() throws Exception {
        defaultArmy = new Army();
        customizeArmy = new Army(SIZE, MORALE, FORMATION, GOLD, FOOD);
    }

    /**
     * Method: getApproximateSize()
     */
    @Test
    public void testGetApproximateSize() throws Exception {
        assertEquals("Test Failed: Default Constructor Size Getter", 100, defaultArmy.getApproximateSize());
        assertEquals("Test Failed: customize Constructor Size Getter", SIZE, customizeArmy.getApproximateSize());
    }

    /**
     * Method: setSize(int newSize)
     */
    @Test
    public void testSetSize() throws Exception {
        defaultArmy.setSize(SIZE);
        assertEquals("Test Failed: Size Setter", SIZE, defaultArmy.getApproximateSize());
    }

    /**
     * Method: getMorale()
     */
    @Test
    public void testGetMorale() throws Exception {
        assertEquals("Test Failed: Default Constructor Morale Getter", 100, defaultArmy.getMorale());
        assertEquals("Test Failed: customize Constructor Morale Getter", MORALE, customizeArmy.getMorale());
    }

    /**
     * Method: getFormation()
     */
    @Test
    public void testGetFormation() throws Exception {
        assertEquals("Test Failed: Default Constructor Formation Getter", 100, defaultArmy.getFormation());
        assertEquals("Test Failed: customize Constructor Formation Getter", FORMATION, customizeArmy.getFormation());
    }

    /**
     * Method: setFormation(int newFormation)
     */
    @Test
    public void testSetFormation() throws Exception {
        defaultArmy.setFormation(FORMATION);
        assertEquals("Test Failed: Formation Setter", FORMATION, defaultArmy.getFormation());
    }

    /**
     * Method: getResources()
     */
    @Test
    public void testGetResources() throws Exception {
        defaultArmy.getResources().setGold(GOLD);
        defaultArmy.getResources().setFood(FOOD);
        assertEquals("Test Failed: Resources Getter", GOLD, defaultArmy.getResources().getGold());
        assertEquals("Test Failed: Resources Getter", FOOD, defaultArmy.getResources().getFood());
    }


}
