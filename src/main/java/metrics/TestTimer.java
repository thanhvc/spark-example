package metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;

public class TestTimer {
    private static final MetricRegistry metrics = new MetricRegistry();
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(
            metrics).build();

    private static final Timer requests = metrics.timer(MetricRegistry.name(
            TestTimer.class, "request"));

    public static void handleRequest(int sleep) {
        Timer.Context context = requests.time();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.stop();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*
        reporter.start(3, TimeUnit.SECONDS);
        for (int i = 0; i < 2000; i++) {
            handleRequest(200);
        }
        */
        Timer timer = new Timer();
        for (int i = 0; i < 100; i++) {
            Timer.Context context = timer.time(); // 开始计时
            Thread.sleep(100 + i);
            context.stop(); // 结束计时
        }
        System.out.println("我们对timer做了100次计时，每次计时的时间依次从100增加到199：");
        System.out.println("总计时次数：" + timer.getCount());
        System.out.println("计时事件触发的频率（次/秒）：" + timer.getMeanRate());
        Snapshot snapshot = timer.getSnapshot();
        System.out.println("最短一次计时经历的时间（纳秒）：" + snapshot.getMin());
        System.out.println("最长一次计时经历的时间（纳秒）：" + snapshot.getMax());
        System.out.println("历次计时经历时间的中位数（纳秒）：" + snapshot.getMedian());
        System.out.println("历次计时经历时间的75百分位数（纳秒）：" + snapshot.get75thPercentile());
        /*
        while (true) {
            Timer.Context context = timer.time();
            Thread.sleep(100);
            context.stop();
            System.out.println(timer.getOneMinuteRate());
        }
        */
    }
}