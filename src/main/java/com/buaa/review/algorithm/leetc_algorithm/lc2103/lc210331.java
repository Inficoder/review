package com.buaa.review.algorithm.leetc_algorithm.lc2103;

import java.util.ArrayList;
import java.util.List;

public class lc210331 {
    /**
     *
     给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

     示例 1：

     输入：nums = [1,2,2]
     输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     示例 2：

     输入：nums = [0]
     输出：[[],[0]]

     提示：

     1 <= nums.length <= 10
     -10 <= nums[i] <= 10
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;

        for (int i = 0; i < nums.length; i++) {

        }
        return res;
    }

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        int[] array = new int[21];
        for (int i = 0; i < nums.length; i++) {
            array[nums[i] + 10]++;
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                List<List<Integer>> lists = new ArrayList<>();
                List<Integer> list0 = new ArrayList<>();
                list0.add(i - 10);
                lists.add(list0);
                for (int j = 1; j < array[i]; j++) {
                    List<Integer> list = new ArrayList<>();
                    list.addAll(lists.get(lists.size() - 1));
                    list.add(i - 10);
                    lists.add(list);
                }
                int size = res.size();
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < lists.size(); k++) {
                        List<Integer> list = new ArrayList<>();
                        list.addAll(res.get(j));
                        list.addAll(lists.get(k));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        subsetsWithDup(arr);
        /** 1 2 3
         *  [] 1 12 122 2 22
         * 1 12 123 2 23 3
         * c40 + c41 + c42 + c43 +c44
         * 1 + 4 + 6 + 4 + 1
         */
    }
}

