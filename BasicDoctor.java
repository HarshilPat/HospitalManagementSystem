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
     Model solution File for Assignment 4
 */
 

/**
 * A very simple model of a doctor who has a name.
 */
public class BasicDoctor {

    /**
     * The name of the doctor.
     */
    private String name;

    /**
     * Initialize an instance of BasicDoctor with the given name.
     *
     * @param name the name of the doctor
     */
    public BasicDoctor(String name)
    {
        this.name = name;
    }

    /**
     * Return  the name of the doctor
     *
     * @return the name of the doctor
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Change the name of the doctor.
     *
     * @param newName the name of the doctor
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Return a string representation of the doctor
     *
     * @return a string representation of the doctor
     */
    public String toString()
    {
        return "\nName: " + this.name + "\n";
    }

    /**
     * A method to test the BasicDoctor class.
     */
    public static void main(String[] args) {
        int numErrors = 0;

        // testing all the methods with one instance of a BasicDoctor
        BasicDoctor d = new BasicDoctor("Joe");
        if(! d.getName().equals("Joe")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        d.setName("Dr. Joe");
        if(! d.getName().equals("Dr. Joe")) {
            System.out.println("The constructor or setName failed");
            numErrors++;
        }
        String expected = "\nName: Dr. Joe\n";
        if(!d.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        // testing all the methods with a second instance of a BasicDoctor
        d = new BasicDoctor("Mary");
        if(! d.getName().equals("Mary")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        d.setName("Dr. Mary");
        if(! d.getName().equals("Dr. Mary")) {
            System.out.println("The constructor or setName failed");
            numErrors++;
        }
        expected = "\nName: Dr. Mary\n";
        if(!d.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
