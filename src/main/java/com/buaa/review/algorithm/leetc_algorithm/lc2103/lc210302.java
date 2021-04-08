package com.buaa.review.algorithm.leetc_algorithm.lc2103;

import org.apache.tomcat.jni.Time;

public class lc210302 {
/**
 * 459. 重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 * 输入: "abab"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 */
    public static boolean repeatedSubstringPattern(String s) {
        // 超过时间限制
        for (int i = 1; i < s.length()/2; i++) {
            String replace = s.replace(s.substring(0, i), "");
//            String[] split = s.split(s.substring(0, i));
            if(replace.length() == 0){
                return true;
            }
        }
        return false;
    }


    public static boolean repeatedSubstringPattern_new(String s) {
        //一行
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }

    /**
     *
     * 给出 字符串 text 和 字符串列表 words,
     * 返回所有的索引对 [i, j]
     * 使得在索引对范围内的子字符串
     * text[i]…text[j]（包括 i 和 j）属于字符串列表 words。
     *
     * 示例 1:
     * 输入: text = "thestoryofleetcodeandme",
     * 		words = ["story","fleet","leetcode"]
     * 输出: [[3,7],[9,13],[10,17]]
     *
     * 示例 2:
     * 输入: text = "ababa", words = ["aba","ab"]
     * 输出: [[0,1],[0,2],[2,3],[2,4]]
     * 解释:
     * 注意，返回的配对可以有交叉，比如，"aba" 既在 [0,2] 中也在 [2,4] 中
     *
     * 提示:
     * 所有字符串都只包含小写字母。
     * 保证 words 中的字符串无重复。
     * 1 <= text.length <= 100
     * 1 <= words.length <= 20
     * 1 <= words[i].length <= 50
     * 按序返回索引对 [i,j]（即，按照索引对的第一个索引进行排序，
     * 			当第一个索引对相同时按照第二个索引对排序）。
     */

    public static int[][] indexPairs(String text, String[] words) {
        int[][] res = new int[words.length][2];
        for (int j = 0;j < words.length; j++) {
            for (int i = 0; i < text.length() - words[j].length(); i++) {
                if (words[j].equals(text.substring(i, i + words[j].length()))){
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = i + words[j].length() - 1;
                    res[j] = temp;
                    break;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String text = "thestoryofleetcodeandme";
        String[] words = {"story","fleet","leetcode"};
        int[][] ints = indexPairs(text, words);
        for (int i : ints[0]) {
            System.out.println(i);
        }
        for (int i : ints[1]) {
            System.out.println(i);
        }
        for (int i : ints[2]) {
            System.out.println(i);
        }

    }
}
