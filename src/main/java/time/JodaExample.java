package time;

import org.joda.time.DateTime;

public class JodaExample {
    public static void main(String[] args) {
        //DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0);
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.dayOfWeek().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime.dayOfWeek().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss"));
        dateTime.toDate();

        System.out.println(new DateTime("2012-02-28T12:21:32").toString("yyyy-MM-dd HH:mm:ss"));
    }
}
