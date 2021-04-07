package com.buaa.review.algorithm.leetc_algorithm;

import java.util.ArrayList;
import java.util.List;

public class Lc_2021_04 {

    /**
     * 给定一个字符串 s，计算具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是连续的。
     * 重复出现的子串要计算它们出现的次数。
     *
     * 示例 1 :
     * 输入: "00110011"
     * 输出: 6
     * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
     *
     * 请注意，一些重复出现的子串要计算它们出现的次数。
     *
     * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
     * 示例 2 :
     *
     * 输入: "10101"
     * 输出: 4
     * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
     *
     * 提示：
     * s.length 在1到50,000之间。
     * s 只包含“0”或“1”字符。
     */
    //210406
    //暴力
    public static boolean isSubs(String s){
        int j = s.length() - 1;
        int i = j / 2;
        if(!s.substring(0, i+1).contains("0") && !s.substring(i+1, j+1).contains("1")){
            return true;
        }
        if(!s.substring(0, i+1).contains("1") && !s.substring(i+1, j+1).contains("0")){
            return true;
        }
        return false;
    }

    //计数 可优化 空间
    public static int countBinarySubstrings(String s) {
        // 1.将00111000字符串转为[2,3,3]
        List<Integer> list = new ArrayList<>();
        int count = 1;
        char temp = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if(temp == s.charAt(i))
                count++;
            else {
                list.add(count);
                count = 1;
                temp = s.charAt(i);
            }
            if(i == s.length() - 1)
                list.add(count);
        }
//        list.forEach(System.out::println);
        //2. 计算结果
        int res = 0;
        for (int i = 0; i < list.size() - 1; i ++) {
            res += list.get(i) >= list.get(i + 1) ? list.get(i + 1) : list.get(i);
        }
        return res;
    }


    /**
     * 我们使用一个长度为2的滑窗遍历整个字符串，
     * 如果窗口中的两个字符（s[i]与s[i+1]）不同，
     * 则以该长度为2的子串为基础向左右同步扩展，探测伸长的子串是否可以满足要求。
     * 期间，需要用变量即时统计满足要求的子串数目。
     */
    //滑动窗口
    public static int countBinarySubstrings2(String s) {
        int res = 0;
        int left, right;
        int left_idx , right_idx;
        for (int i = 0; i < s.length() - 1; i++) {
            left_idx = i; right_idx = i + 1;
            left = s.charAt(i); right = s.charAt(i + 1);
            if(left != right){
                while(left_idx >= 0 && right_idx <= s.length() - 1 && left == s.charAt(left_idx) && right == s.charAt(right_idx)){
                    res ++;
                    left_idx --;
                    right_idx ++;
                }
            }
        }
        return res;
    }


    //2021.04.07
    /**
     * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
     *
     *
     * 示例：
     *
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *
     */


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
//        minStack.list.forEach(System.out::println);
//        minStack.getMin();
//        System.out.println(minStack.getMin());
//           //--> 返回 -3.
//-2 -2 0
//        minStack.pop();
//        System.out.println(minStack.min+"--"+minStack.top());
        minStack.list.forEach(System.out::println);
////        minStack.top();      //返回 0.
//        System.out.println(minStack.top());
//        System.out.println(minStack.getMin());
//        minStack.getMin();   //--> 返回 -2.
    }
}

class MinStack {
    ArrayList<Integer> list;
    int min;
    /** initialize your data structure here. */
    public MinStack() {
        this.list = new ArrayList<>();
    }

    public void push(int x) {
        if(this.list.size() == 0){
            this.min = x;
            this.list.add(x);
            this.list.add(x);
        }else{
            if(x <= min){
                this.list.add(min);
                this.list.add(x);
                this.min = x;
            }else {
                this.list.add(x);
            }
        }
    }

    public void pop() {
        if(this.list.size() > 0) {
            if (this.top() == this.min) {
                this.list.remove(list.size() - 1);
                this.min = this.top();
                this.list.remove(list.size() - 1);
            } else {
                this.list.remove(list.size() - 1);
            }
        }
    }

    public int top() {
        if(list.size() != 0)
            return this.list.get(list.size() - 1);
        return 0;
    }

    public int getMin() {
        if(this.list.size() > 0)
            return this.min;
        return 0;
    }
}