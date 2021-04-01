package com.buaa.review.java.annotation;

public class Student {

    @CherryAnnotation(name = "cherry-p",age = 23,score = {99,66,77})
    public void study(int times){
        for(int i = 0; i < times; i++){
            System.out.println("Good Good Study, Day Day Up!");
        }
    }

    public static void main(String[] args) {
        Student s = new Student();
        s.study(5);
    }
}