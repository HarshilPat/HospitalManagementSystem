/*
 * Description: Implementing command pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */
import java.util.Collection;

/**
 * this class displays the current system state.
 */
public class SystemState implements Command{
    /**
     * simpler execute method from the command interface to implement the operation which returns nothing and interacts with user
     * to display the current state of the system.
     */
    @Override
    public void execute() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = PatientMapAccess.getInstance().values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = DoctorMapAccess.getInstance().values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + WardAccess.getInstance();
        IOAccess.getInstance().outputString(result);
    }
    /**
     * testing for above class
     * @param args
     */
    public static void main(String[] args){
        Command cmd = new SystemState();
        cmd.execute();
    }
}
