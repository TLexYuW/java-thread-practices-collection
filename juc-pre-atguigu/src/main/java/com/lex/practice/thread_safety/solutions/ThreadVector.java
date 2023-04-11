package com.lex.practice.thread_safety.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/11
 */
public class ThreadVector {
	public static void main(String[] args) {
		List<String> strList = new Vector<>();

		for (int i = 0; i < 1000; i++) {
			new Thread(() -> {
				// add element to collection
				strList.add(UUID.randomUUID().toString().substring(0, 8));
				// get element from collection
				System.out.println(strList);
			},String.valueOf(i)).start();
		}
	}
}
