package org.emamotor.javase.ref;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Yoshimasa Tanabe
 */
public class MyInvocationHandlerClient {

    public static void main(String[] args) {
        MyInterface myInterfaceReal = new MyClass();

        InvocationHandler handler = new MyInvocationHandler(myInterfaceReal);

        MyInterface myInterface = (MyInterface) Proxy.newProxyInstance(
                MyClass.class.getClassLoader(),
                MyClass.class.getInterfaces(),
                handler
        );

        String ret = myInterface.sayHello("JAVA");

        System.out.println(ret);
    }

}
