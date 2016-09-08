package gmetric4j;

import info.ganglia.gmetric4j.gmetric.GMetric;

public class MulticastExample2 {

    public static void main(String[] args) throws Exception {
        String host = "239.2.11.71";
        int port = 8649;
        int ttl = 1;
        GMetric gMetric = new GMetric(host, port, GMetric.UDPAddressingMode.MULTICAST, ttl);

        for (int i = 0; i < 1000; i++) {    // 发1000次数据
            String group = "WasServerMonitor.WasDmgrCell01.wasNode01.singleN1S1.webAppModule,branchitech-ics-websphere-ear_branchitech-metrics-websphere.war,webAppModule.servlets";
            String name = group + ".AsyncContext_responeTime.totalTime";
            System.out.println(name.length());
            gMetric.announce(name, 0, group);
            /*
            gMetric.announce("World1.hello", 1234, "");
            gMetric.announce("World2.hello", 123456789L, "");
            gMetric.announce("World3.hello", 1234.1234F, "");
            gMetric.announce("World4.hello", 123456789.123456789, "");
            */

            Thread.sleep(5000);     // 每隔5秒钟发送一次数据
        }
    }
}
