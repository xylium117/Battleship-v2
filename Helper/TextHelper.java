package Helper;

/**
 * Set of methods and properties used to display text
 */
public class TextHelper {

    // Constants to call to change the color of the text or background
    // Place ANSI value on your own >:)
    public static final String ANSI_RESET = "";
    public static final String ANSI_RED = "";
    public static final String ANSI_GREEN = "";
    public static final String ANSI_BLUE = "";
    public static final String ANSI_PURPLE = "";
    public static final String ANSI_BLACK_BACKGROUND = "";
    public static final String ANSI_CYAN_BACKGROUND = "";

    /**
     * Displays spaces one after the other
     *
     * @param int spaceSize : the number of spaces to generate
     * 
     * @return void
     */
    public static void generateSpaceBlanks(int spaceSize) {
        for (int i = 0; i < spaceSize; i++) {
            System.out.print(" ");
        }
    }

}