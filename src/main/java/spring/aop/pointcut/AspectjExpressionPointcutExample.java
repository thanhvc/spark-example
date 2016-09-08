package spring.aop.pointcut;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AopUtils;

public class AspectjExpressionPointcutExample {
    static AspectJExpressionPointcut pointcut;

    static void testMatches(String methodName) throws Exception {
        testMatches(methodName, TestBean.class);
    }

    static void testMatches(String methodName, Class<?> clazz) throws Exception {
        System.out.println(methodName + " " + pointcut.matches(clazz.getMethod(methodName), clazz));
    }

    static void testMethodMethodMatch(String methodName, Class<?> clazz) throws Exception {
        System.out.println(methodName + " " + pointcut.getMethodMatcher().matches(clazz.getMethod(methodName), clazz));
    }

    public static void main(String[] args) throws Exception {
        pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* spring.aop.pointcut.*.sayHello(..))");
        testMatches("sayHello");
        testMatches("sayNo");
        testMatches("length", StringBuilder.class);
        testMethodMethodMatch("sayNo", TestBean.class);
        testMethodMethodMatch("sayHello", TestBean.class);

        System.out.println();

        pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* spring.aop.pointcut.*.sayN*(..))");
        testMatches("sayHello");
        testMatches("sayNo");
        testMatches("length", StringBuilder.class);
        testMethodMethodMatch("sayNo", TestBean.class);
        testMethodMethodMatch("sayHello", TestBean.class);

        System.out.println();
        System.out.println(AopUtils.canApply(pointcut, String.class));
        System.out.println(AopUtils.canApply(pointcut, TestBean.class));
    }
}
