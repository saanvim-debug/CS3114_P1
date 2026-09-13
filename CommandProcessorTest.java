import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly. 
 * Also test passing improper command to ensure 
 * all class functionalities work as intended.
 * 
 * @author <your_name>
 * @version <version_no>
 */
public class CommandProcessorTest extends TestCase {
    
    private CommandProcessor processor;
    
    /**
     * The setUp() method will be called automatically before
     * each test and reset whatever the test modified. For this
     * test class, only a new database object is needed, so 
     * creat a database here for use in each test case.
     */
    public void setUp() {
        processor = new CommandProcessor();
    }
    
    /**
     * Tests that an invalid command produces the correct
     * unrecognized command message.
     */
    public void testInvalidCommand() {
        processor.processor("hello");

        assertEquals(
            "Unrecognized command.\n",
            systemOut().getHistory());
    }

    /**
     * Tests that an insert command is processed correctly.
     */
    public void testInsert() {
        processor.processor("insert a 1 2 3 4");

        assertEquals(
            "Rectangle inserted: (a, 1, 2, 3, 4)\n",
            systemOut().getHistory());
    }

    /**
     * Tests that a remove command using a rectangle name
     * is processed correctly.
     */
    public void testRemoveByName() {
        processor.processor("remove a");

        assertEquals(
            "",
            systemOut().getHistory());
    }

    /**
     * Tests that a remove command using rectangle coordinates
     * and dimensions is processed correctly.
     */
    public void testRemoveByCoordinates() {
        processor.processor("remove 1 2 3 4");

        assertEquals(
            "",
            systemOut().getHistory());
    }

    /**
     * Tests that a regionsearch command is processed correctly.
     */
    public void testRegionSearch() {
        processor.processor("regionsearch 1 2 3 4");

        assertEquals(
            "",
            systemOut().getHistory());
    }

    /**
     * Tests that an intersections command is processed correctly.
     */
    public void testIntersections() {
        processor.processor("intersections");

        assertEquals(
            "",
            systemOut().getHistory());
    }

    /**
     * Tests that a search command using a rectangle name
     * is processed correctly.
     */
    public void testSearch() {
        processor.processor("search a");

        assertEquals(
            "",
            systemOut().getHistory());
    }

    /**
     * Tests that a dump command is processed correctly.
     */
    public void testDump() {
        processor.processor("dump");

        assertEquals(
            "SkipList dump:\n"
            + "Node with depth 1, Value null\n"
            + "SkipList size is: 0\n",
            systemOut().getHistory());

    }
    
    /**
     * Tests processing an insert command with an invalid rectangle.
     */
    public void testInvalidInsert() {
        processor.processor("insert bad -1 2 3 4");

        assertEquals(
            "Rectangle rejected: (bad, -1, 2, 3, 4)\n",
            systemOut().getHistory());
    }
    
    
    /**
     * Tests insert with different values to verify that all
     * parameters are parsed correctly.
     */
    public void testInsertDifferentValues() {
        processor.processor("insert box 10 20 30 40");

        assertEquals(
            "Rectangle inserted: (box, 10, 20, 30, 40)\n",
            systemOut().getHistory());
    }


    /**
     * Tests an insert with zero width.
     */
    public void testInvalidInsertWidth() {
        processor.processor("insert bad 1 2 0 4");

        assertEquals(
            "Rectangle rejected: (bad, 1, 2, 0, 4)\n",
            systemOut().getHistory());
    }


    /**
     * Tests an insert with zero height.
     */
    public void testInvalidInsertHeight() {
        processor.processor("insert bad 1 2 3 0");

        assertEquals(
            "Rectangle rejected: (bad, 1, 2, 3, 0)\n",
            systemOut().getHistory());
    }


    /**
     * Tests dump after inserting multiple rectangles.
     */
    public void testMultipleInsertAndDump() {
        processor.processor("insert a 1 2 3 4");
        processor.processor("insert b 10 20 30 40");

        systemOut().clearHistory();

        processor.processor("dump");

        String output = systemOut().getHistory();

        assertTrue(output.contains("(a, 1, 2, 3, 4)"));
        assertTrue(output.contains("(b, 10, 20, 30, 40)"));
        assertTrue(output.contains("SkipList size is: 2"));
    }

}


