package org.emamotor.javase.utility.misc;

/**
 * @author tanabe
 */
public class ByteToIntTest {
  public static void main(String[] args) {
    byte b3 = (byte)0x00;
    byte b2 = (byte)0x01;
    byte b1 = (byte)0x00;
    byte b0 = (byte)0x01;


    /* b3
     * 00000000 00000000 00000000 0000000'0'
     * b3 << 24
     * 0000000'0' 00000000 00000000 00000000
     *
     * b2
     * 00000000 00000000 00000000 0000000'1'
     * b2 << 16
     * 00000000 0000000'1' 00000000 00000000
     *
     * b1
     * 00000000 00000000 00000000 0000000'0'
     * b3 << 8
     * 0000000'0' 00000000 0000000'0' 00000000
     *
     * b0
     * 00000000 00000000 00000000 0000000'1'
     * b0 << 0
     * 00000000 00000000 00000000 0000000'1'
     *
     * 0000000'0' 0000000'1' 0000000'0' 0000000'1'
     */
    int x = (int)((((b3 & 0xff) << 24) |
      ((b2 & 0xff) << 16) |
      ((b1 & 0xff) <<  8) |
      ((b0 & 0xff) <<  0)));

    System.out.println(x);

    System.out.println("=====================================");

    System.out.println((b3 & 0xff));  // 0
    System.out.println((b3 & 0xff) << 24); // 0
    System.out.println((b2 & 0xff));  // 1
    System.out.println((b2 & 0xff) << 16);  // 65536
    System.out.println(((b3 & 0xff) << 24) | ((b2 & 0xff) << 16));  // 65536
  }
}
