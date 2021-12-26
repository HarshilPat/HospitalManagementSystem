/*
 * Description: Implementing command pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */
/**
 * class to add doctor.
 */
public class AddDoctor implements Command{
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user
     * to add a doctor in the system.
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("Adding Doctor to Ward...");
        String name = IOAccess.getInstance().readString("Enter the name of the doctor: ");
        if (DoctorMapAccess.getInstance().containsKey(name))
            throw new IllegalStateException("Doctor not added as there already "
                    + "is a doctor with the name " + name);

        String response = IOAccess.getInstance().readString("Is the doctor a surgeon? (yes or no)");
        Doctor d;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            d = new Surgeon(name);
        else
            d = new Doctor(name);

        // check to make sure the doctor name doesn't already exist
        Doctor sameNumberDoctor = DoctorMapAccess.getInstance().put(name, d);
        if (sameNumberDoctor != null)
        {
            DoctorMapAccess.getInstance().put(name, sameNumberDoctor); // put the original doctor back
            throw new IllegalStateException("Name in the doctor dictionary even though "
                    + "containsKey failed.  Name "  + name + " not entered.");
        }
    }
    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new AddDoctor();
        cmd.execute();
    }
}