package metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.TimeUnit;

public class TestCounter {

	/**
	 * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
	 */
	private static final MetricRegistry metrics = new MetricRegistry();

	/**
	 * 在控制台上打印输出
	 */
	private static ConsoleReporter reporter = ConsoleReporter.forRegistry(
			metrics).build();

	/**
	 * 实例化一个counter,同样可以通过如下方式进行实例化再注册进去 pendingJobs = new Counter();
	 * metrics.register(MetricRegistry.name(TestCounter.class, "pending-jobs"),
	 * pendingJobs);
	 */
	private static Counter pendingJobs = metrics.counter(MetricRegistry.name(
			TestCounter.class, "pedding-jobs"));

	public static void add(String str) {
		pendingJobs.inc();
	}

	public static void main(String[] args) throws InterruptedException {
        /*
		reporter.start(3, TimeUnit.SECONDS);
		for (int i = 0; i < 20; i++) {
			add("1");
			Thread.sleep(1000);
		}
		*/
        Counter counter = new Counter();
        System.out.println("当前计数：" + counter.getCount());
        counter.inc();
        System.out.println("执行inc()后的计数：" + counter.getCount());
        counter.dec();
        System.out.println("执行dec()后的计数：" + counter.getCount());
        counter.inc(10);
        System.out.println("执行inc(10)后的计数：" + counter.getCount());
        counter.dec(5);
        System.out.println("执行dec(5)后的计数：" + counter.getCount());
    }
}