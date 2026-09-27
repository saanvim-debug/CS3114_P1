
import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author Saanvi Movva
 * @version 2026-09-27
 */
public class KVPairTest
    extends TestCase
{
    private Rectangle rect;
    private KVPair<String, Rectangle> pair;

    /**
     * Sets up the test objects before each test method. Creates a Rectangle and
     * a KVPair containing the rectangle.
     */
    public void setUp()
    {
        rect = new Rectangle(1, 2, 3, 4);
        pair = new KVPair<String, Rectangle>("box", rect);
    }

    // ----------------------------------------------------------


    /**
     * Tests the KVPair getKey(), getValue(), and toString() methods to ensure
     * they return the expected values.
     */
    public void testKVPair()
    {
        assertEquals("box", pair.getKey());
        assertEquals(rect, pair.getValue());
        assertEquals("(box, 1, 2, 3, 4)", pair.toString());
    }

}
