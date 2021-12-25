/*
 * Description: IOAccess class which is a singleton pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */

/**
 * class to set the user interface of the system
 */
public class IOAccess {
    /**
     * singleton attribute
     */
    private static InputOutputInterface io;

    /**
     * private constructor for singleton class
     */
    private IOAccess() {
        io = null;
    }

    /**
     * static method to get the instance of IOInterface which user wants
     * @return io returns an object with ConsoleIO or DialogIO
     */
    public static InputOutputInterface getInstance() {
        if (io == null) {
            DialogIO cIO = new DialogIO();
            String in = cIO.readString("Which kind of interaction do you want?" +
                    "\n\tA: ConsoleIO" +
                    "\n\tB: DialogIO" +
                    "\n-> ");
            if (in.equals("a") || in.equals("A")) {
                io = new ConsoleIO();
            } else if (in.equals("b") || in.equals("B")) {
                io = new DialogIO();
            } else {
                throw new RuntimeException("invalid input for IOAccess");
            }
        }
        return io;
    }
}
