package metrics;

import com.codahale.metrics.MetricRegistry;

public class MetricName {

	public static void main(String[] args) {
		String name = MetricRegistry.name(MetricName.class, "request");
		System.out.println(name);
	}
}
