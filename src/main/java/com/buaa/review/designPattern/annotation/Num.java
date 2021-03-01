package com.buaa.review.designPattern.annotation;

import org.springframework.stereotype.Component;

@Component
public class Num {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    @Init(value = "t")
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    @Init(value = "23")
    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Num{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
