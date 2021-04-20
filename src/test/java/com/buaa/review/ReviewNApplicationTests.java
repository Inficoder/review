package com.buaa.review;

import com.buaa.review.java.annotation.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;


@SpringBootTest
class ReviewNApplicationTests {

    @Resource
    Student student;

    @Test
    void contextLoads() {
        LHashMap<Integer, Integer> map = new LHashMap<>(10);
        map.put(1, 1);
        map.put(3, 3);
        map.put(7, 7);
        System.out.println(map.head.value+ " " + map.tail.value + " " +map.size());
    }
}
class LHashMap<K, T>{
    private final int size;
    ListNode<K, T> head;
    ListNode<K, T> tail;

    LHashMap(int size){
        this.size = size;
    }
    public int size(){
        return size;
    }

    public T get(){
        if(tail == null) return null;
        return tail.value;
    }
    public void put(K key, T value){
        if(head == null){
            head = new ListNode<>(key, value);
            tail = head;
        }
        tail.next = new ListNode<>(key, value);
        tail = tail.next;
    }
}

class ListNode<K, T>{
    K key;
    T value;
    ListNode(K key, T value){
        this.key = key;
        this.value = value;
    }
    ListNode<K, T> pre;
    ListNode<K, T> next;
}