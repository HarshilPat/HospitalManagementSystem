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
 * class creates relation between doctor and patient.
 */
public class AssignDoctorToPatient implements Command{
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user
     * to make relation between a doctor and patient.
     */
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("Assigning a new Doctor-Patient Association...");
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
        else
        {
            p.addDoctor(d);
            d.addPatient(p);
        }
    }
    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new AssignDoctorToPatient();
        cmd.execute();
    }
}
