package org.emamotor.javase.utility.misc;

/**
 * @author tanabe
 */
public class PathChecker {

    public static void main(String[] args) {
        System.out.println(getUserDir());
    }

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

}
