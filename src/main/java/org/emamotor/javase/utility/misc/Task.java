package org.emamotor.javase.utility.misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author tanabe
 */
public class Task implements Callable<String> {

  private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd");

  Date date;
  String task = "";
  String expected = "";

  public Task(Date date, String task) {
    this.date = date;
    this.expected = FORMAT.format(date);
    this.task = task;
  }

  @Override
  public String call() throws Exception {

    long counter = 0;
    Calendar limit = Calendar.getInstance();
    Calendar now = Calendar.getInstance();

    limit.add(Calendar.MILLISECOND, 5000);

    try {
      while (now.before(limit)) {

        //インスタンス生成時のFormat結果と異なる場合、出力する。
        String actual = FORMAT.format(this.date);
        if (!actual.equals(expected)) {
          System.out.println(task + " -> actual:" + actual + " is not equals expected:" + expected);
        }
        now.setTime(new Date());
        counter++;
      }
    } catch (Exception e) {
      System.out.println(task + " -> Exception");
      e.printStackTrace();
    }
    System.out.println(task + " -> execute " + counter);
    return null;
  }
}

