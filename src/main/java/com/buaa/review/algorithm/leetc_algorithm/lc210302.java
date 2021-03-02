package com.buaa.review.algorithm.leetc_algorithm;

import org.apache.tomcat.jni.Time;

public class lc210302 {
/**
 * 459. 重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 * 输入: "abab"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 */
    public static boolean repeatedSubstringPattern(String s) {
        // 超过时间限制
        for (int i = 1; i < s.length()/2; i++) {
            String replace = s.replace(s.substring(0, i), "");
//            String[] split = s.split(s.substring(0, i));
            if(replace.length() == 0){
                return true;
            }
        }
        return false;
    }


    public static boolean repeatedSubstringPattern_new(String s) {
        //一行
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }


    public static void main(String[] args) {
        String a = "qweqwe";
        boolean b = repeatedSubstringPattern_new(a);
        System.out.println(b);
    }
}
