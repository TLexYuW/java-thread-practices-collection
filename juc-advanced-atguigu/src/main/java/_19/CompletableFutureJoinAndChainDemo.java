package _19;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 */
public class CompletableFutureJoinAndChainDemo {
	public static void main(String[] args) {
//		Student s1 = new Student();
//		s1.setId(11).setName("K").setMajor("English");

		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hi There");
//		System.out.println(stringCompletableFuture.get());

		System.out.println(stringCompletableFuture.join()); // Won't throw exception
	}
}


@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student {
	private Integer id;
	private String name;
	private String major;
}

