import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Predicate;

public class MainRunner {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(2);
        InheritableThreadLocal<String> valueInheritableThreadLocal = new InheritableThreadLocal<>();
        InheritableThreadLocal<String> valueInheritableThreadLocal2 = new InheritableThreadLocal<>();
        valueInheritableThreadLocal2.remove();
        Thread c1 = new Thread(() -> {
            valueInheritableThreadLocal.set("child1");
//            System.out.println("In thread " + Thread.currentThread().getId());
            executors.submit(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("In thread " + Thread.currentThread().getId());
                    System.out.println(valueInheritableThreadLocal.get() + " ");
                }
            });
        });
        Thread c2 = new Thread(() -> {
            valueInheritableThreadLocal.set("child2");
//            System.out.println("In thread " + Thread.currentThread().getId());
            executors.submit(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("In thread " + Thread.currentThread().getId());
                    System.out.println(valueInheritableThreadLocal.get() + " ");
                }
            });
        });
        Thread c3 = new Thread(() -> {
            valueInheritableThreadLocal.set("child3");
//            System.out.println("In thread " + Thread.currentThread().getId());
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
//                    System.out.println("In thread " + Thread.currentThread().getId());
                    System.out.println(valueInheritableThreadLocal.get() + " ");
                }
            });
        });
        c1.start();
        c2.start();
        c3.start();
        c3.join();
        c1.join();
        c2.join();
        executors.shutdown();

        boolean b = executors.awaitTermination(2, TimeUnit.SECONDS);
        List<Integer> l = new ArrayList<>();
        StringBuilder s1 = new StringBuilder("abc");
        StringBuilder s2 = s1;
        s1.append("d");
        boolean bb = s1 == s2;

    }
}
