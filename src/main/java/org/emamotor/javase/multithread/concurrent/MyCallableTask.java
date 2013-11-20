package org.emamotor.javase.multithread.concurrent;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author Yoshimasa Tanabe
 */
public class MyCallableTask implements Callable<Date> {

    @Override
    public Date call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return new Date();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Date> future = service.submit(new MyCallableTask());

        try {
            Date date = future.get();
            System.out.println(date);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
