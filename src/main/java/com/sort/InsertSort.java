package com.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {4, 4, 71, 7, 8, 12, 456, 234};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        //定义载体
        int tmp;
        for (int i = 1; i < arr.length; i++) {
            tmp = arr[i];
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            if (j != i) {
                arr[j] = tmp;
            }
        }
    }
}
