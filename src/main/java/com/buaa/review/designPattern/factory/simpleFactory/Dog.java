package com.buaa.review.designPattern.factory.simpleFactory;

public class Dog implements Pets{

    public void talk() {
        System.out.println("wang wang!");
    }

    public void run() {
        System.out.println("dog running");
    }
}
