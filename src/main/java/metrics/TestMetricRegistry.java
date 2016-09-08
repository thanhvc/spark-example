package metrics;

import com.codahale.metrics.*;

import java.util.HashMap;
import java.util.Map;

public class TestMetricRegistry {
    public static void main(String[] args) {
        MetricSet metricSet = new MetricSet() {
            private Map<String, Metric> map = new HashMap<String, Metric>();

            {// 构造函数
                this.map.put("timer1", new Timer());
                //this.map.put("timer2", new DurationTimer());
                this.map.put("counter1", new Counter());
                this.map.put("meter1", new Meter());
            }

            @Override
            public Map<String, Metric> getMetrics() {
                return this.map;
            }
        };

        MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.register("metricSet", metricSet);

        System.out.println("遍历所有指标类：");
        for (Map.Entry<String, Metric> entry : metricRegistry.getMetrics().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getClass());
        }

        System.out.println("\n遍历所有Timer指标类：");
        for (Map.Entry<String, Timer> entry : metricRegistry.getTimers().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getClass());
        }

        /*
        MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.register("timer1", new Timer());
        metricRegistry.register("timer2", new DurationTimer());
        metricRegistry.register("counter1", new Counter());
        metricRegistry.register("meter1", new Meter());

        System.out.println("遍历所有指标类：");
        for (Map.Entry<String, Metric> entry : metricRegistry.getMetrics().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getClass());
        }

        System.out.println("\n遍历所有Timer指标类：");
        for (Map.Entry<String, Timer> entry : metricRegistry.getTimers().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getClass());
        }
        */
    }
}
