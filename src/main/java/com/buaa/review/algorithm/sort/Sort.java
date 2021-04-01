package com.buaa.review.algorithm.sort;

public class Sort {

    public static int[] bubble(int[] arr){
        /**
         * 优化：加入flag  若在一次大循环中没有交换  则顺序已经排好  直接返回结果即可
         */
        boolean flag = true;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - 1; j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            if(flag) return arr;
        }
        return arr;
    }

    public static int[] insert(int[] arr){
        /**
         * 把第一个数作为已经排好的序列 将第二个数插入到排好序列中
         */
        for(int i = 1; i < arr.length; i++){
            int j = i;
            while(j > 0 && arr[j] < arr[j - 1]){
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
        return arr;
    }

    public static int[] choose(int arr[]){
        /**
         * 每次选一个最小的数  和大循环的i位置交换
         */
        for (int i = 0; i < arr.length; i ++) {
            int index = i;
            int min = arr[i];
            int temp;
            for(int j = i; j < arr.length; j ++){
                if(min > arr[j]){
                    min = arr[j];
                    index = j;
                }
            }
            if(index != i){
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
        return arr;
    }

    public static int[] quickSort(int[] arr){
        int left = 0, right = arr.length - 1;
        int flag = arr[left];
        while(left < right){

            while(left < right && arr[left] <= flag)
                left++;
            arr[right] = arr[left];
            while(left < right && arr[right] >= flag)
                right++;
            arr[left] = arr[right];
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = {7, 6, 1, 9, 8, 5, 2, 3};

        int[] bubble = choose(arr);
        for (int i : bubble) {
            System.out.println(i);
        }
    }
}