package com.buaa.review.designPattern.factory.simpleFactory;

/**
 * 简单工厂模式  //todo 抽象工厂没有实现
 */
public class SimpleFactory {

    public Pets getPets(String pets){
        if("dog".equals(pets)){
            return new Dog();
        }else if("cat".equals(pets)){
            return new Cat();
        }
        return null;
    }

}
