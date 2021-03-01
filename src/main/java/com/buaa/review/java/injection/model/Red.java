package com.buaa.review.java.injection.model;

import lombok.Data;

@Data
public class Red {
    private int id;
    private String ch;
    public void printHello(){
        System.out.println("hello!");
    }
}
