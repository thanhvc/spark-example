package metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

import java.util.HashMap;
import java.util.Map;

public class TestMetricSet {
    public static void main(String[] args) {
        final Counter counter = new Counter();
        final Meter meter = new Meter();
        MetricSet metricSet = new MetricSet() {
            @Override
            public Map<String, Metric> getMetrics() {
                Map<String, Metric> map = new HashMap<String, Metric>();
                map.put("counter", counter);
                map.put("meter", meter);
                return map;
            }
        };
        System.out.println(metricSet.getMetrics());
    }
}
