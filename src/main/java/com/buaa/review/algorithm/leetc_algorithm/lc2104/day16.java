package com.buaa.review.algorithm.leetc_algorithm.lc2104;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class day16 {
    /**
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     */

    public static int mySqrt(int x) {
        if(x == 0) return 0;
        int res = 1;
        //防溢出 用除法
        while(x / res >= res && x / (res + 1) >= (res + 1)){
            res ++;
        }
        return res;
    }

    //二分查找
    public static int mySqrt2(int x) {
        int i = 1, j = x;
        while(i <= j){
            int mid = (i + j)/2;
            if(x / mid >= mid){
                if(x / (mid+1) < mid + 1) return mid;
                else i = mid + 1;
            } else{
                j = mid - 1;
            }
        }
        return 0;
    }

    //牛顿迭代
    public static int mySqrt3(int x) {
        if(x == 0) return 0;
        if(x == 1) return 1;
        int guess = x/2;
        while(x / guess < guess || x / (guess + 1) >= (guess + 1)){
            guess = (guess + x/guess)/2;
        }
        return guess;
    }

    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 你可以按任意顺序返回答案。
     *
     * 示例 1：
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     *
     * 示例 2：
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     * 提示：
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     * A[i][j] 是小写字母
     * 通过次数50,495提交次数68,171
     */

    //垃圾 需要优化
    public static List<String> commonChars(String[] A) {
        if(A.length < 2) return null;
        List<String> list = new ArrayList<>();
        String sTemp = A[0];
        for (int i = 0; i < sTemp.length(); i++) {
            boolean isAdd = true;
            String t = String.valueOf(sTemp.charAt(i));
            for (int j = 1; j < A.length; j++) {
                if (!A[j].contains(t)) {
                    isAdd = false;
                    break;
                }
                if(list.contains(t)){
                    int rCount = 0;
                    int nCount = 0;
                    for (String s : list) {
                        //原结果中t数量
                        if(t.equals(s)) rCount ++;
                    }
                    for (int i1 = 0; i1 < A[j].length(); i1++) {
                        if(String.valueOf(A[j].charAt(i1)).equals(t)){
                            nCount ++;
                        }
                    }
                    if(nCount <= rCount){
                        isAdd = false;
                        break;
                    }
                }
            }
            if(isAdd){
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
     *
     * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
     *
     * 注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
     *
     * 返回词汇表 words 中你掌握的所有单词的 长度之和。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
     * 输出：6
     * 解释：
     * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
     * 示例 2：
     *
     * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
     * 输出：10
     * 解释：
     * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
     *  
     *
     * 提示：
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length, chars.length <= 100
     * 所有字符串中都仅包含小写英文字母
     */

    public static int countCharacters(String[] words, String chars) {
        int res = 0;
        HashMap<String, Integer> rString = new HashMap<>();

        for (int i = 0; i < chars.length(); i++) {
            String s = String.valueOf(chars.charAt(i));
            rString.put(s, rString.getOrDefault(s, 0) + 1);
        }
        HashMap<String, Integer> rString2 = null;

        HashMap<String, Integer> nowString = new HashMap<>();
        for (String word : words) {
            rString2 = rString;
            for (int i = 0; i < word.length(); i++) {
                if(rString2.containsKey(String.valueOf(word.charAt(i)))){
                    if(rString2.get(word) == 1){
                        rString2.remove(word);
                    }else{
                        rString2.put(word, rString2.get(word) - 1);
                    }
                }else{
                    break;
                }
                res += word.length();
            }


        }

        return res;
    }

    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     *
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n) 。
     *
     * 示例 1：
     *
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     *
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     * 示例 3：
     *
     * 输入：4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     *  
     *
     * 提示：
     *
     * 0 <= n <= 30
     */

    public static int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int i = 1, j = 1;
        while(n > 2){
            int temp = i + j;
            i = j;
            j = temp;
            n --;
        }
        return j;
    }

    /**
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     *
     * 注意：本题相对原题稍作改动
     *
     * 示例：
     *
     * 输入： 1->2->3->4->5 和 k = 2
     * 输出： 4
     * 说明：
     *
     * 给定的 k 保证是有效的。
     */

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    //1.逆置 后取k个
    //2.先遍历大小 取n-k
    //3.快慢指针 √
    public static int kthToLast(ListNode head, int k) {
        ListNode quick = head, slow = head;
        while(k > 0){
            quick = quick.next;
            k -- ;
        }
        while(quick != null){
            quick = quick.next;
            slow = slow.next;
        }
        return slow.val;
    }

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     * 示例 1：
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     * 示例 2：
     *
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     *
     * 提示：
     * 给定链表的结点数介于 1 和 100 之间。
     */
    //同样快慢指针 quick 一次走两个  slow一次走一个
    public static ListNode middleNode(ListNode head) {
        if(head == null) return null;
        ListNode quick = head, slow = head;
        while(quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     *
     * 示例1：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * 限制：
     *
     * 0 <= 链表长度 <= 1000
     */

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode head = new ListNode();
        ListNode res = head;
        while(l1 != null || l2 != null){
            if(l1.val < l2.val){
                head.next = new ListNode(l1.val);
                l1 = l1.next;
            }else if(l1.val > l2.val){
                head.next = new ListNode(l2.val);
                l2 = l2.next;
            }else{
                head.next = new ListNode(l1.val);
                head = head.next;
                head.next = new ListNode(l2.val);
                l1 = l1.next;
                l2 = l2.next;
            }
            head = head.next;
            if(l1 == null){
                head.next = l2;
                break;
            }
            if(l2 == null){
                head.next = l1;
                break;
            }

        }
        return res.next;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);


        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;

        n4.next = n5;
        n5.next = n6;

        ListNode listNode = mergeTwoLists(n1, n4);
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
