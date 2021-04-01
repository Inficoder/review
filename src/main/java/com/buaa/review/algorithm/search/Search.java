package com.buaa.review.algorithm.search;

public class Search {

    public static int binarySearch(int[] arr, int target, int start, int end){
        /**
         * 递归法 效率可优化
         */
        if(arr.length < 2) return -1;
        if(start > end)
            return -1;
        int mid = (start + end) / 2;
        if(arr[mid] == target)
            return mid;
        else if(arr[mid] > target)
            return binarySearch(arr, target, 0, mid - 1);
        else
            return binarySearch(arr, target, mid + 1, end);
    }

    public static int binarySearch2(int[] arr, int target, int start, int end){
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] == target)
                return mid;
            else if(arr[mid] > target)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,11,45};
        int i = binarySearch2(arr, 87, 0, 6);
        System.out.println(i);
    }
}
