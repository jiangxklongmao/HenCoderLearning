import communication.ThreadInteractionDemo;
import communication.WaitDemo;
import multithreading.Synchronized1Demo;
import multithreading.Synchronized2Demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
//        thread();
//        runnable();
//        threadFactory();
//        executor();
//        callable();
//        synchronized1Demo();
//        synchronized2Demo();
//        synchronized3Demo();
//        communication();
        waitDemo();
    }


    static void thread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("current Thread : " + Thread.currentThread().getName());
                System.out.println("Thread started!");
            }
        };
        thread.start();
    }

    static void runnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("current Thread : " + Thread.currentThread().getName());
                System.out.println("Thread with Runnable started!");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    static void threadFactory() {
        ThreadFactory factory = new ThreadFactory() {
            AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Thread-" + count.incrementAndGet());
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " started!");
            }
        };

        Thread thread = factory.newThread(runnable);
        thread.start();

        Thread thread1 = factory.newThread(runnable);
        thread1.start();

    }

    static void executor() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("current Thread : " + Thread.currentThread().getName());
                System.out.println("Executor Thread with Runnable started!");
            }
        };

        Executor executor = Executors.newCachedThreadPool();

        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);

//        Executors.newSingleThreadExecutor();
//        Executors.newFixedThreadPool(10);
//        Executors.newScheduledThreadPool(1);
    }

    static void callable() {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2000);
                System.out.println("current Thread : " + Thread.currentThread().getName());
                return "Done!";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable);

        try {
            System.out.println("before " + System.currentTimeMillis());
            String result = future.get();
            System.out.println("after " + System.currentTimeMillis());
            System.out.println("current Thread : " + Thread.currentThread().getName());
            System.out.println("Callable Thread " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("other " + System.currentTimeMillis());
    }


    static void synchronized1Demo() {
        new Synchronized1Demo().runTest();
    }

    static void synchronized2Demo() {
        new Synchronized2Demo().runTest();
    }

    static void synchronized3Demo() {
        new Synchronized2Demo().runTest();
    }

    static void communication() {
        ThreadInteractionDemo demo = new ThreadInteractionDemo();
        demo.runTest();
    }

    static void waitDemo() {
        new WaitDemo().runTest();
    }
}
