import java.util.Iterator;
import student.TestCase;
import student.TestableRandom;
import java.util.ArrayList;

/**
 * This class tests the methods of SkipList class
 * 
 * @author Saanvi Movva
 * @version 2026-09-27
 */

public class SkipListTest
    extends TestCase
{

    private SkipList<String, Rectangle> sl;

    /**
     * Creates a new SkipList before each test.
     */
    public void setUp()
    {
        sl = new SkipList<String, Rectangle>();
    }


    /***
     * Example 1: Test `randomLevel` method with predetermined random values
     * using `TestableRandom`
     */
    public void testRandomLevelOne()
    {
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
     * Example 2: Test `randomLevel` method with predetermined random values
     * using `TestableRandom`
     */
    public void testRandomLevelFour()
    {
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


    /**
     * Tests if adjustHead increases the number of levels in head so that no
     * element has more indices than the head.
     */
    public void testAdjustHead()
    {
        // Start with an empty SkipList.
        sl = new SkipList<String, Rectangle>();

        // Increase the head to level 3.
        sl.adjustHead(3);

        sl.dump();

        assertEquals(
            "SkipList dump:\n" + "Node with depth 4, Value null\n"
                + "SkipList size is: 0\n",
            systemOut().getHistory());
    }


    /**
     * Tests if insert() inserts the KVPair in the SkipList at its appropriate
     * spot as designated by its lexicoragraphical order.
     */
    public void testInsert()
    {
        // First node gets level 3:
        // true -> 2, true -> 3, false -> stop.
        // Second node gets level 1:
        // false -> stop.
        TestableRandom.setNextBooleans(true, true, false, false);

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
            "SkipList dump:\n" + "Node with depth 4, Value null\n"
                + "Node with depth 2, Value (a, 5, 10, 15, 20)\n"
                + "Node with depth 4, Value (b, 10, 20, 30, 40)\n"
                + "SkipList size is: 2\n",
            systemOut().getHistory());
    }


    /**
     * Tests dump on an empty SkipList, then on a SkipList containing a single
     * inserted node, verifying both the header node and the inserted node are
     * reported correctly.
     */
    public void testDump()
    {
        // Test an empty SkipList first.
        sl = new SkipList<String, Rectangle>();

        sl.dump();

        assertEquals(
            "SkipList dump:\n" + "Node with depth 1, Value null\n"
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
            "SkipList dump:\n" + "Node with depth 2, Value null\n"
                + "Node with depth 2, Value (test, 1, 2, 3, 4)\n"
                + "SkipList size is: 1\n",
            systemOut().getHistory());
    }


    // ----------------------------------------------------------
    /**
     * Tests searching for keys in the SkipList, including an existing key, a
     * missing key, and duplicate keys.
     */
    public void testSearch()
    {
        // Search with one matching rectangle
        TestableRandom.setNextBooleans(false);

        Rectangle rect = new Rectangle(1, 2, 3, 4);

        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", rect);

        sl.insert(pair);

        ArrayList<KVPair<String, Rectangle>> results = sl.search("a");

        assertEquals(1, results.size());
        assertEquals(pair, results.get(0));

        // name does not exist
        assertEquals(0, sl.search("missing").size());

        // duplicate rectangles
        TestableRandom.setNextBooleans(false);

        KVPair<String, Rectangle> second =
            new KVPair<String, Rectangle>("a", new Rectangle(10, 20, 30, 40));

        sl.insert(second);

        // finds both rectangles named "a"
        results = sl.search("a");

        assertEquals(2, results.size());
    }


    /**
     * Tests searching through multiple keys and duplicate keys.
     */
    public void testSearchMultipleKeys()
    {
        TestableRandom.setNextBooleans(false, false, false, false);

        KVPair<String, Rectangle> a =
            new KVPair<String, Rectangle>("a", new Rectangle(1, 1, 1, 1));

        KVPair<String, Rectangle> b1 =
            new KVPair<String, Rectangle>("b", new Rectangle(2, 2, 2, 2));

        KVPair<String, Rectangle> b2 =
            new KVPair<String, Rectangle>("b", new Rectangle(3, 3, 3, 3));

        KVPair<String, Rectangle> c =
            new KVPair<String, Rectangle>("c", new Rectangle(4, 4, 4, 4));

        sl.insert(a);
        sl.insert(b1);
        sl.insert(b2);
        sl.insert(c);

        ArrayList<KVPair<String, Rectangle>> results = sl.search("b");

        assertEquals(2, results.size());
        assertEquals("b", results.get(0).getKey());
        assertEquals("b", results.get(1).getKey());

        assertEquals(1, sl.search("a").size());
        assertEquals(1, sl.search("c").size());

        assertEquals(0, sl.search("d").size());
        assertEquals(0, sl.search("aa").size());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing a KVPair from the SkipList by its key and verifies that
     * removing a nonexistent key returns null.
     */
    public void testRemove()
    {
        TestableRandom.setNextBooleans(false);

        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", new Rectangle(1, 2, 3, 4));

        sl.insert(pair);

        KVPair<String, Rectangle> removed = sl.remove("a");

        assertEquals(pair, removed);
        assertEquals(0, sl.size());

        // key not found
        assertNull(sl.remove("missing"));
        assertEquals(0, sl.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing a KVPair from the SkipList by its Rectangle value and
     * verifies that removing a nonexistent value returns null.
     */
    public void testRemoveByValue()
    {
        TestableRandom.setNextBooleans(false);

        Rectangle rect = new Rectangle(1, 2, 3, 4);

        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", rect);

        sl.insert(pair);

        KVPair<String, Rectangle> removed =
            sl.removeByValue(new Rectangle(1, 2, 3, 4));

        assertEquals(pair, removed);
        assertEquals(0, sl.size());

        // not found

        assertNull(sl.removeByValue(new Rectangle(1, 2, 3, 4)));

        assertEquals(0, sl.size());
    }


    /**
     * Tests removing keys from different positions in the SkipList.
     */
    public void testRemoveMultipleKeys()
    {
        TestableRandom.setNextBooleans(false, false, false);

        KVPair<String, Rectangle> a =
            new KVPair<String, Rectangle>("a", new Rectangle(1, 1, 1, 1));

        KVPair<String, Rectangle> b =
            new KVPair<String, Rectangle>("b", new Rectangle(2, 2, 2, 2));

        KVPair<String, Rectangle> c =
            new KVPair<String, Rectangle>("c", new Rectangle(3, 3, 3, 3));

        sl.insert(a);
        sl.insert(b);
        sl.insert(c);

        assertEquals(3, sl.size());

        // Remove middle
        assertEquals(b, sl.remove("b"));
        assertEquals(2, sl.size());
        assertEquals(0, sl.search("b").size());

        // Remove beginning
        assertEquals(a, sl.remove("a"));
        assertEquals(1, sl.size());

        // Remove end
        assertEquals(c, sl.remove("c"));
        assertEquals(0, sl.size());

        // Remove from empty list
        assertNull(sl.remove("c"));
    }


    /**
     * Tests removing a node that has multiple SkipList levels.
     */
    public void testRemoveMultiLevel()
    {
        // a gets a higher level
        TestableRandom.setNextBooleans(true, true, false, false, false);

        KVPair<String, Rectangle> a =
            new KVPair<String, Rectangle>("a", new Rectangle(1, 1, 1, 1));

        KVPair<String, Rectangle> b =
            new KVPair<String, Rectangle>("b", new Rectangle(2, 2, 2, 2));

        KVPair<String, Rectangle> c =
            new KVPair<String, Rectangle>("c", new Rectangle(3, 3, 3, 3));

        sl.insert(a);
        sl.insert(b);
        sl.insert(c);

        assertEquals(3, sl.size());

        KVPair<String, Rectangle> removed = sl.remove("a");

        assertEquals(a, removed);
        assertEquals(2, sl.size());
        assertEquals(0, sl.search("a").size());
        assertEquals(1, sl.search("b").size());
        assertEquals(1, sl.search("c").size());
    }


    /**
     * Tests removing a value that occurs after other values.
     */
    public void testRemoveByValueMultiple()
    {
        TestableRandom.setNextBooleans(false, false, false);

        Rectangle r1 = new Rectangle(1, 1, 1, 1);
        Rectangle r2 = new Rectangle(2, 2, 2, 2);
        Rectangle r3 = new Rectangle(3, 3, 3, 3);

        KVPair<String, Rectangle> a = new KVPair<String, Rectangle>("a", r1);

        KVPair<String, Rectangle> b = new KVPair<String, Rectangle>("b", r2);

        KVPair<String, Rectangle> c = new KVPair<String, Rectangle>("c", r3);

        sl.insert(a);
        sl.insert(b);
        sl.insert(c);

        KVPair<String, Rectangle> removed = sl.removeByValue(r2);

        assertEquals(b, removed);
        assertEquals(2, sl.size());

        assertEquals(1, sl.search("a").size());
        assertEquals(0, sl.search("b").size());
        assertEquals(1, sl.search("c").size());

        assertNull(sl.removeByValue(new Rectangle(100, 100, 1, 1)));

        assertEquals(2, sl.size());
    }


    /**
     * Tests removing a value stored in a multi-level node.
     */
    public void testRemoveByValueMultiLevel()
    {
        TestableRandom.setNextBooleans(false, true, true, false, false);

        Rectangle r1 = new Rectangle(1, 1, 1, 1);
        Rectangle r2 = new Rectangle(2, 2, 2, 2);
        Rectangle r3 = new Rectangle(3, 3, 3, 3);

        KVPair<String, Rectangle> a = new KVPair<String, Rectangle>("a", r1);

        KVPair<String, Rectangle> b = new KVPair<String, Rectangle>("b", r2);

        KVPair<String, Rectangle> c = new KVPair<String, Rectangle>("c", r3);

        sl.insert(a);
        sl.insert(b);
        sl.insert(c);

        assertEquals(b, sl.removeByValue(r2));

        assertEquals(2, sl.size());
        assertEquals(0, sl.search("b").size());
    }


    /**
     * Tests iteration through an empty and populated SkipList.
     */
    public void testIterator()
    {
        Iterator<KVPair<String, Rectangle>> emptyIterator = sl.iterator();

        assertFalse(emptyIterator.hasNext());

        TestableRandom.setNextBooleans(false, false);

        KVPair<String, Rectangle> a =
            new KVPair<String, Rectangle>("a", new Rectangle(1, 1, 1, 1));

        KVPair<String, Rectangle> b =
            new KVPair<String, Rectangle>("b", new Rectangle(2, 2, 2, 2));

        sl.insert(b);
        sl.insert(a);

        Iterator<KVPair<String, Rectangle>> iterator = sl.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(a, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(b, iterator.next());

        assertFalse(iterator.hasNext());
    }

}
