package com.buaa.review.designPattern.factory.simpleFactory;

public class Cat implements Pets{

    public void talk() {
        System.out.println("miao miao!");
    }

    public void run() {
        System.out.println("cat running");
    }
}