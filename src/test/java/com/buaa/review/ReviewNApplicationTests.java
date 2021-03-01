package com.buaa.review;

import com.buaa.review.java.injection.Apple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Optional;

@SpringBootTest
class ReviewNApplicationTests {

//    @Resource
    Apple apple;


//    @Autowired
//    ReviewNApplicationTests(Apple apple){
//        this.apple = apple;
//    }

    @Autowired
    public void setApple(Apple apple) {
        this.apple = apple;
    }

    @Test
    void contextLoads() throws Exception {
        Object java8Tester = new Object();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        System.out.println(a.isPresent());
    }

    public Object a(){
        System.out.println(111);
        return 1;
    }

    public Object b(){
        System.out.println(2);
        return 2;
    }

    @Test
    public void SerializableTest() throws IOException, ClassNotFoundException {
        Reader fileReader = new FileReader("object.txt");
        char[] ca = new char[1024];
        int len = 0;
        while((len = fileReader.read()) != -1){
            System.out.println(new String(ca,0,len));
        }
    }

}

