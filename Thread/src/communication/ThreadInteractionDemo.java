package communication;

import multithreading.TestDemo;

public class ThreadInteractionDemo implements TestDemo {

    @Override
    public void runTest() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    if (!isInterrupted()) {
                        System.out.println("1 number : " + i);
                    }
                }
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    if (!isInterrupted()) {
                        System.out.println("2 number : " + i);
                    }
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.stop();
        thread.interrupt();
        System.out.println("Alive = " + thread.isAlive());
        System.out.println("Interrupted = " + thread.isInterrupted());

    }
}
