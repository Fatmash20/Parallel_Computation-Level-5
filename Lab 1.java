public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from MyThread");
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();
        
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
/* ---------------------- */
class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello from MyTask");
    }
}

public class Main {

    public static void main(String[] args) {

        Runnable task = new MyTask();
        Thread t = new Thread(task);
        t.start();

    }
}
