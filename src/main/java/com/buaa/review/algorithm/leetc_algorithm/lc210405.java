package com.buaa.review.algorithm.leetc_algorithm;

import java.util.Arrays;

public class lc210405 {
    /**
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * 示例 1：
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     *
     * 示例 2：
     *
     * 输入：n = 0
     * 输出：0
     *
     * 示例 3：
     *
     * 输入：n = 1
     * 输出：0
     *  
     * 提示：
     * 0 <= n <= 5 * 106
     *
     */

    public static int countPrimes(int n) {
        int res = 0;
        for (int i = 2; i < n; i++) {
            res += isPrime(i) ? 1 : 0;
        }
        return res;
    }

    public static boolean isPrime(int n){
        for (int i = 2; i * i <= n; i++) {
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 埃氏筛
     */

    public static int countPrimes2(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int res = 0;
        for (int i = 0; i < isPrime.length; i++) {
            if(isPrime[i] == 1){
                res ++;
                if(i*i < n){
                    for(int j = i*i; j < n; j+=i){
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 这题搜到一个非常牛逼的算法,叫做厄拉多塞筛法. 比如说求20以内质数的个数,首先0,1不是质数.2是第一个质数,然后把20以内所有2的倍数划去.2后面紧跟的数即为下一个质数3,然后把3所有的倍数划去.3后面紧跟的数即为下一个质数5,再把5所有的倍数划去.以此类推.
     *
     * 代码的实现上用了非常好的技巧:
     *
     * def countPrimes(self, n: int) -> int:
     *         if n < 3:
     *             return 0     
     *         else:
     *             # 首先生成了一个全部为1的列表
     *             output = [1] * n
     *             # 因为0和1不是质数,所以列表的前两个位置赋值为0
     *             output[0],output[1] = 0,0
     *              # 此时从index = 2开始遍历,output[2]==1,即表明第一个质数为2,然后将2的倍数对应的索引
     *              # 全部赋值为0. 此时output[3] == 1,即表明下一个质数为3,同样划去3的倍数.以此类推.
     *             for i in range(2,int(n**0.5)+1):
     *                 if output[i] == 1:
     *                     output[i*i:n:i] = [0] * len(output[i*i:n:i])
     *          # 最后output中的数字1表明该位置上的索引数为质数,然后求和即可.
     *         return sum(output)
     * 在上面遍历索引的时候用到了一个非常好的技巧. 即i是从(2,int(n**0.5)+1)而非(2,n).这个技巧是可以验证的,比如说求9以内的质数个数,那么只要划掉sqrt(9)以内的质数倍数,剩下的即全为质数. 所以在划去倍数的时候也是从i*i开始划掉,而不是i+i.
     *
     * 这个解法真是太赞了!又学到了很多~~~ 和大家分享一下
     * @param args
     */


    public static void main(String[] args) {
        int i = countPrimes(10);
        System.out.println(i);
    }

}
