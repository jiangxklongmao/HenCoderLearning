package threadAndroid;

public class Main {
    public static void main(String[] args) {
        CustomThread thread = new CustomThread();
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.looper.setTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("jiangxk");
            }
        });
        thread.looper.quit();
    }
}
