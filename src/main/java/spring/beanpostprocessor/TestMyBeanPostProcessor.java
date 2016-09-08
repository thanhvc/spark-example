package spring.beanpostprocessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyBeanPostProcessor {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "perf-metrics2.xml");
        ctx.getBean("testBean", TestBean.class);
        ctx.close();
    }
}
