package _20.mallDemo;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : Lex Yu
 */
public class CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(
            new NetMall("A-Online-Shop"),
            new NetMall("B-Online-Shop"),
            new NetMall("C-Online-Shop")
    );

    public static List<String> getPrice(List<NetMall> list, String productName) {

        return list.stream().map(netMall -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        System.out.println(ThreadLocalRandom.current().nextDouble() * 2 + "database".charAt(0));

        long start = System.currentTimeMillis();

        List<String> stringList = getPrice(list, "Special-Novel");
        for (String s : stringList) {
            System.out.println(s);
        }

        long end = System.currentTimeMillis();
        System.out.println("Execution Time: " + (end - start) + " ms");
    }
}


@Getter
class NetMall {

    private String netMallName;

    public NetMall() {
    }

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
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
