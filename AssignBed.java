/*
 * Description: Implementing command pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */
import java.util.NoSuchElementException;

/**
 * class to assign a patient a bed
 */

public class AssignBed implements Command {
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user
     * to assign bed to patient in a ward.
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("Assigning a Patient to a Bed...");
        String healthNumber = IOAccess.getInstance().readString("Enter the health number of the patient: ");

        Patient p = PatientMapAccess.getInstance().get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new IllegalStateException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        int bedNum = IOAccess.getInstance().readInt("Enter the bed number for the patient: ");
        if (bedNum < WardAccess.getInstance().getMinBedLabel() || bedNum > WardAccess.getInstance().getMaxBedLabel())
            throw new IllegalArgumentException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + WardAccess.getInstance().getMinBedLabel()
                    + " and " + WardAccess.getInstance().getMaxBedLabel());

        p.setBedLabel(bedNum);
        WardAccess.getInstance().assignPatientToBed(p, bedNum);
    }
    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new AssignBed();
        cmd.execute();
    }
}
