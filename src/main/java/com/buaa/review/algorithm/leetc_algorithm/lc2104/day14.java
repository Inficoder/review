package com.buaa.review.algorithm.leetc_algorithm.lc2104;


/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 */
public class day14 {

    /**
     * 大数相乘，大数的排列组合等为什么要取模
     * 1000000007是一个质数（素数），对质数取余能最大程度避免结果冲突/重复
     * int32位的最大值为2147483647，所以对于int32位来说1000000007足够大。
     * int64位的最大值为2^63-1，用最大值模1000000007的结果求平方，不会在int64中溢出。
     * 所以在大数相乘问题中，因为(a∗b)%c=((a%c)∗(b%c))%c，所以相乘时两边都对1000000007取模，再保存在int64里面不会溢出。
     * 这道题为什么要取模，取模前后的值不就变了吗？
     * 确实：取模前 f(43) = 701408733, f(44) = 1134903170, f(45) = 1836311903, 但是 f(46) > 2147483647结果就溢出了。
     *
     * _____，取模后 f(43) = 701408733, f(44) = 134903163 , f(45) = 836311896, f(46) = 971215059没有溢出。
     *
     * 取模之后能够计算更多的情况，如 f(46)
     *
     * 这道题的测试答案与取模后的结果一致。
     *
     * 总结一下，这道题要模1000000007的根本原因是标准答案模了1000000007。不过大数情况下为了防止溢出，模1000000007是通用做法，原因见第一点。

     1e9+7这个数，满足[0,1e9+7)内所有数，两个数相加不爆int，两个数相乘不爆long long

     还有一点，由于1e9+7是质数，所以在[1,1e9+7)内所有数都存在关于1e9+7的逆元（这样就可以做除法）
     */

    public static int numWays(int n){
        if(n == 0) return 1;
        if(n == 1) return 1;
        return numWays(n - 1) + numWays(n - 2);
    }

    public static int numWays2(int n){
        if(n == 0) return 1;
        if(n == 1) return 1;
        int i = 1;
        int j = 1;
        int temp;
        while(n >= 2){
            temp = (i + j)%1000000007;
            i = j;
            j = temp;
            n--;
            System.out.println(j);
        }
        return j;
    }

    /**
     * 翻转一棵二叉树。
     *
     * 示例：
     *
     * 输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * 备注:
     * 这个问题是受到 Max Howell 的 原问题 启发的 ：
     *
     * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
     */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    public static TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode temp;
        TreeNode tRoot = root;
        temp = tRoot.left;
        tRoot.left = tRoot.right;
        tRoot.right = temp;
        if(tRoot.left != null) invertTree(tRoot.left);
        if(tRoot.right != null) invertTree(tRoot.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(5);
        root.left = left;
        root.right = right;
        root.left.right = left1;
        root.right.right = right1;

        TreeNode t = invertTree(root);

    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}