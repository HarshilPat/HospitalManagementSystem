/*
 * Description: DialogIO class which extends AbstractDialogIO methods.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */

import javax.swing.*;

/**
 * DialogIO class which extands the AbstractDialogIO class which represents the information to user
 * with GUI.
 */
public class DialogIO extends AbstractDialogIO{

    /**
     * Displays the given string in the dialog box.
     * @param outString the string whose value is to be displayed
     */
    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null,outString);
    }

    /**
     * takes integer input from user through dialog box
     * @param prompt the string to be displayed as a prompt
     * @return int value entered by the user
     */
    public int readInt(String prompt) {
        String input=JOptionPane.showInputDialog(prompt);
        if(input == null || input.equals("")){
            JOptionPane.showMessageDialog(null,"Invalid Input!");
            return readInt(prompt);
        }
        int in = Integer.parseInt(input);
        JOptionPane.showMessageDialog(null,"User entered: "+in);
        return in;
    }

    /**
     * takes string input from the user through dialog box
     * @param prompt the string to be displayed as a prompt
     * @return string value entered by the user.
     */
    public String readString(String prompt) {
        String result = JOptionPane.showInputDialog(prompt);
        JOptionPane.showMessageDialog(null,"User entered: "+result);
        return result;
    }

    /**
     * displays the choice option dialog box menu
     * @param options an array with the options that are presented to the user
     * @return choice option menu dialog box to user
     */
    public int readChoice(String[] options) {
        return super.readChoice(options);
    }

    /**
     * main method to test all the methods in DialogIO class
     * @param args
     */
    public static void main(String[] args){
        DialogIO Dio = new DialogIO();
        Dio.outputString("Hello this is a test dialog box");
        Dio.readInt("enter a number");
        String[] options = {"1: enter number", "2: divide  number"};
        Dio.readChoice(options);
        Dio.readString("enter a  String");
    }
}