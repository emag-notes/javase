package org.emamotor.javase.utility.misc;

import java.util.UUID;

/**
 * @author tanabe
 */
public class UUIDTester {

    public static void main(String[] args) {
        UUID id = UUID.randomUUID();
        System.out.println(id.toString());
    }
}
