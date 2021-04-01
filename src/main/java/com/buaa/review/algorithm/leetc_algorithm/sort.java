package com.buaa.review.algorithm.leetc_algorithm;


/**
 *  4 7 1 2 5 9 3
 *
 */
public class sort {
    public static int[] quickSort(int[] arr, int l, int r){
        while(l < r){
            while(arr[l] <= arr[l] && l < r)
                l++;
            while(arr[r] >= arr[l] && l < r)
                r--;
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        quickSort(arr, l, r - 1);
        quickSort(arr, l+1, r);
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3,5,1,2,7,6,4};
        for (int i : quickSort(arr, 0, arr.length)) {
            System.out.println(i);
        }
    }
}
