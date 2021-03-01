package com.buaa.review.algorithm.leetc_algorithm;

import java.util.HashMap;

/**
 * 在一个数组nums中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 示例：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 */
public class array {

    /**
     * 如果一个数字出现3次，它的二进制每一位也出现的3次。
     * 如果把所有的出现三次的数字的二进制表示的每一位都分别加起来，那么每一位都能被3整除。
     * 我们把数组中所有的数字的二进制表示的每一位都加起来。
     * 如果某一位能被3整除，那么这一位对只出现一次的那个数的这一肯定为0。
     * 如果某一位不能被3整除，那么只出现一次的那个数字的该位置一定为1.
     *
     */

    public static int getNumFromArray_1(int[] arr){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            Integer orDefault = map.getOrDefault(i, 0);
            map.put(i, orDefault + 1);
        }
        for (Integer integer : map.keySet()) {
            if(map.get(integer) == 1){
                return integer;
            }
        }
        return 0;
    }

    public static int t(int[] arr){
        int res = 0;
        int[] bitArr = new int[32]; // int -> 4个字节一个字节8位

        for (int i = 0; i < arr.length; i++) {
            //先把第一个数分摊到32位上
            for (int j = 0; j < bitArr.length; j++) {
                bitArr[j] += (arr[i]>>j & 1 ) == 1 ? 1 : 0;
            }
        }
        //对3取余=0，则该数字此位为0，对3取余!=0,则该数字此位为1
        //通过移位+或运算 将结果写进res
        for (int i = 0; i < bitArr.length; i++) {
            if(bitArr[i] % 3 == 1){
                res += Math.pow(2,i);
            }

        }
        return res;
    }

    public static int singleNumber(int[] nums) {
        int[] binarySum = new int[32];
        for(int i = 0; i< 32; i++){   //求每个二进制位的和
            int sum = 0;
            for(int num : nums){
                sum += (num >>i & 1);
            }
            binarySum[i] = sum;
        }
        int res = 0;
        for (int i = 0; i< 32; i++){
            if(binarySum[i]%3!=0){
                res += 1<<i;
            }
        }
        return res;
    }

    public static int singleNumber1(int[] nums) {
        int a = 0;
        int b = 0;

        for(int num : nums) {
            a = (a ^ num) & ~b;
            b = (b ^ num) & ~a;
        }

        return a;
    }
}
