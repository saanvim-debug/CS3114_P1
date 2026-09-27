
import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author Saanvi Movva
 * @version 2024.1
 */
public class KVPairTest
    extends TestCase
{
    private Rectangle rect;
    private KVPair<String, Rectangle> pair;

    public void setUp()
    {
        rect = new Rectangle(1, 2, 3, 4);
        pair = new KVPair<String, Rectangle>("box", rect);
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testKVPair()
    {
        assertEquals("box", pair.getKey());
        assertEquals(rect, pair.getValue());
        assertEquals("(box, 1, 2, 3, 4)", pair.toString());
    }

}
