import java.time.LocalTime;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println(LocalTime.now() + " Task start");
            Thread.sleep(3000L);
            return "Task Result";
        };

        Future<String> future = executor.submit(callable);
        Thread.sleep(2000L);
        System.out.println(LocalTime.now() + " Waiting the task done");
        System.out.println("isDone take 1 = " + future.isDone());
        String result = future.get();
        System.out.println("isDone take 2 = " + future.isDone());
        System.out.println(LocalTime.now() + " future.get() = " + result);

        System.out.println("Hello world!");
    }
}