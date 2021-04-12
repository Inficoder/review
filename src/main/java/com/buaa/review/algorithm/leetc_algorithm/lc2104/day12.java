package com.buaa.review.algorithm.leetc_algorithm.lc2104;


import java.util.Arrays;

public class day12 {
    /**
     * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     *
     * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
     * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
     * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
     * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     *
     * 示例 1：
     *
     * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
     *
     * 输出：3
     *
     * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
     *
     * 示例 2：
     *
     * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
     *
     * 输出：0
     *
     * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
     *
     * 限制：
     *
     * 2 <= n <= 10
     * 1 <= k <= 5
     * 1 <= relation.length <= 90, 且 relation[i].length == 2
     * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
     *
     * 方法二：动态规划
     * 我们用 dp[i][j] 表示数组的第 i 轮传递给编号 j 的人的方案数。
     * 若能传递给编号 y 玩家的所有玩家编号 x1,x2,x3... , 则第 i+1 轮传递信息给编号 y 玩家的递推方程为
     * dp[i+1][y] = sum(dp[i][x1],dp[i][x2],dp[i][x3]...)，
     * 其递推形式即
     * dp[i+1][y] += dp[i][x]
     *
     * 方法三：动态规划 + 空间优化
     * 由于方法二中的每一轮的状态数推导 dp[i+1][...]，只和 dp[i][...] 有关，因此可以用两个一维数组优化动态规划的空间复杂度。
     */
    //官方解法
    public static int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[k+1][n];
        dp[0][0] = 1;
        for(int i = 1;i<dp.length;i++){
            for(int[] arr:relation){
                dp[i][arr[1]] += dp[i-1][arr[0]];
            }
        }
        return dp[k][n-1];
    }
    // 重写的
    public static int numWays2(int n, int[][] relation, int k){
        int[][] dp = new int[k][n];
        for (int i = 0; i < dp.length; i++) {
            for (int[] rel : relation) {
                //先算出第一行  即第一次传递，到每一列的次数
                if(i == 0) dp[i][rel[1]] += rel[0] == 0 ? 1 : 0;
                //每一列值用前一列的特定结果相加
                else{
                    dp[i][rel[1]] += dp[i - 1][rel[0]];
                }
            }
        }
        //取结果
        return dp[k - 1][n - 1];
    }

    /**
     * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
     * 你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。
     * 注意：n 和 k 均为非负的整数。
     *
     * 示例
     * 输入：n = 3，k = 2
     * 输出：6
     * 解析：用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有：
     *             柱 1    柱 2   柱 3
     *  -----      -----  -----  -----
     *    1         c1     c1     c2
     *    2         c1     c2     c1
     *    3         c1     c2     c2
     *    4         c2     c1     c1
     *    5         c2     c1     c2
     *    6         c2     c2     c1
     *
     *    两种动态规划法  c1 c2 c2
     * 一、当前栅栏i与之前栅栏i-1颜色相同，更之前栅栏i-2的涂色方案为F(i-2)，当前栅栏的涂色方式有k-1种。
     * i与i-1的颜色不一样，当前栅栏颜色涂色方式有k-1种
     * F(i)=F(i-2)*k-1+F(i-1)*k-1
     *
     * 二、当前i的颜色，由i-1和i-2的颜色是否相同决定
     * 从i=2开始
     *
     * int diff_color = k * (k - 1);
     * int same_color = k;
     * 1
     * 2
     * i=1时有k种涂法，i=2时与i-1相同颜色有k中涂法，与i-1不同由k*(k-1)种涂法
     * 总数是diff_color+same_color
     * i=3时，i与i-1不同的涂法是(diff_color+same_color) (k-1)每种情况都有除开自己颜色的涂法
     * 而i与i-1相同的涂法 只能是diff_colork，必须满足前两个颜色不同，这里才能相同。
     */
    //动态规划
    public static int numWays(int n, int k){
        if(n == 0) return 0;
        if(n == 1) return k;
        int res = 0;
        int temp;
        int pp = 0;
        int p = k;
        for(int i = 2; i <= n; i ++){
            temp = (pp + p) * (k - 1);
            res += temp;
            pp = p;
            p = temp;
        }
        return res;
    }

    /**
     * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
     *
     * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
     *
     * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "loveleetcode", c = "e"
     * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]

     * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
     * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
     * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 3 。
     * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
     * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
     * 示例 2：
     *
     * 输入：s = "aaab", c = "b"
     * 输出：[3,2,1,0]
     *  
     *
     * 提示：
     * 1 <= s.length <= 104
     * s[i] 和 c 均为小写英文字母
     * 题目数据保证 c 在 s 中至少出现一次
     *
     */
    // 从前遍历一次 从后遍历一次
    public static int[] shortestToChar(String s, char c) {
        int length = s.length();
        int[] answer = new int[s.length()];
        Arrays.fill(answer, length);
        for(int i = 0; i < length; i ++){
            if(s.charAt(i) == c){
                answer[i] = 0;
                continue;
            }
            if(i != 0)
                answer[i] = Math.min(answer[i], answer[i - 1] + 1);
        }

        for(int i = length - 1; i >= 0; i --){
            if(s.charAt(i) == c){
                answer[i] = 0;
                continue;
            }
            if(i != length - 1)
                answer[i] = Math.min(answer[i], answer[i + 1] + 1);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] ints = shortestToChar("loveleetcode", 'e');
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }
}
