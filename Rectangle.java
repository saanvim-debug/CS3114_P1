
/**
 * This class holds the coordinates and dimensions of a rectangle and methods to
 * check if it intersects or has the same coordinates as an other rectangle.
 * 
 * @author CS Staff
 * @version 2024-01-22
 */
public class Rectangle
{
    // the x coordinate of the rectangle
    private int xCoordinate;
    // the y coordinate of the rectangle
    private int yCoordinate;
    // the distance from the x coordinate the rectangle spans
    private int width;
    // the distance from the y coordinate the rectangle spans
    private int height;

    /**
     * Creates an object with the values to the parameters given in the
     * xCoordinate, yCoordinate, width, height
     * 
     * @param x
     *            x-coordinate of the rectangle
     * @param y
     *            y-coordinate of the rectangle
     * @param w
     *            width of the rectangle
     * @param h
     *            height of the rectangle
     */
    public Rectangle(int x, int y, int w, int h)
    {
        xCoordinate = x;
        yCoordinate = y;
        width = w;
        height = h;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate()
    {
        return xCoordinate;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate()
    {
        return yCoordinate;
    }


    /**
     * Getter for the width
     *
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }


    /**
     * Getter for the height
     *
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }


    /**
     * Checks if the invoking rectangle intersects with rec.
     * 
     * @param r2
     *            Rectangle parameter
     * @return true if the rectangle intersects with rec, false if not
     */
    public boolean intersect(Rectangle r2)
    {
        return xCoordinate < r2.xCoordinate + r2.width
            && xCoordinate + width > r2.xCoordinate
            && yCoordinate < r2.yCoordinate + r2.height
            && yCoordinate + height > r2.yCoordinate;

    }


    /**
     * Checks, if the invoking rectangle has the same coordinates as rec.
     * 
     * @param rec
     *            the rectangle parameter
     * @return true if the rectangle has the same coordinates as rec, false if
     *             not
     */
    public boolean equals(Object rec)
    {
        if (!(rec instanceof Rectangle))
        {
            return false;
        }

        Rectangle other = (Rectangle)rec;

        return xCoordinate == other.xCoordinate
            && yCoordinate == other.yCoordinate && width == other.width
            && height == other.height;
    }


    /**
     * Outputs a human readable string with information about the rectangle
     * which includes the x and y coordinate and its height and width
     * 
     * @return a human readable string containing information about the
     *             rectangle
     */
    public String toString()
    {
        return xCoordinate + ", " + yCoordinate + ", " + width + ", " + height;
    }


    /**
     * Checks if the rectangle has invalid parameters
     * 
     * @return true if the rectangle has invalid parameters, false if not
     */
    public boolean isInvalid()
    {
        return xCoordinate < 0 || yCoordinate < 0 || width <= 0 || height <= 0
            || xCoordinate + width > 1024 || yCoordinate + height > 1024;
    }
}
