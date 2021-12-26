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
 * The model of a person who has a name and a health number
 * that cannot be changed.
 */
public class Person {

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The person's health card number.
     */
    private String healthNum;

    /**
     * Initialize an instance of a Person with the given name and health number.
     *
     * @param pName the person's name
     * @param pNumber the person's health number
     */
    public Person(String pName, String pNumber) {
        this.name = pName;
        this.healthNum = pNumber;
    }

    /**
     * Return the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the health number of the person.
     *
     * @return the health number of the person
     */
    public String getHealthNumber() {
        return this.healthNum;
    }

    /**
     * Change the name of the person.
     *
     * @param newName the name of the person
     */
    public void setName(String newName) {
        name = newName;
    }
    public static void report1(Person p1, Person p2){
        if (p1==p2){
            System.out.println("Same");
        }else{
            System.out.println("different");
        }
    }

    /**
     * Return a string representation of the person.
     *
     * @return a string representation of the person
     */
    public String toString() {
        return "\nName: " + this.name + "\nHealth card number: " + this.healthNum + "\n";
    }

    /**
     * A method to test the Person class.
     */
    public static void main(String[] args) {
        int numErrors = 0;

        // testing all the methods with one instance of a Person
        Person p = new Person("Pete", "123456");

        if(! p.getName().equals("Pete")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if(p.getHealthNumber() != "123456") {
            System.out.println("The constructor or getHealthNumber failed");
            numErrors++;
        }
        p.setName("Jim");
        if(! p.getName().equals("Jim")) {
            System.out.println("The constructor or setName failed");
            numErrors++;
        }
        String expected = "\nName: Jim\nHealth card number: 123456\n";
        if(!p.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        // testing all the methods with a second instance of a Person
        p = new Person("Mary", "987654");

        if(! p.getName().equals("Mary"))
        {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if(p.getHealthNumber() != "987654")
        {
            System.out.println("The constructor or getHealthNumber failed");
            numErrors++;
        }
        p.setName("Sue");
        if(! p.getName().equals("Sue"))
        {
            System.out.println("setName failed");
            numErrors++;
        }
        expected = "\nName: Sue\nHealth card number: 987654\n";
        if (!p.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);

        report1(new Person("Ringo","dfr"), new Person("Ringo","dsnfjk"));

        Person p1 = new Person("John","a");
        Person p2 = new Person("Paul","a");

        report1(p1, p2);
    }
}
