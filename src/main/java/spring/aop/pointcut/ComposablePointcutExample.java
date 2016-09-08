package spring.aop.pointcut;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.ComposablePointcut;

import java.lang.reflect.Method;

public class ComposablePointcutExample {

    public void test() {
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = ComposablePointcutExample.class;
        Method method = clazz.getMethod("test");

        AspectJExpressionPointcut p = new AspectJExpressionPointcut();
        p.setExpression("execution(* spring.aop.pointcut.*.hello(..))");
        ComposablePointcut pointcut = new ComposablePointcut((Pointcut) p);

        System.out.println(p.getMethodMatcher().matches(method, clazz));
        System.out.println(pointcut.getMethodMatcher().matches(method, clazz));
        System.out.println(AopUtils.canApply(pointcut, ComposablePointcutExample.class));
        System.out.println();

        p = new AspectJExpressionPointcut();
        p.setExpression("execution(* spring.aop.pointcut.*.test(..))");
        pointcut.union((Pointcut) p);

        System.out.println(p.getMethodMatcher().matches(method, clazz));
        System.out.println(pointcut.getMethodMatcher().matches(method, clazz));
        System.out.println(AopUtils.canApply(pointcut, ComposablePointcutExample.class));
    }
}
