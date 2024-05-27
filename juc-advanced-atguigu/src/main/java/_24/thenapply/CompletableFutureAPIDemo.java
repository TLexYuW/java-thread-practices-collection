package _24.thenapply;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : Lex Yu
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Step. 1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            return 10;
        }).thenApply(i -> {
            System.out.println("Step. 2 : " + i);
            return i * 2;
        }).thenApply(i -> {
            System.out.println("Step. 3 : " + i);
            return i * 3;
        }).whenComplete((val, err) -> {
            System.out.println("Step. 4 : " + val);
            if (err == null) {
                System.out.println("After Computed : " + val);
            }
        }).exceptionally(err -> {
            System.out.println("Step. 5");
            err.printStackTrace();
            System.out.println(err.getMessage());
            return null;
        });

        System.out.println(future.join());
    }
}
