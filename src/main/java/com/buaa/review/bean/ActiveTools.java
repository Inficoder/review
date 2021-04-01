package com.buaa.review.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ActiveTools {

    int a;

    @Bean(name = "active")
    public ActiveTools initActive(){
        ActiveTools activeTools = new ActiveTools();
        activeTools.a = 100;
        return activeTools;
    }

}
