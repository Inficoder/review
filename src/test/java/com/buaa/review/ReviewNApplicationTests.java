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
        String s = "fff";
        System.out.println(Integer.parseInt(s, 16));
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

