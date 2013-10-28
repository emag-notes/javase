package org.emamotor.javase.utility.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {

    public static void main(String[] args) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // calc hash value
        md.update(args[0].getBytes());
        byte[] digest = md.digest();

        // to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String tmp = Integer.toHexString(b & 0xff);
            if (tmp.length() == 1) {
                sb.append('0').append(tmp);
            } else {
                sb.append(tmp);
            }
        }
        System.out.println(sb);

    }
}