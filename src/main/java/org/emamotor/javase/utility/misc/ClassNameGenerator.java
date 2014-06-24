package org.emamotor.javase.utility.misc;

/**
 * @author tanabe
 */
public class ClassNameGenerator {

    private static final String className = Thread.currentThread().getStackTrace()[1].getClassName();

    public static void main(String[] args) {

        System.out.println(className);
        new Caller().call();

    }

}

class Caller{
    public void call() {
        System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}

