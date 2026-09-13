import student.TestCase;
import student.TestableRandom;

/**
 * Tests the Database class.
 * 
 * @author Yosna Venkatesh
 * @version 2026-09-13
 */
public class DatabaseTest
    extends TestCase
{

    private Database database;

    /**
     * Creates a new database before each test.
     */
    public void setUp()
    {
        database = new Database();
    }


    /**
     * Tests inserting a valid rectangle.
     */
    public void testValidInsert()
    {
        TestableRandom.setNextBooleans(false);

        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", rect);

        database.insert(pair);

        assertFuzzyEquals(
            "Rectangle inserted: (a, 1, 2, 3, 4)",
            systemOut().getHistory());
    }


    /**
     * Tests rejecting an invalid rectangle.
     */
    public void testInvalidInsert()
    {
        Rectangle rect = new Rectangle(-1, 2, 3, 4);
        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("bad", rect);

        database.insert(pair);

        assertFuzzyEquals(
            "Rectangle rejected: (bad, -1, 2, 3, 4)",
            systemOut().getHistory());
    }


    /**
     * Tests dumping an empty database.
     */
    public void testEmptyDump()
    {
        database.dump();

        assertEquals(
            "SkipList dump:\n" + "Node with depth 1, Value null\n"
                + "SkipList size is: 0\n",
            systemOut().getHistory());
    }


    /**
     * Tests that an inserted rectangle appears in the dump.
     */
    public void testInsertAndDump()
    {
        TestableRandom.setNextBooleans(false);

        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", rect);

        database.insert(pair);

        systemOut().clearHistory();

        database.dump();

        assertEquals(
            "SkipList dump:\n" + "Node with depth 2, Value null\n"
                + "Node with depth 2, Value (a, 1, 2, 3, 4)\n"
                + "SkipList size is: 1\n",
            systemOut().getHistory());
    }
}
