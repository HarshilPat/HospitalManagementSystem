/*
 * Description: Patient map access class which is a singleton pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */

import java.util.TreeMap;

/**
 * class to create single TreeMap instance
 */
public class PatientMapAccess {
    /**
     * singleton attribute directory:TreeMap
     */
    private static TreeMap<String,Patient> dictionary=null;

    /**
     * private constructor for class
     */
    private PatientMapAccess(){
    }

    /**
     * method to get instance of class
     * @return single and only known directory to system for patients.
     */
    public static TreeMap<String,Patient> getInstance(){
        if (dictionary==null){
            dictionary=new TreeMap<String, Patient>();
        }
        return dictionary;
    }
}
