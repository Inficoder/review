package com.buaa.review.java.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@RestController
public class Web {

    @Resource
    Student student;

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        student.study(request);
        return "test";
    }
}
