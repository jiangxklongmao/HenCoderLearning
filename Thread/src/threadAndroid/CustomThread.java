package threadAndroid;

import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThread extends Thread {

    public Looper looper = new Looper();


    @Override
    public void run() {
        looper.loop();
    }


    class Looper {
        private Runnable task;
        private AtomicBoolean quit = new AtomicBoolean(false);

        public synchronized void setTask(Runnable task) {
            this.task = task;
        }

        void quit() {
            quit.set(true);
        }

        void loop() {
            while (!quit.get()) {
                synchronized (this) {
                    if (task != null) {
                        task.run();
                        task = null;
                    }
                }
            }
        }
    }
}
