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

    public static void main(String[] args) {
        String[] arr = {"cat","bt","hat","tree"};
        String s = "atach";
        int i = countCharacters(arr, s);
        System.out.println(i);
    }
}
