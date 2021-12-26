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
public class HospitalSystemA5Q6
{
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
    public HospitalSystemA5Q6() {

        patients = PatientMapAccess.getInstance();
        doctors = DoctorMapAccess.getInstance();
        // get the ward information
        IOAccess.getInstance().outputString("Getting Ward information...");
        String name = IOAccess.getInstance().readString("Enter the name of the Ward: ");
        int firstBedNum = IOAccess.getInstance().readInt("Enter the integer label of the first bed: ");
        int lastBedNum = IOAccess.getInstance().readInt("Enter the integer label of the last bed: ");
        WardAccess.initialize(name,firstBedNum,lastBedNum);
        ward=WardAccess.getInstance();
    }


    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        InputOutputInterface usrIO= IOAccess.getInstance();
        int task = -1;

        Command[] commands = new Command[9];
        commands[0]=new SystemState();
        commands[1]=new AddPatient();
        commands[2]=new AddDoctor();
        commands[3]=new AssignDoctorToPatient();
        commands[4]=new DisplayEmptyBeds();
        commands[5]=new AssignBed();
        commands[6]=new ReleasePatient();
        commands[7]=new DropAssociation();
        commands[8]=new SystemState();

        String[] menu={"Choose from the following","1: quit"
                ,"2: add a new patient"
                , "3: add a new doctor"
                , "4: assign a doctor to a patient"
                , "5: display the empty beds of the ward"
                , "6: assign a patient a bed"
                , "7: release a patient"
                , "8: drop doctor-patient association"
                , "9: display current system state"};
        HospitalSystemA5Q6 sys;

        usrIO.outputString("Initializing the system...");

        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new HospitalSystemA5Q6();
                break;
            }
            catch (RuntimeException e) {
                usrIO.outputString(e.getMessage());
            }
        }

        while(task != 0) {
            try
            {
                int entered = usrIO.readChoice(menu);

                task = entered-1;
                commands[task].execute();

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