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
 * Description: Implemented task 5 and 7 which were left as stub in asn4.
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
public class HospitalSystemA5Q1
{
    /** 
    One Scanner for all methods 
    */
    private static Scanner consoleIn = new Scanner(System.in);

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
    public HospitalSystemA5Q1() {

        patients = new TreeMap<String, Patient>();
        doctors = new TreeMap<String, Doctor>();

        // get the ward information
        System.out.println("Getting Ward information...");
        System.out.print("Enter the name of the Ward: ");
        String name = consoleIn.nextLine();
        System.out.println("Entered: " + name);
        System.out.print("Enter the integer label of the first bed: ");
        int firstBedNum = consoleIn.nextInt();
        System.out.println("Entered: " + firstBedNum);
        consoleIn.nextLine();

        System.out.print("Enter the integer label of the last bed: ");
        int lastBedNum = consoleIn.nextInt();
        System.out.println("Entered: " + lastBedNum);
        consoleIn.nextLine();

        ward = new Ward(name, firstBedNum, lastBedNum);
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        System.out.println("Adding Patient to Ward...");
        System.out.print("Enter the name of the patient: ");
        String name = consoleIn.nextLine();
        System.out.println("Entered: " + name);

        System.out.print("Enter the health number of the patient: ");
        String healthNum = consoleIn.next();
        System.out.println("Entered: " + healthNum);
        consoleIn.nextLine();  // discard the remainder of the line
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
        System.out.println("Adding Doctor to Ward...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
        System.out.println("Entered: " + name);
        if (doctors.containsKey(name))
            throw new IllegalStateException("Doctor not added as there already "
                    + "is a doctor with the name " + name);

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = consoleIn.nextLine();
        System.out.println("Entered: " + response);
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
        System.out.println("Assigning a new Doctor-Patient Association...");
        System.out.print("Enter the health number of the patient: ");
        String healthNumber = consoleIn.next();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
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
        System.out.println("Assigning a Patient to a Bed...");
        System.out.print("Enter the health number of the patient: ");
        String healthNumber = consoleIn.next();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new IllegalStateException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        System.out.print("Enter the bed number for the patient: ");
        int bedNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
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
        System.out.println("Dropping a new Doctor-Patient Association...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        String healthNumber = consoleIn.next();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new NoSuchElementException("There is no patient with health number "
                    + healthNumber);

        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();

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
        System.out.println(this.toString());
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
        System.out.println(dispBeds);
    }


    /**
     * Release a patient from the ward.
     */
    public void releasePatient()
    {
        System.out.println("Releasing a Patient from a Bed...");
        System.out.print("Enter health number of the patient: ");
        String hlthNumber = consoleIn.next();
        consoleIn.nextLine();  // discard the remainder of the line

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
        int task = -1;
        HospitalSystemA5Q1 sys;

        System.out.println("Initializing the system...");
        
        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new HospitalSystemA5Q1();
                break;
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        while(task != 1) {
            try
            {
                System.out.print("Options:"
                        + "\n\t1: quit"
                        + "\n\t2: add a new patient"
                        + "\n\t3: add a new doctor"
                        + "\n\t4: assign a doctor to a patient"
                        + "\n\t5: display the empty beds of the ward"
                        + "\n\t6: assign a patient a bed"
                        + "\n\t7: release a patient"
                        + "\n\t8: drop doctor-patient association"
                        + "\n\t9: display current system state"
                        + "\nEnter your selection {1-9}: ");

                String entered = consoleIn.next();
                
                // cute trick: any string starting with # is ignored here
                // so we can have "comments" in a sequence of commands
                // Now we can run the application by redirecting input from the a file!
                if (entered.charAt(0) == '#') continue;
                
                task = Integer.parseInt(entered);
                consoleIn.nextLine();  // consume any fluff at the end of the line


                if      (task == 1) sys.systemState();
                else if (task == 2) sys.addPatient();
                else if (task == 3) sys.addDoctor();
                else if (task == 4) sys.assignDoctorToPatient();
                else if (task == 5) sys.displayEmptyBeds();
                else if (task == 6) sys.assignBed();
                else if (task == 7) sys.releasePatient();
                else if (task == 8) sys.dropAssociation();
                else if (task == 9) sys.systemState();
                else System.out.println("Invalid option, try again.");
            } 
            catch (RuntimeException e) {
                // No matter what  exception is thrown, this catches it
                // Dealing with it means discarding whatever went wrong 
                // and starting the loop over.  Easy for the programmer,
                // tedious for the user.
                System.out.println(e.getMessage());
            }
        }

        consoleIn.close();
    }
}