package com.buaa.review.algorithm.leetc_algorithm;

public class lc210401 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode res = head;
        int temp = 0, flag = 0;
        while(l1 != null || l2 != null || flag > 0){
            temp = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + flag;
            flag = temp / 10;
            temp %= 10;
            head.next = new ListNode(temp);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            head = head.next;
        }
        return res.next;
    }


    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1：
     *
     * 输入：[3,2,3]
     * 输出：3
     * 示例 2：
     *
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *  
     * 进阶：
     * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     *
     * 摩尔投票法：
     * 核心就是对拼消耗。
     * 玩一个诸侯争霸的游戏，假设你方人口超过总人口一半以上，并且能保证每个人口出去干仗都能一对一同归于尽。最后还有人活下来的国家就是胜利。
     * 那就大混战呗，最差所有人都联合起来对付你（对应你每次选择作为计数器的数都是众数），或者其他国家也会相互攻击（会选择其他数作为计数器的数），但是只要你们不要内斗，最后肯定你赢。
     * 最后能剩下的必定是自己人。
     */


    // 奇数次异或 = 本身  偶数次 = 0;
    public static int majorityElement(int[] nums) {
        /**
         * 1.先排序 ->快排 o(log(n)), 求中位数 o(1)
         * 2.进阶？
         * 3.摩尔投票法
         */
        int count = 1, temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            count = (temp == nums[i]) ? count+1 : count-1;
            if(count == 0){
                temp = nums[i];
                count = 1;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        System.out.println(majorityElement(nums));
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
}