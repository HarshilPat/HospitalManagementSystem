
/*
 * Description: ConsoleIO class which implements InputOutputInterface methods.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */

import java.util.Scanner;

/**
 * ConsoleIO class which is a interface method implements InputOutputInterface for all the user interactions
 * in every class
 */
public class ConsoleIO implements InputOutputInterface {
    Scanner consoleIN = new Scanner(System.in);

    /**
     * method to get user input for choice menu.
     * @param options an array with the options that are presented to the user
     * @return option number selected by the user
     */
    public int readChoice(String[] options) {
        this.outputString("Options:");
        for (int i = 0; i <= options.length - 1; i++) {
            this.outputString("\t" + options[i]);
        }
        this.outputString("\nChoose from the following options:" + "\n{1-"
                + (options.length) + "}: ");
            int intInput = consoleIN.nextInt();
            consoleIN.nextLine();
            if (intInput > 0 && intInput <= options.length) {
                outputString("user entered: " + intInput);
                return intInput;
            }else {
                outputString("Invalid input.");
                return readChoice(options);
            }

    }

    /**
     * method to get the integer value from user
     * @param prompt the string to be displayed as a prompt
     * @return integer value entered by the user
     */
    public int readInt(String prompt) {
        this.outputString(prompt);
        int inputInt = consoleIN.nextInt();
        consoleIN.nextLine();
        this.outputString("user entered: " + inputInt);
        return inputInt;
    }

    /**
     * method to get the string value from user
     * @param prompt the string to be displayed as a prompt
     * @return string value entered by the user
     */
    public String readString(String prompt) {
        this.outputString(prompt);
        if (consoleIN.hasNext(" ")) {
            this.outputString("Invalid Input! Enter String.");
            return readString(prompt);
        }
        String inpString = String.valueOf(consoleIN.nextLine());
        outputString("user entered: " + inpString);
        return inpString;
    }

    /**
     * method to display a given string to the console
     * @param outString the string whose value is to be displayed
     */
    public void outputString(String outString) {
        System.out.println(outString);
    }

    /**
     * main method to test all methods in ConcoleIO class
     * @param args
     */
    public static void main(String[] args) {
        ConsoleIO console = new ConsoleIO();
        String[] options = {"1: enter number", "2: divide  number"};
        console.readChoice(options);
        console.readInt("enter a number: ");
        console.readString("enter your name: ");
    }
}