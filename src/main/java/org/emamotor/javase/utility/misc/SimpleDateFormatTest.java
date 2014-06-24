package org.emamotor.javase.utility.misc;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tanabe
 */
public class SimpleDateFormatTest {

  public static void main(String[] args) {
    ExecutorService service = Executors.newCachedThreadPool();

    System.out.println("task start");

    service.submit(new Task(new Date(0), "task1"));
    service.submit(new Task(new Date(), "task2"));
//    service.submit(new ThreadSafeTask(new Date(0), "task1"));
//    service.submit(new ThreadSafeTask(new Date(), "task2"));

    System.out.println("task end");
    service.shutdown();
  }
}
