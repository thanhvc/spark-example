package gmetric4j;

import info.ganglia.gmetric4j.gmetric.GMetric;

import java.util.Random;

public class UnicastExample {

    public static void main(String[] args) throws Exception {
        Random rnd = new Random();
        String host = "192.168.8.32";
        int port = 8649;
        int ttl = 1;
        GMetric gMetric = new GMetric(host, port, GMetric.UDPAddressingMode.UNICAST, ttl);

        while (true) {
            gMetric.announce("gmetric4j_test", rnd.nextInt(), "gmetrics4j_test_group");
            Thread.sleep(5000);
        }
    }
}
