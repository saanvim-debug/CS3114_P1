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
    
    /**
     * Tests removing a rectangle by its name.
     */
    public void testRemoveByName() 
    {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", rect);

        database.insert(pair);

        systemOut().clearHistory();

        database.remove("a");

        assertEquals(
            "Rectangle removed: (a, 1, 2, 3, 4)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests removing a name that is not in the database.
     */
    public void testRemoveByNameNotFound() 
    {
        database.remove("missing");

        assertEquals(
            "Rectangle not removed: missing\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests removing a rectangle by its dimensions.
     */
    public void testRemoveByCoordinates() 
    {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", rect);

        database.insert(pair);

        systemOut().clearHistory();

        database.remove(1, 2, 3, 4);

        assertEquals(
            "Rectangle removed: (a, 1, 2, 3, 4)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests removing dimensions that are not in the database.
     */
    public void testRemoveByCoordinatesNotFound() 
    {
        database.remove(1, 2, 3, 4);

        assertEquals(
            "Rectangle not found: (1, 2, 3, 4)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests removing an invalid rectangle.
     */
    public void testRemoveInvalidRectangle() 
    {
        database.remove(-1, -1, 2, 4);

        assertEquals(
            "Rectangle rejected: (-1, -1, 2, 4)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests searching for a name that does not exist.
     */
    public void testSearchNotFound() 
    {
        database.search("a");

        assertEquals(
            "Rectangle not found: (a)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests searching for an existing rectangle.
     */
    public void testSearchFound() 
    {
        Rectangle rect = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> pair =
            new KVPair<String, Rectangle>("a", rect);

        database.insert(pair);

        systemOut().clearHistory();

        database.search("a");

        assertEquals(
            "Rectangles found:\n"
            + "(a, 1, 2, 3, 4)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests searching when multiple rectangles
     * have the same name.
     */
    public void testSearchDuplicates() 
    {
        Rectangle rect1 = new Rectangle(1, 2, 3, 4);
        Rectangle rect2 = new Rectangle(10, 20, 30, 40);

        database.insert(
            new KVPair<String, Rectangle>("a", rect1));

        database.insert(
            new KVPair<String, Rectangle>("a", rect2));

        systemOut().clearHistory();

        database.search("a");

        String output = systemOut().getHistory();

        assertTrue(output.contains("Rectangles found:"));
        assertTrue(output.contains("(a, 1, 2, 3, 4)"));
        assertTrue(output.contains("(a, 10, 20, 30, 40)"));
    }
    
    
    /**
     * Tests regionSearch with an invalid width.
     */
    public void testRegionSearchInvalidWidth() 
    {
        database.regionsearch(0, 0, -10, 20);

        assertEquals(
            "Rectangle rejected: (0, 0, -10, 20)\n",
            systemOut().getHistory());
    }
    
    
    /**
     * Tests regionSearch with an invalid height.
     */
    public void testRegionSearchInvalidHeight() 
    {
        database.regionsearch(0, 0, 10, 0);

        assertEquals(
            "Rectangle rejected: (0, 0, 10, 0)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests regionSearch when a rectangle intersects
     * the search region.
     */
    public void testRegionSearchFound() 
    {
        Rectangle rect = new Rectangle(10, 10, 20, 20);

        database.insert(
            new KVPair<String, Rectangle>("a", rect));

        systemOut().clearHistory();

        database.regionsearch(15, 15, 10, 10);

        assertEquals(
            "Rectangles intersecting region "
            + "(15, 15, 10, 10):\n"
            + "(a, 10, 10, 20, 20)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests regionSearch when no rectangles intersect
     * the search region.
     */
    public void testRegionSearchNotFound() 
    {
        Rectangle rect = new Rectangle(10, 10, 20, 20);

        database.insert(
            new KVPair<String, Rectangle>("a", rect));

        systemOut().clearHistory();

        database.regionsearch(100, 100, 10, 10);

        assertEquals(
            "Rectangles intersecting region "
            + "(100, 100, 10, 10):\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests that a region may extend outside the world box.
     */
    public void testRegionSearchOutsideWorld() 
    {
        Rectangle rect = new Rectangle(0, 0, 20, 20);

        database.insert(
            new KVPair<String, Rectangle>("a", rect));

        systemOut().clearHistory();

        database.regionsearch(-10, -10, 30, 30);

        assertEquals(
            "Rectangles intersecting region "
            + "(-10, -10, 30, 30):\n"
            + "(a, 0, 0, 20, 20)\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests intersections with an empty database.
     */
    public void testIntersectionsEmpty() 
    {
        database.intersections();

        assertEquals(
            "Intersection pairs:\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests two rectangles that intersect.
     */
    public void testIntersectionsFound() 
    {
        Rectangle rect1 =
            new Rectangle(10, 10, 15, 15);

        Rectangle rect2 =
            new Rectangle(11, 11, 5, 5);

        database.insert(
            new KVPair<String, Rectangle>("a", rect1));

        database.insert(
            new KVPair<String, Rectangle>("b", rect2));

        systemOut().clearHistory();

        database.intersections();

        assertEquals(
            "Intersection pairs:\n"
            + "(a, 10, 10, 15, 15 | "
            + "b, 11, 11, 5, 5)\n",
            systemOut().getHistory());
    }
    
    
    
    /**
     * Tests rectangles that do not intersect.
     */
    public void testIntersectionsNotFound() 
    {
        Rectangle rect1 =
            new Rectangle(10, 10, 10, 10);

        Rectangle rect2 =
            new Rectangle(100, 100, 10, 10);

        database.insert(
            new KVPair<String, Rectangle>("a", rect1));

        database.insert(
            new KVPair<String, Rectangle>("b", rect2));

        systemOut().clearHistory();

        database.intersections();

        assertEquals(
            "Intersection pairs:\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests that rectangles that only touch edges
     * are not considered intersecting.
     */
    public void testIntersectionsTouching() 
    {
        Rectangle rect1 =
            new Rectangle(10, 10, 5, 5);

        Rectangle rect2 =
            new Rectangle(15, 10, 5, 5);

        database.insert(
            new KVPair<String, Rectangle>("a", rect1));

        database.insert(
            new KVPair<String, Rectangle>("b", rect2));

        systemOut().clearHistory();

        database.intersections();

        assertEquals(
            "Intersection pairs:\n",
            systemOut().getHistory());
    }
    
    /**
     * Tests multiple intersecting rectangles and ensures
     * that each pair is reported only once.
     */
    public void testMultipleIntersections() 
    {
        database.insert(
            new KVPair<String, Rectangle>(
                "a", new Rectangle(10, 10, 20, 20)));

        database.insert(
            new KVPair<String, Rectangle>(
                "b", new Rectangle(15, 15, 20, 20)));

        database.insert(
            new KVPair<String, Rectangle>(
                "c", new Rectangle(18, 18, 5, 5)));

        systemOut().clearHistory();

        database.intersections();

        String output = systemOut().getHistory();

        assertTrue(output.contains(
            "(a, 10, 10, 20, 20 | b, 15, 15, 20, 20)"));

        assertTrue(output.contains(
            "(a, 10, 10, 20, 20 | c, 18, 18, 5, 5)"));

        assertTrue(output.contains(
            "(b, 15, 15, 20, 20 | c, 18, 18, 5, 5)"));
    }
    
    
}
