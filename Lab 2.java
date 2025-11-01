public class Example1 {
    public static void main(String[] args) {

        Thread t1 = new Thread("MyFirstThread");
        t1.start();

        System.out.println("Thread Name: " + t1.getName());
    }
}
/*_______________________ */
public class Example3 {

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println("Running in: " + Thread.currentThread().getName());
        };

        Thread t3 = new Thread(task, "RunnableThread");

        t3.start();
    }
}
/*_______________________ */
public class Example2 {

    public static void main(String[] args) {

        Thread t2 = new Thread();

        t2.setName("WorkerThread-1");

        t2.start();

        System.out.println("Thread Name: " + t2.getName());
    }
}
/*_______________________ */
public class Example1 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 Priority: " + Thread.currentThread().getPriority());
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 Priority: " + Thread.currentThread().getPriority());
        });

        t1.setPriority(Thread.MIN_PRIORITY);   // 1
        t2.setPriority(Thread.MAX_PRIORITY);   // 10

        t1.start();
        t2.start();
    }
}
/*_______________________ */
public class ThreadStateDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("State inside run(): " + Thread.currentThread().getState());
        });

        // State 1: NEW (thread created but not started)
        System.out.println("Before start(): " + t1.getState());

        // Start the thread → RUNNABLE
        t1.start();

        // State after calling start()
        System.out.println("After start(): " + t1.getState());

        // Wait until thread finishes
        t1.join();

        // State after completion → TERMINATED
        System.out.println("After completion(): " + t1.getState());
    }
}
/*_______________________ */
public class ThreadGroupExample {
    public static void main(String[] args) {
        // Create a new ThreadGroup
        ThreadGroup group = new ThreadGroup("MyGroup");

        // Create threads inside the group
        Thread t1 = new Thread(group, () -> {
            System.out.println(Thread.currentThread().getName() + " in " +
                    Thread.currentThread().getThreadGroup().getName());
        }, "Thread-1");
        
        Thread t2 = new Thread(group, () -> {
            System.out.println(Thread.currentThread().getName() + " in " +
                    Thread.currentThread().getThreadGroup().getName());
        }, "Thread-2");
        
        t1.start();
        t2.start();
    }
}
/*_______________________ */
public class ListThreadsInGroup {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("Workers");
        
        Thread t1 = new Thread(group, () -> System.out.println("Running: " + Thread.currentThread().getName()), "Worker-1");
        
        Thread t2 = new Thread(group, () -> System.out.println("Running: " + Thread.currentThread().getName()), "Worker-2");
        
        t1.start();
        t2.start();

        Thread.sleep(100); // wait for them to start

        System.out.println("Active Threads in Group: " + group.activeCount());
        group.list(); // prints details of threads in group
    }
}
/*_______________________ */
public class UserThreadExample {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("Inside user thread: " + Thread.currentThread().getName());
        }, "UserThread-1");
        t.start();
        System.out.println("Main thread ends: " + Thread.currentThread().getName());
    }
}
/*_______________________ */
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Daemon thread running: " + Thread.currentThread().getName());
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Daemon interrupted");
            }
        }, "MyDaemonThread");

        daemon.setDaemon(true); // mark as daemon
        daemon.start();

        System.out.println("Main thread ends: " + Thread.currentThread().getName());
    }
}