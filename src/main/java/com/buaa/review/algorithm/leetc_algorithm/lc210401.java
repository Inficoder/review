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

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l21 = new ListNode(9);
        ListNode l22 = new ListNode(9);

        l1.next = l2; l2.next = l3; l3.next = l21; l21.next = l22;

        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);

        l4.next = l5; l5.next = l6;
        ListNode listNode = addTwoNumbers(l1, l4);
        System.out.println("----");
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
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