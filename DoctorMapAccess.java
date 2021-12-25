/*
 * Description: doctor map access class which is a singleton pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */

import java.util.TreeMap;

/**
 * singleton class for Doctors directory access
 */
public class DoctorMapAccess {
    /**
     * Singleton attribute
     */
    private static TreeMap<String, Doctor> dictionary = null;

    /**
     * private constructor for class
     */
    private DoctorMapAccess() {
    }

    /**
     *  method to get instance of class
     *  @return single and only known directory to system for doctors.
     */
    public static TreeMap<String, Doctor> getInstance() {
        if (dictionary == null) {
            dictionary = new TreeMap<String, Doctor>();
        }
        return dictionary;
    }
}
