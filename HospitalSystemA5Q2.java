/*
  CMPT 270 Course material
  Copyright (c) 2020
  All rights reserved.

  This document contains resources for homework assigned to students of
  CMPT 270 and shall not be distributed without permission.  Posting this
  file to a public or private website, or providing this file to a person
  not registered in CMPT 270, constitutes Academic Misconduct, according
  to the University of Saskatchewan Policy on Academic Misconduct.

  Synopsis:
     Solution file for Assignment 4
 */
/*
 * Description: Implementing the ConsoleIO methods which uses the InputOutputInterface.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */

import java.util.*;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystemA5Q2
{
    private ConsoleIO consoleIO;

    /**
     * The keyed dictionary of all patients.
     */
    private Map<String, Patient> patients;

    /**
     * The keyed dictionary of all doctors.
     */
    private Map<String, Doctor> doctors;

    /**
     * The ward to be handled.
     */
    private Ward ward;

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relavent information
     */
    public HospitalSystemA5Q2() {

        patients = new TreeMap<String, Patient>();
        doctors = new TreeMap<String, Doctor>();
        consoleIO = new ConsoleIO();
        // get the ward information
        consoleIO.outputString("Getting Ward information...");
        String name = consoleIO.readString("Enter the name of the Ward: ");
        int firstBedNum = consoleIO.readInt("Enter the integer label of the first bed: ");
        int lastBedNum = consoleIO.readInt("Enter the integer label of the last bed: ");
        ward = new Ward(name, firstBedNum, lastBedNum);
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        consoleIO.outputString("Adding Patient to Ward...");
        String name = consoleIO.readString("Enter the name of the patient: ");
        String healthNum = consoleIO.readString("Enter the health number of the patient as string: ");
        if (patients.containsKey(healthNum))
        {
            throw new  IllegalStateException("Patient with the health number " + healthNum + " already exsists");
        }

        Patient p = new Patient(name, healthNum);
        Patient result = patients.put(healthNum, p);

        // checking to make sure the the key was unique
        if (result != null)
        {
            patients.put(healthNum, result);  // put the original patient back
            throw new IllegalStateException("Health number in the patient dictionary even "
                    + "though containsKey failed.  Number " + healthNum + " not entered.");
        }
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors.
     */
    public void addDoctor()
    {
        consoleIO.outputString("Adding Doctor to Ward...");
        String name = consoleIO.readString("Enter the name of the doctor: ");
        if (doctors.containsKey(name))
            throw new IllegalStateException("Doctor not added as there already "
                    + "is a doctor with the name " + name);

        String response = consoleIO.readString("Is the doctor a surgeon? (yes or no)");
        Doctor d;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            d = new Surgeon(name);
        else
            d = new Doctor(name);

        // check to make sure the doctor name doesn't already exist
        Doctor sameNumberDoctor = doctors.put(name, d);
        if (sameNumberDoctor != null)
        {
            doctors.put(name, sameNumberDoctor); // put the original doctor back
            throw new IllegalStateException("Name in the doctor dictionary even though "
                    + "containsKey failed.  Name "  + name + " not entered.");
        }
    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        consoleIO.outputString("Assigning a new Doctor-Patient Association...");
        String healthNumber = consoleIO.readString("Enter the health number of the patient: ");

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        consoleIO.outputString("Getting Doctor information...");
        String name = consoleIO.readString("Enter the name of the doctor: ");
        Doctor d = doctors.get(name);
        if (d == null)
            throw new NoSuchElementException("There is no doctor with name " + name);
        else
        {
            p.addDoctor(d);
            d.addPatient(p);
        }
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        consoleIO.outputString("Assigning a Patient to a Bed...");
        String healthNumber = consoleIO.readString("Enter the health number of the patient: ");

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new IllegalStateException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        int bedNum = consoleIO.readInt("Enter the bed number for the patient: ");
        if (bedNum < ward.getMinBedLabel() || bedNum > ward.getMaxBedLabel())
            throw new IllegalArgumentException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + ward.getMinBedLabel()
                    + " and " + ward.getMaxBedLabel());

        p.setBedLabel(bedNum);
        ward.assignPatientToBed(p, bedNum);
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {
        consoleIO.outputString("Dropping a new Doctor-Patient Association...");
        consoleIO.outputString("Getting Patient information...");
        String healthNumber = consoleIO.readString("Enter the health number of the patient: ");

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        consoleIO.outputString("Getting Doctor information...");
        String name = consoleIO.readString("Enter the name of the doctor: ");

        Doctor d = doctors.get(name);
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
     * Displays the system state
     */
    public void systemState()
    {
        consoleIO.outputString(this.toString());
    }

    /**
     * Return a string representation of the HospitalSystem
     * @return a string representation of the HospitalSystem
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = patients.values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = doctors.values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + ward;
        return result;
    }

    /**
     * Display the list of empty beds for the ward.
     */
    public void displayEmptyBeds()
    {
        LinkedList<Integer> dispBeds = new LinkedList<>(ward.availableBeds());
        consoleIO.outputString(dispBeds+"");
    }


    /**
     * Release a patient from the ward.
     */
    public void releasePatient()
    {
        consoleIO.outputString("Releasing a Patient from a Bed...");
        String hlthNumber = consoleIO.readString("Enter health number of the patient: ");

        Patient p = patients.get(hlthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + hlthNumber);

        if (p.getBedLabel() == -1)
            throw new IllegalStateException(" Patient " + p
                    + " has no bed assigned.");

        ward.freeBed(p.getBedLabel());
        p.setBedLabel(-1);

    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        ConsoleIO usrIO=new ConsoleIO();
        int task = -1;
        String[] menu={"1: quit"
                ,"2: add a new patient"
                , "3: add a new doctor"
                , "4: assign a doctor to a patient"
                , "5: display the empty beds of the ward"
                , "6: assign a patient a bed"
                , "7: release a patient"
                , "8: drop doctor-patient association"
                , "9: display current system state"};
        HospitalSystemA5Q2 sys;

        usrIO.outputString("Initializing the system...");
        
        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new HospitalSystemA5Q2();
                break;
            }
            catch (RuntimeException e) {
                usrIO.outputString(e.getMessage());
            }
        }

        while(task != 1) {
            try
            {
                int entered = usrIO.readChoice(menu);

                task = entered;


                if      (task == 1) sys.systemState();
                else if (task == 2) sys.addPatient();
                else if (task == 3) sys.addDoctor();
                else if (task == 4) sys.assignDoctorToPatient();
                else if (task == 5) sys.displayEmptyBeds();
                else if (task == 6) sys.assignBed();
                else if (task == 7) sys.releasePatient();
                else if (task == 8) sys.dropAssociation();
                else if (task == 9) sys.systemState();
                else usrIO.outputString("Invalid option, try again.");
            } 
            catch (RuntimeException e) {
                // No matter what  exception is thrown, this catches it
                // Dealing with it means discarding whatever went wrong 
                // and starting the loop over.  Easy for the programmer,
                // tedious for the user.
                usrIO.outputString(e.getMessage());
            }
        }
    }
}