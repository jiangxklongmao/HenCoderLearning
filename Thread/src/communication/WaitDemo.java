package communication;

import multithreading.TestDemo;

public class WaitDemo implements TestDemo {
    private String sharedString;

    private synchronized void initString() {
        sharedString = "Lengtoo";
        notifyAll();
    }

    private synchronized void printString() {
        while (sharedString == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("String: " + sharedString);
    }

    @Override
    public void runTest() {
        final Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printString();
            }
        };
        thread1.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initString();
            }
        };
        thread2.start();
    }
}
