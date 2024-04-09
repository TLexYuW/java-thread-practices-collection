package _19.mallDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author : Lex Yu
 */
public class CompletableFutureMallDemo {
	public static void main(String[] args) {

	}
}


@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student{
	private Integer id;
	private String name;
	private String major;
}
