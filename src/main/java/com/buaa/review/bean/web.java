package com.buaa.review.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName web
 * @Description
 * @Author Bryce
 * @Date 2021-03-16 10:57
 */
@Controller
@RequestMapping("/")
public class web {

    @Autowired
    ActiveTools active;

    @RequestMapping("/")
    @ResponseBody
    public String web(){
        System.out.println(active.a);
        return "hello";
    }
}
