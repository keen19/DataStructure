package com.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {4, 1, 6, 7, 1, 5, 6};
        selectSort(arr);
    }

    public static void selectSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            //定义下标,用来存放找到的值小的下标
            int index = i;
            //定义载体
            int tmp;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    //找到值小的下标
                    index = j;
                }
            }
            //检查是否需要发生交换,即下标是否改变
            if (i != index) {
                //把值小的放到载体
                tmp = arr[index];
                //把值大的换到大下标
                arr[index] = arr[i];
                //把值小的放到小下标
                arr[i] = tmp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
