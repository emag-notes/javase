package org.emamotor.javase.ref;

/**
 * @author Yoshimasa Tanabe
 */
public class MyClass implements MyInterface {
    @Override
    public String sayHello(String name) {
        System.out.println("hello! " + name);
        return "hello!";
    }
}
