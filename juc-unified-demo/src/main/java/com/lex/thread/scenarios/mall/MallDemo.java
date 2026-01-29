package com.lex.thread.scenarios.mall;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : Lex Yu
 */
public class MallDemo {
	static List<NetMall> list = Arrays.asList(
			new NetMall("A-Online-Shop"),
			new NetMall("B-Online-Shop"),
			new NetMall("C-Online-Shop"),
			new NetMall("D-Online-Shop"),
			new NetMall("E-Online-Shop"),
			new NetMall("F-Online-Shop"),
			new NetMall("G-Online-Shop")
	);

	public static List<String> getPrice(List<NetMall> list, String productName) {

		return list.stream().map(netMall -> String.format(productName + " in %s price is %.2f",
						netMall.getNetMallName(),
						netMall.calcPrice(productName)))
				.collect(Collectors.toList());
	}

	public static List<String> getPriceCompletableFuture(List<NetMall> list, String productName) {

		List<CompletableFuture<String>> futures = list.stream()
				.map(netMall -> CompletableFuture.supplyAsync(() ->
						String.format(productName + " in %s price is %.2f",
								netMall.getNetMallName(),
								netMall.calcPrice(productName))))
				.collect(Collectors.toList());

		return futures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> stringList = getPrice(list, "Special-Novel");
		stringList.forEach(System.out::println);

		long end = System.currentTimeMillis();
		System.out.println("Execution Time: " + (end - start) + " ms");

		start = System.currentTimeMillis();

		List<String> stringList2 = getPriceCompletableFuture(list, "Special-Novel");
		stringList2.forEach(System.out::println);

		end = System.currentTimeMillis();
		System.out.println("Execution Time: " + (end - start) + " ms");
	}
}


class NetMall {

	private String netMallName;

	public NetMall() {
	}

	public NetMall(String netMallName) {
		this.netMallName = netMallName;
	}

	public String getNetMallName() {
		return netMallName;
	}

	public double calcPrice(String productName) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
	}
}
