package spring.aop.pointcut;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;

import java.lang.reflect.Method;

public class ComposablePointcutBug {
    public void test() {
    }

    public static void main(String[] args) throws Exception {
        AspectJExpressionPointcut p = new AspectJExpressionPointcut();
        p.setExpression("execution(* java.lang.Integer.*(..))");
        ComposablePointcut son = new ComposablePointcut((Pointcut) p);
        ComposablePointcut parent = new ComposablePointcut(son);

        p = new AspectJExpressionPointcut();
        p.setExpression("execution(* java.lang.String.*(..))");
        son.union((Pointcut) p);

        Method method = String.class.getMethod("toString");
        System.out.println(son.getMethodMatcher().matches(method, String.class));
        //print(AopUtils.canApply(son, String.class));
        System.out.println(parent.getMethodMatcher().matches(method, String.class));
        //print(AopUtils.canApply(parent, String.class));
    }
}
