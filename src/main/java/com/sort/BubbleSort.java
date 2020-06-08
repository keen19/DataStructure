package com.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 2, 1, 8, 5};
        new BubbleSort(arr);

    }

    public BubbleSort(int arr[]) {
        //定义载体,放入交换的数据
        int tmp = 0;
        //判断是否交换数据
        boolean flag =true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                //如果后面比前面的打,交换数据
                if (arr[i] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                    flag=false;
                }
            }
            //如果没有交换,退出程序
            if (flag){
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
