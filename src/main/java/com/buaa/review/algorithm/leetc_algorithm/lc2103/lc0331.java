package com.buaa.review.algorithm.leetc_algorithm.lc2103;

public class lc0331 {

    /**
     *
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * 示例 2：
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * 示例 3：
     *
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *  
     *
     * 提示：
     *
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     *
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        int flag = 0;
        while(l1 != null && l2 != null){
            int temp;
            temp = l1.val + l2.val;
            if(flag == 1){
                temp ++;
                flag = 0;
            }
            if(temp >= 10){
                temp = temp % 10;
                flag = 1;
            }
            System.out.println(temp);
            node.val = temp;
            node.child = new ListNode();
            node = node.child;
            l1 = l1.child;
            l2 = l2.child;
            if(l1.child == null || l2.child == null){
                if(l1.child != null) {
                    node.child = l1.child;
                }else{
                    node.child = l2.child;
                }
            }

        }

        return node;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);

        node1.child = node2;
        node2.child = node3;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);

        node4.child = node5;
        node5.child = node6;

        ListNode listNode = addTwoNumbers(node1, node4);
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.child;
        }
    }

}
class ListNode{
    int val;
    ListNode child;
    ListNode(int val){
        this.val = val;
    }
    ListNode(){ }
}