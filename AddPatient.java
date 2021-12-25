/*
 * Description: Implementing command pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */
/**
 * class adds patient to system
 */
public class AddPatient implements Command{
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user
     * to add a patient in the system.
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("Adding Patient to Ward...");
        String name = IOAccess.getInstance().readString("Enter the name of the patient: ");
        String healthNum = IOAccess.getInstance().readString("Enter the health number of the patient as string: ");
        if (PatientMapAccess.getInstance().containsKey(healthNum))
        {
            throw new  IllegalStateException("Patient with the health number " + healthNum + " already exsists");
        }

        Patient p = new Patient(name, healthNum);
        Patient result = PatientMapAccess.getInstance().put(healthNum, p);

        // checking to make sure the the key was unique
        if (result != null)
        {
            PatientMapAccess.getInstance().put(healthNum, result);  // put the original patient back
            throw new IllegalStateException("Health number in the patient dictionary even "
                    + "though containsKey failed.  Number " + healthNum + " not entered.");
        }
    }
    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new AddPatient();
        cmd.execute();
    }
}
