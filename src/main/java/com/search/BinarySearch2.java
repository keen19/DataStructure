package com.search;

import java.util.ArrayList;

public class BinarySearch2 {


    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234, 1234, 1234, 1234};
        ArrayList index = binarySearch(arr, 0, arr.length, 1000);
        System.out.println(index);
    }

    private static ArrayList binarySearch(int[] arr, int left, int right, int value) {
        ArrayList<Integer> midList = new ArrayList<>();
        //基准值,设为数组中间的值
        int mid = (left + right) / 2;
        //设置一个载体
        int temp = mid - 1;
        while (left <= right) {
            if (value == arr[mid]) {
                //找到
                midList.add(mid);
                break;
            } else if (value > arr[mid]) {
                //查询的值大于中间值 往右边递归查找
                return binarySearch(arr, mid + 1, right, value);
            } else if (value < arr[mid]) {
                //查询的值小于中间值, 往左边递归查找
                return binarySearch(arr, left, mid - 1, value);
            }
        }
        //往左扫描是否有与value相同值
        while (temp >= 0 && temp < mid && arr[temp] == value) {
            midList.add(temp);
            temp--;
        }

        temp = mid + 1;
        //往右扫描是否有与value相同值
        while (temp < right && temp > mid && arr[temp] == value) {
            midList.add(temp);
            temp++;
        }
        return midList;
    }
}

