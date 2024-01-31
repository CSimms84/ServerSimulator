import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebServerSimulator {
    public static void main(String[] args) {
        final int maxRequests = 10000;
        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < maxRequests; i++) {
            executor.submit(() -> {
                try {
                    System.out.println("Simulating request from thread: " + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
