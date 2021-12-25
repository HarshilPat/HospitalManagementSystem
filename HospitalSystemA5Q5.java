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
 * Description: Implementing command pattern.
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
public class HospitalSystemA5Q5
{
    private InputOutputInterface dialogBox;

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
     * relies on user-input to get the relevant information
     */
    public HospitalSystemA5Q5() {

        patients = PatientMapAccess.getInstance();
        doctors = DoctorMapAccess.getInstance();
        dialogBox =IOAccess.getInstance();
        // get the ward information
        dialogBox.outputString("Getting Ward information...");
        String name = dialogBox.readString("Enter the name of the Ward: ");
        int firstBedNum = dialogBox.readInt("Enter the integer label of the first bed: ");
        int lastBedNum = dialogBox.readInt("Enter the integer label of the last bed: ");
        WardAccess.initialize(name,firstBedNum,lastBedNum);
        ward=WardAccess.getInstance();
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients using command pattern.
     */
    public void addPatient()
    {
        Command cmd = new AddPatient();
        cmd.execute();
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors using command pattern.
     */
    public void addDoctor()
    {
        Command cmd = new AddDoctor();
        cmd.execute();
    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        Command cmd = new AssignDoctorToPatient();
        cmd.execute();
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        Command cmd = new AssignBed();
        cmd.execute();
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {
        Command cmd = new DropAssociation();
        cmd.execute();
    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
        Command cmd = new SystemState();
        cmd.execute();
    }

    /**
     * Return a string representation of the HospitalSystem
     * @return a string representation of the HospitalSystem
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = PatientMapAccess.getInstance().values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = DoctorMapAccess.getInstance().values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + WardAccess.getInstance();
        return result;
    }

    /**
     * Display the list of empty beds for the ward.
     */
    public void displayEmptyBeds()
    {
        Command cmd = new DisplayEmptyBeds();
        cmd.execute();
    }


    /**
     * Release a patient from the ward.
     */
    public void releasePatient()
    {
        Command cmd = new ReleasePatient();
        cmd.execute();
    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        InputOutputInterface usrIO= IOAccess.getInstance();
        int task = -1;
        String[] menu={"Choose from the following","1: quit"
                ,"2: add a new patient"
                , "3: add a new doctor"
                , "4: assign a doctor to a patient"
                , "5: display the empty beds of the ward"
                , "6: assign a patient a bed"
                , "7: release a patient"
                , "8: drop doctor-patient association"
                , "9: display current system state"};
        HospitalSystemA5Q5 sys;

        usrIO.outputString("Initializing the system...");
        
        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new HospitalSystemA5Q5();
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