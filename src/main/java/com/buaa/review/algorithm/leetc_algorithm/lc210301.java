package com.buaa.review.algorithm.leetc_algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lc210301 {
    /**
     * 303. 区域和检索 - 数组不可变
     * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
     *
     * 实现 NumArray 类：
     *
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
     *
     * 示例：
     *
     * 输入：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     *
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     *
     */




    /**
     * 5689. 统计匹配检索规则的物品数量
     *
     *给你一个数组 items ，其中 items[i] = [typei, colori, namei] ，描述第 i 件物品的类型、颜色以及名称。
     *
     * 另给你一条由两个字符串 ruleKey 和 ruleValue 表示的检索规则。
     *
     * 如果第 i 件物品能满足下述条件之一，则认为该物品与给定的检索规则 匹配 ：
     *
     * ruleKey == "type" 且 ruleValue == typei 。
     * ruleKey == "color" 且 ruleValue == colori 。
     * ruleKey == "name" 且 ruleValue == namei 。
     * 统计并返回 匹配检索规则的物品数量 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
     * 输出：1
     * 解释：只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
     * 示例 2：
     *
     * 输入：items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
     * 输出：2
     * 解释：只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。
     *
     *
     */

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int keyInt = 0;
        if("color".equals(ruleKey)){
            keyInt = 1;
        }else if("name".equals(ruleKey)){
            keyInt = 2;
        }
        int res = 0;
        for (List<String> item : items) {
            if(item.get(keyInt).equals(ruleValue)){
                res++;
            }
        }
        return res;
    }

    /**
     * 771. 宝石与石头
     * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     *
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     *
     * 示例 1:
     *
     * 输入: J = "aA", S = "aAAbbbb"
     * 输出: 3
     * 示例 2:
     *
     * 输入: J = "z", S = "ZZ"
     * 输出: 0
     * 注意:
     *
     * S 和 J 最多含有50个字母。
     *  J 中的字符不重复。
     *
     *
     */
    public static int numJewelsInStones(String jewels, String stones) {
        /**
         * 运行性能差
         */
        String[] jewelsArr = jewels.split("");
        String[] split = stones.split("");
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String s : split) {
            if(map.containsKey(s)){
                map.put(s, map.get(s) + 1);
            }else{
                map.put(s, 1);
            }
        }

        for (String s : jewelsArr) {
            if(map.containsKey(s))
                res += map.get(s);
        }
        return res;
    }

    public static int numJewelsInStones_new(String jewels, String stones) {
        /**
         * 仍然慢
         */
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < stones.length(); i++) {
            char temp = stones.charAt(i);
            if(map.containsKey(temp)){
                map.put(temp, map.get(temp) + 1);
            }else{
                map.put(temp, 1);
            }
        }
        int res = 0;
        for (int i = 0; i < jewels.length(); i++) {
            char temp = jewels.charAt(i);
            if(map.containsKey(temp))
                res += map.get(temp);
        }
        return res;
    }

    public static int numJewelsInStones_new2(String jewels, String stones) {
        int[] map = new int['z' - 'A' + 1];
        for (int i = 0; i < jewels.length(); i++) {
            map[jewels.charAt(i) - 'A']++;
        }
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if(map[stones.charAt(i) - 'A'] > 0){
                count++;
            }
        }
        return count;
    }

/**
 * 1431. 拥有最多糖果的孩子
 * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 *
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
 *
 * 示例 1：
 *
 * 输入：candies = [2,3,5,1,3], extraCandies = 3
 * 输出：[true,true,true,false,true]
 * 解释：
 * 孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
 * 孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
 * 孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
 * 孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
 * 孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
 */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        /**
         * too easy to do
         */
        return null;
    }

    /**
     * 1470. 重新排列数组
     * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     *
     * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     * 示例 1：
     *
     * 输入：nums = [2,5,1,3,4,7], n = 3
     * 输出：[2,3,5,4,1,7]
     * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
     */


    public static int[] shuffle(int[] nums, int n) {
        //时间空间都为o(n) 空间可优化
        int[] res = new int[n << 1];
        int temp = 0;
        for (int i = 0; i < n; i++) {
            res[temp++] = nums[i];
            res[temp++] = nums[i + n];
        }
        return res;
    }

    public static int[] shuffle_new(int[] nums, int n) {
        //时间o(n) 空间o(1)
        return nums;
    }

    public static void main(String[] args) {
        int a = 7;
        System.out.println(a >> 1);
    }




}
class NumArray{

    int[] nums;

    public NumArray(int[] nums) {
        int[] temp = new int[nums.length + 1];
        temp[0] = 0;
        for (int i = 1; i < temp.length; i++) {
            temp[i] = temp[i - 1] + nums[i - 1];
        }
        this.nums = temp;
    }

    public int sumRange(int i, int j) {
        return this.nums[j + 1] - this.nums[i];
    }

    public static void main(String[] args) {
        int[] res = {1, 3, 7, 5, 4, 2, 8};
        NumArray numArray = new NumArray(res);
        for (int num : numArray.nums) {
            System.out.println(num);
        }
        int i = numArray.sumRange(0, 5);
        System.out.println(i);
    }

    /**
     * 分析：初始化时间、空间复杂度 都为 o(n)
     *      每次执行时间复杂度为o(1)
     */


}
