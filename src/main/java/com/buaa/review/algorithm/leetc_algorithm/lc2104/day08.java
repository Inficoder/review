package com.buaa.review.algorithm.leetc_algorithm.lc2104;

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

    public static void main(String[] args) {
        int[] arr = {0, 0, 0};
        boolean b = canPlaceFlowers(arr, 2);
        System.out.println(b);
    }
}
