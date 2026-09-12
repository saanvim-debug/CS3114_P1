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

        assertFuzzyEquals(
            "Unrecognized command.",
            systemOut().getHistory());
    }

    /**
     * Tests that an insert command is processed correctly.
     */
    public void testInsert() {
        processor.processor("insert a 1 2 3 4");
    }

    /**
     * Tests that a remove command using a rectangle name
     * is processed correctly.
     */
    public void testRemoveByName() {
        processor.processor("remove a");
    }

    /**
     * Tests that a remove command using rectangle coordinates
     * and dimensions is processed correctly.
     */
    public void testRemoveByCoordinates() {
        processor.processor("remove 1 2 3 4");
    }

    /**
     * Tests that a regionsearch command is processed correctly.
     */
    public void testRegionSearch() {
        processor.processor("regionsearch 1 2 3 4");
    }

    /**
     * Tests that an intersections command is processed correctly.
     */
    public void testIntersections() {
        processor.processor("intersections");
    }

    /**
     * Tests that a search command using a rectangle name
     * is processed correctly.
     */
    public void testSearch() {
        processor.processor("search a");
    }

    /**
     * Tests that a dump command is processed correctly.
     */
    public void testDump() {
        processor.processor("dump");
    }


}


