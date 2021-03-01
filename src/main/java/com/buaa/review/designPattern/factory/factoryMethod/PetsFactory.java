package com.buaa.review.designPattern.factory.factoryMethod;


import com.buaa.review.designPattern.factory.simpleFactory.Pets;

/**
 * 工厂通用接口
 */
public interface PetsFactory {
    Pets getPets();
}
