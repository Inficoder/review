package com.buaa.review.java.injection;

import org.springframework.stereotype.Component;

@Component
public class Apple {

    private int sweet;

    Apple(){ }

    public int getSweet() {
        return sweet;
    }

    public void setSweet(int sweet) {
        this.sweet = sweet;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "sweet=" + sweet +
                '}';
    }
}
