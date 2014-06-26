package org.emamotor.javase.ref;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Yoshimasa Tanabe
 * http://ffy.afy-system.jp/tips/t_004.html
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("invode method: " + method.getName());

        Object ret = method.invoke(this.target, args);

        System.out.println("invoke result:" + ret.toString());

        return ret;

    }

}
