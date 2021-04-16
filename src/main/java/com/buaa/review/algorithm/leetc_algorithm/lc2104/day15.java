package com.buaa.review.algorithm.leetc_algorithm.lc2104;

import com.buaa.review.java.injection.S;

import java.lang.reflect.Array;
import java.util.*;

public class day15 {
    /**
     *给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
     *
     * 形式上，如果可以找出索引 i+1 < j 且满足 A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1] 就可以将数组三等分。
     *
     * 示例 1：
     * 输入：[0,2,1,-6,6,-7,9,1,2,0,1]
     * 输出：true
     * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
     *
     * 示例 2：
     * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
     * 输出：false
     * 示例 3：
     *
     * 输入：[3,3,6,5,-2,2,5,1,-9,4]
     * 输出：true
     * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
     *
     * 提示：
     * 3 <= A.length <= 50000
     * -10^4 <= A[i] <= 10^4
     */

    public static boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        if(arr.length < 3) return false;
        for(int i = 0; i < arr.length; i ++){
            sum += arr[i];
        }
        if(sum % 3 != 0) return false;
        sum = sum / 3;
        int temp = 0;
        int count = 0;
        for(int i = 0; i < arr.length; i ++){
            temp += arr[i];
            if(temp == sum && count < 3){
                count ++;
                if(count != 3) temp = 0;
            }
        }
        return temp == sum && count == 3;
    }


    /**
     * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
     *
     * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
     *
     * 示例 1：
     *
     * 输入：28
     * 输出：True
     * 解释：28 = 1 + 2 + 4 + 7 + 14
     * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
     * 示例 2：
     *
     * 输入：num = 6
     * 输出：true
     * 示例 3：
     *
     * 输入：num = 496
     * 输出：true
     * 示例 4：
     *
     * 输入：num = 8128
     * 输出：true
     * 示例 5：
     *
     * 输入：num = 2
     * 输出：false
     *
     * 提示：
     * 1 <= num <= 108
     */

    public static boolean checkPerfectNumber(int num) {
        if(num < 2)
            return false;
        int res = 1;
        for(int i = 2; i * i <= num; i ++){
            if(num % i == 0){
                res += i;
                if(i != Math.pow(num, 0.5))
                    res += num/i;
            }
        }
        return num == res;
    }

    /**
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
     * (1) 每次只能移动一个盘子;
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * (3) 盘子只能叠在比它大的盘子上。
     *
     * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
     *
     * 你需要原地修改栈。
     *
     * 示例1:
     *
     *  输入：A = [2, 1, 0], B = [], C = []
     *  输出：C = [2, 1, 0]
     * 示例2:
     *
     *  输入：A = [1, 0], B = [], C = []
     *  输出：C = [1, 0]
     * 提示:
     *
     * A中盘子的数目不大于14个。
     */

    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        B.addAll(A.subList(1, A.size()));
        C.add(A.get(0));
        C.addAll(B);
    }


    /**
     * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
     *
     * 请实现 KthLargest 类：
     *
     * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
     * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
     *  
     *
     * 示例：
     *
     * 输入：
     * ["KthLargest", "add", "add", "add", "add", "add"]
     * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
     * 输出：
     * [null, 4, 5, 5, 8, 8]
     *
     * 解释：
     * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
     * kthLargest.add(3);   // return 4
     * kthLargest.add(5);   // return 5
     * kthLargest.add(10);  // return 5
     * kthLargest.add(9);   // return 8
     * kthLargest.add(4);   // return 8
     *  
     *
     * 提示：
     * 1 <= k <= 104
     * 0 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * -104 <= val <= 104
     * 最多调用 add 方法 104 次
     * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
     */
    //class Kthlargest

    /**
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     *
     * 输入:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * 输出: ["1->2->5", "1->3"]
     *
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     */

    public static List<String> binaryTreePaths(TreeNode root) {
        String path = "";
        List<String> paths = new ArrayList<>();
        constructPaths(root, path, paths);
        return paths;
    }

    public static void constructPaths(TreeNode root, String path, List<String> paths) {
        StringBuilder sb = new StringBuilder(path);
        if(root != null) {
            sb.append(root.val);
            if(root.right == null && root.left == null)
                paths.add(sb.toString());
            else{
                sb.append("->");
                constructPaths(root.left, sb.toString() ,paths);
                constructPaths(root.right, sb.toString() ,paths);
            }
        }
    }

    /**
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     *
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     *
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,2,2,5,2,3,7]
     * 输出：5
     * 解释：最长的和谐子序列是 [3,2,2,2,3]
     * 示例 2：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：2
     * 示例 3：
     *
     * 输入：nums = [1,1,1,1]
     * 输出：0
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 2 * 104
     * -109 <= nums[i] <= 109
     */

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin = 0,res = 0;
        for(int end = 0;end < nums.length;end++){
            while(nums[end] - nums[begin] > 1)
                begin++;
            if(nums[end] - nums[begin] == 1)
                res = Math.max(res,end - begin + 1);
        }
        return res;
    }

    public int findLHS2(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if(map.containsKey(key + 1)){
                res = Math.max(res, res + map.get(key + 1));
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 7,3, 2, 4};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Integer integer : map.keySet()) {
            System.out.println(integer);
        }

    }
}

class KthLargest {
    int k;
    PriorityQueue<Integer> priorityQueue;

    public KthLargest(int k, int[] nums) {

        this.k = k;
        priorityQueue = new PriorityQueue<Integer>(k);
        for(int i: nums) {
            add(i);
        }
    }

    public int add(int val) {
        if(priorityQueue.size() < k) {
            priorityQueue.offer(val);

        }
        else if(priorityQueue.peek() < val) {
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
        return priorityQueue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */