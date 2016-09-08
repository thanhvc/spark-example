package metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Snapshot;

import java.util.concurrent.TimeUnit;

public class TestHistograms {

	private static final MetricRegistry metrics = new MetricRegistry();
	private static ConsoleReporter reporter = ConsoleReporter.forRegistry(
			metrics).build();

	private static Histogram his = metrics.histogram(MetricRegistry.name(
			TestHistograms.class, "his"));

	public static void main(String[] args) throws InterruptedException {
		reporter.start(3, TimeUnit.SECONDS);
		for (int i = 0; i < 20; i++) {
			his.update(i);
			Thread.sleep(1000);
		}
        Snapshot sn=his.getSnapshot();
	}
}