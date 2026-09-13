import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import student.TestCase;

/**
 * This class tests the methods of Rectangle1 class which serves as the entry
 * point of the command line program.
 * 
 * @author Patrick Sullivan
 * @version 2024.1
 */
public class SkipListProjectTest
    extends TestCase
{

    /**
     * Tests the main method when the file name is valid.
     */
    public void testMain()
    {
        SkipListProject project = new SkipListProject();
    }


    /**
     * Tests the main method when no filename is given.
     */
    public void testNoArguments()
    {
        String[] args = {};

        SkipListProject.main(args);

        assertEquals(
            "Invalid file. No filename in command line arguments\n",
            systemOut().getHistory());
    }


    /**
     * Tests main when the file cannot be found.
     */
    public void testInvalidFile()
    {
        String[] args = { "doesNotExist.txt" };

        SkipListProject.main(args);

        assertTrue(systemOut().getHistory().contains("Invalid file"));
    }


    /**
     * Tests main using a valid command file.
     */
    public void testValidFile()
        throws IOException
    {
        File file = new File("testCommands.txt");

        FileWriter writer = new FileWriter(file);
        writer.write("insert a 1 2 3 4\n");
        writer.close();

        String[] args = { "testCommands.txt" };

        SkipListProject.main(args);

        assertEquals(
            "Rectangle inserted: (a, 1, 2, 3, 4)\n",
            systemOut().getHistory());

        file.delete();
    }


    /**
     * Tests that blank lines are skipped.
     */
    public void testBlankLines()
        throws IOException
    {
        File file = new File("blankLines.txt");

        FileWriter writer = new FileWriter(file);
        writer.write("\n");
        writer.write("   \n");
        writer.write("insert b 5 6 7 8\n");
        writer.close();

        String[] args = { "blankLines.txt" };

        SkipListProject.main(args);

        assertEquals(
            "Rectangle inserted: (b, 5, 6, 7, 8)\n",
            systemOut().getHistory());

        file.delete();
    }
}
