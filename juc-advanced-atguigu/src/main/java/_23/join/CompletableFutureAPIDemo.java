package _23.join;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : Lex Yu
 */
public class CompletableFutureAPIDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			return "some-value";
		});

		System.out.println(future.join());
	}
}
