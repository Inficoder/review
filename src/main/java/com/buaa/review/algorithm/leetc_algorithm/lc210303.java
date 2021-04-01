package com.buaa.review.algorithm.leetc_algorithm;

public class lc210303 {
    /**
     * 338. 比特位计数
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     *
     * 进阶:
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     *
     * 1 2 3 4 5 6 7 8 9 10
     * 1 1 2 1 2 2 3 1 2 2
     * */
    public static int[] countBits(int num) {
        //o(n*32) 可优化
        int temp;
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int count = 0;
            temp = i;
            while(temp != 0 ){
                if((temp & 1) == 1){
                    count++;
                }
                temp = (temp >> 1);
            }
            res[i] = count;
        }
        return res;
    }


    /**
     *354. 俄罗斯套娃信封问题
     * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     *
     * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     *
     * 说明:
     * 不允许旋转信封。
     *
     * 示例:
     *                   23 54 64 67
     * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出: 3
     * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     *
     */
    public int maxEnvelopes(int[][] envelopes) {
        int[] flag = envelopes[0];
//        while()
        return 0;
    }

    public static void main(String[] args) {
        for (int i : countBits(9)) {
            System.out.println(i);
        }
    }
}
