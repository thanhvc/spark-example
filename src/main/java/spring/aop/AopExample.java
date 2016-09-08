package spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopExample {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop-example.xml");
        Performer performer = ctx.getBean("performer", Performer.class);
        performer.perform();
        Common c = (Common) ctx.getBean("common");
        c.execute("aa", "bb");
    }
}
