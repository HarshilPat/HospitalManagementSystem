/*
 * Description: ward access class which is a singleton pattern.
 * Student Name: Harshil Patel
 * NSID: hap793
 * Student # 11290942
 * Course: CMPT270
 * Date: 11/09/20
 * */

/**
 * singleton class for ward access
 */
public class WardAccess {
    /**
     * singleton attribute
     */
    private static Ward ward;

    /**
     * private constructor
     */
    private WardAccess(){}

    /**
     * static method to initialize ward
     * @param name name of the ward
     * @param first first bed label
     * @param last last bed label
     */
    public static void initialize(String name, int first, int last) {
        if (name==null||name.equals("")||last<first||last==0){
            throw new RuntimeException("Input for name or bed label is invalid");
        }
        if(ward==null){
            ward=new Ward(name,first,last);
        }else {
            throw new RuntimeException("Constructor should be used once");
        }
    }

    /**
     * static method to get the instance of the ward.
     * @return one and only known ward to system
     */
    public static Ward getInstance(){
        if(ward==null){
            throw new RuntimeException("first call the constructor method to create ward");
        }
        return ward;
    }
}
