package com.buaa.review.java.annotation;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

@Component
//@CherryAnnotation(name = "cherry-p",age = 23,score = {99,66,77})
public class Student {

    @CherryAnnotation(name = "cherry-p",age = 23,score = {99,66,77})
    public void study(HttpServletRequest request){
        System.out.println("Good Good Study, Day Day Up!");
    }

}

class CherryParser {
    public void parse(Class cls) throws NoSuchMethodException {
        /**
         * 获取 Cherry的注解对象
         */
        CherryAnnotation study = cls.getMethod("study").getAnnotation(CherryAnnotation.class);
        System.out.println(study.name() + "---" );
        Annotation annotation = cls.getAnnotation(CherryAnnotation.class);
        CherryAnnotation cherry = (CherryAnnotation) annotation;
        /**
         * 检查是否有Check注解，并且注解的value属性为true
         */
        if (cherry != null && cherry.name().equals("cherry-p")) {
            System.out.println(cls.getName()+" 在检查中........");
            System.out.println(cherry.name() + " " + cherry.age() + " " + cherry.score().length);
        }
    }
}