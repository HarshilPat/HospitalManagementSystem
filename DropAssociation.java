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
 * class to drop relation between doctor and patient.
 */
public class DropAssociation implements Command{
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user
     * to drop relation between patient and doctor.
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("Dropping a new Doctor-Patient Association...");
        IOAccess.getInstance().outputString("Getting Patient information...");
        String healthNumber = IOAccess.getInstance().readString("Enter the health number of the patient: ");

        Patient p = PatientMapAccess.getInstance().get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        IOAccess.getInstance().outputString("Getting Doctor information...");
        String name = IOAccess.getInstance().readString("Enter the name of the doctor: ");

        Doctor d = DoctorMapAccess.getInstance().get(name);
        if (d == null)
            throw new NoSuchElementException("There is no doctor with name " + name);

        String pHealthNumber = p.getHealthNumber();
        if (!d.hasPatient(pHealthNumber))
            throw new IllegalStateException("This doctor is not associated with this patient.");
        if (!p.hasDoctor(name))
            throw new IllegalStateException("This doctor and this patient are incorrectly "
                    + "associated.  The doctor has the patient, "
                    + "but the patient does not have the doctor");

        p.removeDoctor(name);
        d.removePatient(healthNumber);
    }
    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new DropAssociation();
        cmd.execute();
    }
}
