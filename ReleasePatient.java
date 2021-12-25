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
 * release patient class to release patient from bed
 */
public class ReleasePatient implements Command{
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user to release
     * patient from bed.
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("Releasing a Patient from a Bed...");
        String hlthNumber = IOAccess.getInstance().readString("Enter health number of the patient: ");

        Patient p = PatientMapAccess.getInstance().get(hlthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + hlthNumber);

        if (p.getBedLabel() == -1)
            throw new IllegalStateException(" Patient " + p
                    + " has no bed assigned.");

        WardAccess.getInstance().freeBed(p.getBedLabel());
        p.setBedLabel(-1);
    }

    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new ReleasePatient();
        cmd.execute();
    }
}
