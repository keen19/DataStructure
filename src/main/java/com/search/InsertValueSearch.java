package com.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(index);
    }

    private static int insertValueSearch(int[] arr, int left, int right, int value) {
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);

        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        //查找的值大于数组中值,往右扫描
        if (value > arr[mid]) {
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (value < arr[mid]) {
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

}
