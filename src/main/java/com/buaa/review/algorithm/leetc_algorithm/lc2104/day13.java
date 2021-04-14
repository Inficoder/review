package com.buaa.review.algorithm.leetc_algorithm.lc2104;

import java.util.HashMap;
import java.util.Stack;

public class day13 {
    /**
     * 给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。
     *
     * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：date = "2019-01-09"
     * 输出：9
     * 示例 2：
     *
     * 输入：date = "2019-02-10"
     * 输出：41
     * 示例 3：
     *
     * 输入：date = "2003-03-01"
     * 输出：60
     * 示例 4：
     *
     * 输入：date = "2004-03-01"
     * 输出：61
     *  
     *
     * 提示：
     *
     * date.length == 10
     * date[4] == date[7] == '-'，其他的 date[i] 都是数字。
     * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日。
     *
     */

    //split
    public static int dayOfYear(String date) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 31);
        map.put(2, 28);
        map.put(3, 31);
        map.put(4, 30);
        map.put(5, 31);
        map.put(6, 30);
        map.put(7, 31);
        map.put(8, 31);
        map.put(9, 30);
        map.put(10, 31);
        map.put(11, 30);
        map.put(12, 31);
        String[] strArr = date.split("-");
        int year = Integer.parseInt(strArr[0]);
        int month = Integer.parseInt(strArr[1]);
        int day = Integer.parseInt(strArr[2]);
        int res = 0;
        if(month > 2 && year % 4 == 0 && year % 100 != 0) res ++;
        for(int i = 1; i < month; i ++){
            res += map.get(i);
        }
        res += day;
        return res;
    }

    // substring 更快  使用数组
    public static int dayOfYear2(String date) {
        int[] map = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        int res = 0;
        if(year % 4 == 0 && year % 100 != 0) map[1] = 29;
        for(int i = 0; i < month - 1; i ++){
            res += map[i];
        }
        res += day;
        return res;
    }

    /**
     *实现一个MyQueue类，该类用两个栈来实现一个队列。
     *
     * 示例：
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1);
     * queue.push(2);
     * queue.peek();  // 返回 1
     * queue.pop();   // 返回 1
     * queue.empty(); // 返回 false
     *
     * 说明：
     *
     * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
     */


    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.pop());   // 返回 1
        System.out.println(queue.empty()); // 返回 false
    }
}

// 04 13
class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        this.s1 = new Stack<Integer>();
        this.s2 = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(s2.empty()) {
            while (!s1.empty())
                s2.push(s1.pop());
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(s2.empty()) {
            while(!s1.empty())
                s2.push(s1.pop());
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}
