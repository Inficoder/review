package com.buaa.review.algorithm.leetc_algorithm.lc2103;

public class lc210310 {

    static LNode addLNode(LNode l1, LNode l2){
        LNode l = new LNode();
        LNode res = l;
        boolean addFlag = false;
        while(l1 != null || l2 != null || addFlag){
            l.next = new LNode();
            l = l.next;
            int temp = 0;
            if(addFlag) temp+=1;
            if(l1 != null){
                temp += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                temp += l2.val;
                l2 = l2.next;
            }
            if(temp > 10){
                addFlag = true;
                temp = temp - 10;
            }else{
                addFlag = false;
            }
            l.val = temp;
        }
        return res.next;
    }

    public static void main(String[] args) {
        LNode l1 = new LNode();
        LNode l11 = new LNode();
        LNode l111 = new LNode();
        LNode l2 = new LNode();
        LNode l22 = new LNode();
        LNode l222 = new LNode();
        l1.val = 1;
        l11.val = 2;
        l111.val = 3;

        l2.val = 6;
        l22.val = 9;
        l222.val = 9;
        l1.next = l11;
        l11.next = l111;
        l2.next = l22;
        l22.next = l222;

        LNode lNode = addLNode(l1, l2);
        while(lNode != null){
            System.out.println(lNode.val);
            lNode = lNode.next;
        }

    }
}

class LNode{
    int val;
    LNode next;
}
