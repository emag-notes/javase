package org.emamotor.javase.io;

import java.io.File;

/**
 * @author tanabe
 */
public class DirCreater {

    public static void main(String[] args) {

        File resultDir = new File("/tmp/jms-test/result");

        boolean result = false;
        if (!resultDir.exists()) {
            result = resultDir.mkdirs();
        }
        System.out.println(result);

    }

}
