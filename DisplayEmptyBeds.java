/*
 * Description: Implementing command pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */
import java.util.LinkedList;

/**
 * class to diaplay empty beds in the wared
 */
public class DisplayEmptyBeds implements Command{
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user
     * to diaplay empty beds in a ward.
     */
    @Override
    public void execute() {
        LinkedList<Integer> dispBeds = new LinkedList<>(WardAccess.getInstance().availableBeds());
        IOAccess.getInstance().outputString(dispBeds+"");
    }
    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new DisplayEmptyBeds();
        cmd.execute();
    }
}
