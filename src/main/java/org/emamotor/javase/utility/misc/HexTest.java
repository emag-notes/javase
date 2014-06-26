package org.emamotor.javase.utility.misc;

/**
 * http://www.creativegear.jp/2011/05/09/java_byte_to_int/
 * @author tanabe
 */
public class HexTest {
  public static void main(String[] args) {

    System.out.println("[decimal]");

    byte b = 96;
    System.out.println(b & 0xff); // -> 96
    System.out.println((int) b & 0xff); // -> 96
    System.out.println(Integer.toHexString((int) b)); // -> 60
    System.out.println(Integer.toHexString((int) b & 0xff)); // -> 60

    System.out.println("=========================================");

    System.out.println("[hex]");
    b = (byte)0xC8; // 10進数では200のはず
    int i = (int)b;
    System.out.println(b); // -> -56

    /*
     * 2の補数
     *                        16 :                                   2 :  10
     * -----------------------------------------------------------------------
     * byte :               0xC8 :                            11001000 : -56
     * -----------------------------------------------------------------------
     * int  : 0xFFFFFFFFFFFFFFC8 : 11111111 11111111 11111111 11001000 : -56
     * -----------------------------------------------------------------------
     *
     * 先頭ビットの値で埋められたビットを 0xff でひっくり返す
     *                        16 :                                   2 :  10
     * -----------------------------------------------------------------------
     * byte :               0xC8 :                            11001000 : -56
     * -----------------------------------------------------------------------
     * int  : 0xFFFFFFFFFFFFFFC8 : 11111111 11111111 11111111 11001000 : -56
     * -----------------------------------------------------------------------
     *                      0xff : 00000000 00000000 00000000 11111111 : 255
     * -----------------------------------------------------------------------
     * AND 演算結果               : 00000000 00000000 00000000 11001000 : 200
     *
     */
    i = (int)b & 0xff;
    System.out.println(i); // -> 200

  }
}
