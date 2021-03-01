package com.buaa.review.designPattern.factory.factoryMethod;

import com.buaa.review.designPattern.factory.simpleFactory.Cat;
import com.buaa.review.designPattern.factory.simpleFactory.Pets;

/**
 * 每个实体类都有一个工厂类
 */
public class CatFactory implements PetsFactory{

    @Override
    public Pets getPets() {
        return new Cat();
    }

}
