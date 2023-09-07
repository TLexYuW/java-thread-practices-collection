package com.lex._03_sharing_objects._3_1;

/**
 * @author : Lex Yu
 * @date : 2023/9/7
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		new NoVisibility.ReaderThread().start();

		NoVisibility.number = 100;

		NoVisibility.ready = true;
	}
}

