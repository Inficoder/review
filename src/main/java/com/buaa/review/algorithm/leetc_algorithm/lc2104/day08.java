package com.buaa.review.algorithm.leetc_algorithm.lc2104;

import java.util.HashMap;

public class day08 {
    /**
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。
     * 可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     * 给你一个整数数组  flowerbed 表示花坛，
     * 由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
     * 另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
     *
     * 示例 1：
     * 输入：flowerbed = [1,0,0,0,1], n = 1
     * 输出：true
     *
     * 示例 2：
     * 输入：flowerbed = [1,0,0,0,1], n = 2
     * 输出：false
     *
     * 提示：
     * 1 <= flowerbed.length <= 2 * 104
     * flowerbed[i] 为 0 或 1
     * flowerbed 中不存在相邻的两朵花
     * 0 <= n <= flowerbed.length
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) return true;
        if(flowerbed.length == 1 && flowerbed[0] == 0 && (n == 1)) return true;
        if(flowerbed.length == 2 && flowerbed[0] + flowerbed[1] == 0 && n == 1) return true;
        if(flowerbed.length == 2 && flowerbed[0] + flowerbed[1] == 1) return false;
        int res = 0;
        for (int i = 0; i < flowerbed.length - 2; i++) {
            if(i == 0 && flowerbed[0] == 0 && flowerbed[1] == 0){
                res ++;
                flowerbed[0] = 1;
            }
            if(i == flowerbed.length - 3 && flowerbed[i] == 1 && flowerbed[i + 1] == 0 && flowerbed[i + 2] == 0){
                res ++;
                flowerbed[i + 2] = 1;
            }

            if(flowerbed[i] == 0 && flowerbed[i + 1] == 0 && flowerbed[i + 2] == 0){
                res ++;
                flowerbed[i + 1] = 1;
            }

            if(res >= n){
                return true;
            }
        }
        return false;
    }

    /**
     * 易混淆数
     * 给定一个数字 N，当它满足以下条件的时候返回 true：
     * 把原数字旋转180°以后得到新的数字。
     * 如 0, 1, 6, 8, 9 旋转 180° 以后，得到了新的数字 0, 1, 9, 8, 6 。
     * 2, 3, 4, 5, 7 旋转 180° 后,得到的不是数字。
     * 易混淆数字 (confusing number) 就是一个数字旋转180°以后，得到和原来不同的数字，且新数字的每一位都是有效的。
     *
     * 示例 1：
     *
     * 输入：6
     * 输出：true
     * 解释：
     * 把 6 旋转 180° 以后得到 9，9 是有效数字且 9!=6 。

     * 示例 2：
     *
     * 输入：89
     * 输出：true
     * 解释:
     * 把 89 旋转 180° 以后得到 68，86 是有效数字且 86!=89 。

     * 示例 3：
     * 输入：11
     * 输出：false
     * 解释：
     * 把 11 旋转 180° 以后得到 11，11 是有效数字但是值保持不变，所以 11 不是易混淆数字。

     * 示例 4：
     *
     * 输入：25
     * 输出：false
     * 解释：
     * 把 25 旋转 180° 以后得到的不是数字。

     * 提示：
     *
     * 0 <= N <= 10^9
     * 可以忽略掉旋转后得到的前导零，例如，如果我们旋转后得到 0008 那么该数字就是 8 。
     */

    public static boolean confusingNumber(int N) {
        HashMap<Integer, Integer> vert = new HashMap<>();
        vert.put(0, 0);
        vert.put(1, 1);
        vert.put(6, 9);
        vert.put(8, 8);
        vert.put(9, 6);
        int M = N;
        int temp;
        StringBuilder str = new StringBuilder();
        while(N > 0) {
            temp = N % 10;
            N = N / 10;
            str.append(vert.get(temp));
            if(!vert.containsKey(temp)) return false;
        }
//        System.out.println(Integer.parseInt(String.valueOf(str)));
        return !(Integer.parseInt(String.valueOf(str)) == M);
    }

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，
     * 同时仍保留空格和单词的初始顺序。
     *
     * 示例：
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     *  
     */
    public static String reverseWords(String s){
        StringBuilder res = new StringBuilder();
        String[] strTemp = s.split(" ");
        for (int i = 0; i < strTemp.length; i++) {
            for (int j = strTemp[i].length() - 1; j >= 0; j--) {
                res.append(strTemp[i].charAt(j));
            }
            if(i != strTemp.length - 1) res.append(" ");
        }
        return res.toString();
    }

    // java 字符串不可改变 所以一定会消耗多余空间 不能使用原地置换法
    public static String reverseWords2(String s) {
        StringBuilder res = new StringBuilder();
        int left = 0, right;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ' || i == s.length() - 1){
                right = i == s.length() - 1 ? s.length() - 1 : i - 1;
                for(int j = right; j >= left; j--){
                    res.append(s.charAt(j));
                }
                if(i != s.length() - 1) res.append(' ');
                left = i + 1;
            }
        }
        return res.toString();
    }


    public static void main(String[] args) {
        String s = reverseWords2("Let's take LeetCode contest");
        System.out.println(s);
    }
}
