package com.buaa.review.java.injection;

import org.springframework.stereotype.Component;

@Component
public class F {

    S s;

    F(S s){
        this.s = s;
    }

}
