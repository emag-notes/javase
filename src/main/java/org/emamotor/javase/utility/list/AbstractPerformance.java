package org.emamotor.javase.utility.list;

/**
 * @author emag
 */
abstract public class AbstractPerformance {

    private long _startTime = 0L;

    abstract protected void targetOperation();

    public void exec() {
        start();
        targetOperation();
        end();
    };

    private void start() {
        _startTime = System.currentTimeMillis();
    }

    private void end() {
        System.out.printf("%5d ms\n", (System.currentTimeMillis() - _startTime));
    }

}
