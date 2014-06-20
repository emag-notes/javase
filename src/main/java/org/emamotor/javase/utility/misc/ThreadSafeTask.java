package org.emamotor.javase.utility.misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author tanabe
 */
public class ThreadSafeTask implements Callable<String> {

  private final static ThreadLocal<DateFormat> FORMAT = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
      return new SimpleDateFormat();
    }
  };

  Date date;
  String task = "";
  String expected = "";

  public ThreadSafeTask(Date date, String task) {
    this.date = date;
    this.expected = FORMAT.get().format(date);
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
        String actual = FORMAT.get().format(this.date);
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

