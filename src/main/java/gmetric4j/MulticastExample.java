package gmetric4j;

import info.ganglia.gmetric4j.gmetric.GMetric;
import info.ganglia.gmetric4j.gmetric.GMetricSlope;
import info.ganglia.gmetric4j.gmetric.GMetricType;

public class MulticastExample {

    public static void main(String[] args) throws Exception {
        String host = "239.2.11.71";
        int port = 8649;
        int ttl = 1;
        GMetric gMetric = new GMetric(host, port, GMetric.UDPAddressingMode.MULTICAST, ttl);

        for (int i = 0; i < 1000; i++) {    // 发1000次数据
            gMetric.announce("int_value", 1234, "int_value_group");
            gMetric.announce("long_value", 123456789L, "long_value_group");
            gMetric.announce("float_value", 1234.1234F, "float_value_group");
            gMetric.announce("double_value", 123456789.123456789, "double_value_group");

            // 要发送字符串型数据，则需要在调用方法中设置更多的参数
            int tmax = 60, dmax = 600;
            gMetric.announce("string_value", "helloWorld",
                    GMetricType.STRING, "string_value_units",
                    GMetricSlope.BOTH, tmax, dmax, "string_value_group");

            // 对于数值型数据，也可以调用参数比较多的方法
            gMetric.announce("double_value_in_detail", String.valueOf(54321.54321),
                    GMetricType.DOUBLE, "double_value_in_detail_units",
                    GMetricSlope.BOTH, tmax, dmax, "double_value_in_detail_group");

            Thread.sleep(5000);     // 每隔5秒钟发送一次数据
        }
    }
}
