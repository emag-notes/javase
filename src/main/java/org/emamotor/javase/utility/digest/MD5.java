package org.emamotor.javase.utility.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yoshimasa Tanabe
 */
public class MD5 {

    public static void main(String[] args) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for (String arg : args) {
            // calc hash value
            md.update(arg.getBytes());
            byte[] digest = md.digest();
            md.reset();

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
            System.out.println(arg + ": " + sb);
        }

    }
}
