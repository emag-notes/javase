package org.emamotor.javase.utility.digest;

import java.io.*;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yoshimasa Tanabe
 */
public class FileMD5 {

    public static void main(String[] args) {

        try {
            System.out.println("■ファイル data.txt の内容");
            MessageDigest md = MessageDigest.getInstance("MD5");
            URL resource = new URL(FileMD5.class.getResource("."),"data.txt");
            DigestInputStream input = new DigestInputStream(resource.openConnection().getInputStream(), md);
            InputStreamReader ir = new InputStreamReader(input, "UTF-8");
            BufferedReader readBuffer = new BufferedReader(ir);
            System.out.println("■ファイルの中身");
            String line;
            while ((line = readBuffer.readLine()) != null) {
                System.out.println(line);
            }
            byte[] digest = md.digest();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                String tmp = Integer.toHexString(digest[i] & 0xff);
                if (tmp.length() == 1) {
                    buffer.append('0').append(tmp);
                } else {
                    buffer.append(tmp);
                }
            }
            System.out.println("■ファイルの中身のハッシュ値");
            System.out.println("[0x" + buffer + "]");
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("入力された名前のファイルが存在しません。");
        } catch (IOException e) {
            System.err.println("ファイルの入出力エラーが発生しました。");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("指定したハッシュ値計算アルゴリズムは存在しません。");
        }
    }

}
