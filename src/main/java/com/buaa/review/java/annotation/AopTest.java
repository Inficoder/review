package com.buaa.review.java.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class AopTest {

    @Pointcut(value = "@annotation(com.buaa.review.java.annotation.CherryAnnotation)")
    public void getAnnotation(){

    }

    @Before("getAnnotation()")
    public void before(){
        System.out.println("cherry 1");
    }

    @Around("@annotation(cherry)")
    public Object  around(ProceedingJoinPoint joinPoint, CherryAnnotation cherry) throws Throwable {
        System.out.println(cherry.name());
        System.out.println(cherry.age());
        System.out.println(cherry.score().length);
        Object target = joinPoint.getTarget().getClass().getName();
        System.out.println("调用者+"+target);
        //通过joinPoint.getArgs()获取Args参数
        Object[] args = joinPoint.getArgs();//2.传参  
        System.out.println("2.传参:----"+args[0]);
        for (Object object : args) {
            if(object instanceof HttpServletRequest){
                Method method = object.getClass().getMethod("getHeader", String.class);
                Object werity = method.invoke(object, "Accept-Language");
                System.out.println(werity.toString());
                System.out.println(method.getName());
            }
        }
        return joinPoint.proceed();

    }

}
