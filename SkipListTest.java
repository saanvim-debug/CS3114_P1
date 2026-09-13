import java.util.Iterator;


import org.junit.Test;

import student.TestCase;
import student.TestableRandom;


/**
 * This class tests the methods of SkipList class
 * 
 * @author Saanvi Movva
 * 
 * @version 2024-01-22
 */

public class SkipListTest extends TestCase {
	
	SkipList<String, Rectangle> sl;
	
	public void setUp() {
		// TODO: implement setup
		sl = new SkipList<String, Rectangle>();
	}

	/***
	 * Example 1: Test `randomLevel` method with 
	 * predetermined random values using `TestableRandom`
	 */
	public void testRandomLevelOne() {
		TestableRandom.setNextBooleans(false);
		sl = new SkipList<String, Rectangle>();
		int randomLevelValue = sl.randomLevel();
		
		// This returns 1 because the first preset 
		// random boolean is `false` which breaks 
		// the `while condition inside the `randomLevel` method
		int expectedLevelValue = 1;  
		
		// Compare the values
		assertEquals(expectedLevelValue, randomLevelValue);
	}
	
	/***
	 * Example 2: Test `randomLevel` method with 
	 * predetermined random values using `TestableRandom`
	 */
	public void testRandomLevelFour() {
		TestableRandom.setNextBooleans(true, true, true, false, true, false);
		sl = new SkipList<String, Rectangle>();
		int randomLevelValue = sl.randomLevel();

		// This returns 4 because the fourth preset 
		// random boolean is `false` which breaks 
		// the `while condition inside the `randomLevel` method
		int expectedLevelValue = 4; 
		
		// Compare the values
		assertEquals(expectedLevelValue, randomLevelValue);
	}
	
    // TODO: implement more tests
	public void testAdjustHead() {
	    // Start with an empty SkipList.
	    sl = new SkipList<String, Rectangle>();

	    // Increase the head to level 3.
	    sl.adjustHead(3);

	    sl.dump();

	    assertEquals(
	        "SkipList dump:\n"
	        + "Node with depth 4, Value null\n"
	        + "SkipList size is: 0\n",
	        systemOut().getHistory());
	}
	
	
	public void testInsert() {
        // First node gets level 3:
        // true -> 2, true -> 3, false -> stop.
        // Second node gets level 1:
        // false -> stop.
        TestableRandom.setNextBooleans(
            true, true, false,
            false);
 
        sl = new SkipList<String, Rectangle>();
 
        Rectangle r1 = new Rectangle(10, 20, 30, 40);
        Rectangle r2 = new Rectangle(5, 10, 15, 20);
 
        KVPair<String, Rectangle> pair1 =
            new KVPair<String, Rectangle>("b", r1);
 
        KVPair<String, Rectangle> pair2 =
            new KVPair<String, Rectangle>("a", r2);
 
        sl.insert(pair1);
        sl.insert(pair2);
 
        assertEquals(2, sl.size());
 
        sl.dump();
 
        assertEquals(
            "SkipList dump:\n"
            + "Node with depth 4, Value null\n"
            + "Node with depth 2, Value (a, 5, 10, 15, 20)\n"
            + "Node with depth 4, Value (b, 10, 20, 30, 40)\n"
            + "SkipList size is: 2\n",
            systemOut().getHistory());
    }
 
    /**
     * Tests dump on an empty SkipList, then on a SkipList containing
     * a single inserted node, verifying both the header node and the
     * inserted node are reported correctly.
     */
    public void testDump() {
        // Test an empty SkipList first.
        sl = new SkipList<String, Rectangle>();
 
        sl.dump();
 
        assertEquals(
            "SkipList dump:\n"
            + "Node with depth 1, Value null\n"
            + "SkipList size is: 0\n",
            systemOut().getHistory());
 
        systemOut().clearHistory();
 
        // Insert one node with level 1.
        TestableRandom.setNextBooleans(false);
 
        Rectangle rect = new Rectangle(1, 2, 3, 4);
 
        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("test", rect);
 
        sl.insert(pair);
        sl.dump();
 
        assertEquals(
            "SkipList dump:\n"
            + "Node with depth 2, Value null\n"
            + "Node with depth 2, Value (test, 1, 2, 3, 4)\n"
            + "SkipList size is: 1\n",
            systemOut().getHistory());
    }
	
	

}
