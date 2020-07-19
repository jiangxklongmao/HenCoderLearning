package multithreading;

public class Synchronized2Demo implements TestDemo {

    private volatile int count = 0;

    private synchronized  void count() {
        count++;
    }

    @Override
    public void runTest() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }
                System.out.println("final count from 1: " + count);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }
                System.out.println("final count from 2: " + count);
            }
        }.start();
    }
}
