public class Synchronized1Demo implements TestDemo {
    private boolean running = true;

    private void stop() {
        running = false;
    }


    @Override
    public void runTest() {
        new Thread() {
            @Override
            public void run() {
                while (running) {
                    System.out.println("runTest");
                }
            }
        }.start();

        try {
            System.out.println("before");
            Thread.sleep(1000);
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop();
    }
}
