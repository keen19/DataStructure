package com.search;

//二分查找
public class BinarySearch {


    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length, 8);
        System.out.println(index);
    }

    private static int binarySearch(int[] arr, int left, int right, int value) {
        //基准值,设为数组中间的值
        int mid = (left + right) / 2;
        while (left <= right) {
            if (value == arr[mid]) {
                //找到
                return mid;
            } else if (value > arr[mid]) {
                //查询的值大于中间值 往右边递归查找
                return binarySearch(arr, mid + 1, right, value);
            } else if (value < arr[mid]) {
                //查询的值小于中间值, 往左边递归查找
                return binarySearch(arr, left, mid - 1, value);
            }
        }
        return -1;
    }
}
