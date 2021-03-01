package com.buaa.review.java.stream;

import com.buaa.review.java.injection.model.Red;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName Sflatmap
 * @Description
 * @Author Bryce
 * @Date 2021-02-18 14:54
 */
public class Sflatmap {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {
        String[] strings = {"Hello", "World"};
//        List<Red> res = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Red red = new Red();
//            red.setId(i);
//            red.setCh("ch" + String.valueOf(i));
//            res.add(red);
//        }
//        List<LinkedHashMap<String, Object>> declare = getDeclare(res);
//        declare.forEach(System.out::println);

        Red r = new Red();
        r.setId(2);
        reflectToRead(r);
    }

    public static <T> T printValue(List<T> arr){
        for(T e : arr){
            System.out.println(e);
        }
        return arr.get(0);
    }


    public static <E> List<E> copy(E[] arr){
        List<E> list = new ArrayList<>();
        for(E e : arr){
            list.add(e);
        }
        return list;
    }

    public static <E> List<LinkedHashMap<String,Object>> getDeclare(List<E> list) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        List<LinkedHashMap<String,Object>> resList = new ArrayList<>();
        for (E e : list) {
            Field[] declaredFields = e.getClass().getDeclaredFields();
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
            for (Field field : declaredFields) {
                String name = field.getName();//成员名
                Class<?> type = field.getType();//成员类型
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, e.getClass()); //获取属性的描述符
                Method readMethod = propertyDescriptor.getReadMethod(); //获取用于读取属性值的方法
                Object o = readMethod.invoke(e); //读取属性值
                linkedHashMap.put(name, o);
            }
            resList.add(linkedHashMap);
        }
        return resList;
    }

    public static <E> void reflectToRead(E e) throws NoSuchFieldException, IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Field field = e.getClass().getDeclaredField("id");
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), e.getClass());
        Method[] method = e.getClass().getMethods();
        Method printHello = e.getClass().getMethod("printHello");
        Object invoke = printHello.invoke(e);
        System.out.println(invoke);
    }

}
