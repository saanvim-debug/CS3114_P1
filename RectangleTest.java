
import student.TestCase;

/**
 * This class tests the methods of Rectangle class,
 * ensuring that they work as they should.
 * 
 * @author Yosna Venkatesh
 * @version <version_no>
 */
public class RectangleTest extends TestCase {


    private Rectangle rect;
    
    /**
     * Initializes a rectangle object to be used for the tests.
     */
    public void setUp() {
        rect = new Rectangle(10, 20, 30, 40);
    }

    /**
     * Tests the getter methods for the rectangle's coordinates
     * and dimensions.
     */
    public void testGetters() {
        assertEquals(10, rect.getxCoordinate());
        assertEquals(20, rect.getyCoordinate());
        assertEquals(30, rect.getWidth());
        assertEquals(40, rect.getHeight());
    }

    /**
     * Tests the string representation of a rectangle.
     */
    public void testToString() {
        assertEquals("10, 20, 30, 40", rect.toString());
    }

    /**
     * Tests whether rectangles with the same coordinates and
     * dimensions are considered equal.
     */
    public void testEquals() {
        Rectangle same = new Rectangle(10, 20, 30, 40);
        Rectangle different = new Rectangle(11, 20, 30, 40);

        assertTrue(rect.equals(same));
        assertFalse(rect.equals(different));
        assertFalse(rect.equals("not a rectangle"));
    }

    /**
     * Tests that two overlapping rectangles intersect.
     */
    public void testIntersect() {
        Rectangle overlap = new Rectangle(20, 30, 10, 10);
        assertTrue(rect.intersect(overlap));
    }

    /**
     * Tests that two separated rectangles do not intersect.
     */
    public void testDoesNotIntersect() {
        Rectangle farAway = new Rectangle(500, 500, 10, 10);
        assertFalse(rect.intersect(farAway));
    }

    /**
     * Tests that rectangles whose edges only touch are not
     * considered to intersect.
     */
    public void testTouchingIsNotIntersection() {
        Rectangle first = new Rectangle(10, 10, 5, 5);
        Rectangle second = new Rectangle(15, 10, 5, 5);

        assertFalse(first.intersect(second));
    }

    /**
     * Tests that a rectangle with a negative x-coordinate is invalid.
     */
    public void testInvalidNegativeX() {
        assertTrue(new Rectangle(-1, 0, 10, 10).isInvalid());
    }

    
    /**
     * Tests that a rectangle with a negative y-coordinate is invalid.
     */
    public void testInvalidNegativeY() {
        assertTrue(new Rectangle(0, -1, 10, 10).isInvalid());
    }

    
    /**
     * Tests that a rectangle with zero width is invalid.
     */
    public void testInvalidWidth() {
        assertTrue(new Rectangle(0, 0, 0, 10).isInvalid());
    }

    /**
     * Tests that a rectangle with zero height is invalid.
     */
    public void testInvalidHeight() {
        assertTrue(new Rectangle(0, 0, 10, 0).isInvalid());
    }

    /**
     * Tests that a rectangle extending beyond the right boundary
     * of the world box is invalid.
     */
    public void testOutsideRightBoundary() {
        assertTrue(new Rectangle(1000, 0, 25, 10).isInvalid());
    }

    /**
     * Tests that a rectangle extending beyond the bottom boundary
     * of the world box is invalid.
     */
    public void testOutsideBottomBoundary() {
        assertTrue(new Rectangle(0, 1000, 10, 25).isInvalid());
    }

    /**
     * Tests that a rectangle fitting exactly within the world box
     * is valid.
     */
    public void testValidRectangle() {
        assertFalse(new Rectangle(0, 0, 1024, 1024).isInvalid());
    }
    
}
