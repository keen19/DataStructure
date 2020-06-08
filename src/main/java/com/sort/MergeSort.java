package com.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int tmp[] =new int[arr.length];
        mergeSort(arr,0,arr.length-1,tmp);
        System.out.println(Arrays.toString(arr));
    }
    //分解、合并
    public static void mergeSort(int arr[], int left, int right, int tmp[]) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, tmp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, tmp);
            //合并
            merge(arr,left,mid,right,tmp);
        }
    }

    /**
     * 合并方法
     * @param arr   要排序的数组
     * @param left  左边有序序列的索引
     * @param mid   中间索引
     * @param right 右边有序序列的索引
     * @param tmp   载体数组
     */
    public static void merge(int arr[], int left, int mid, int right, int tmp[]) {
        int i = left;
        int j = mid + 1;
        int t = 0;// tmp[]索引

        while (i <= mid && j <= right) {
            //如果左边的数比右边的小,放入tmp数组
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {//如果右边的数比左边的小,放入tmp数组
                tmp[t++] = arr[j++];
            }
        }
        //把剩余的数放入tmp数组
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        while (j <= right) {
            tmp[t++] = arr[j++];
        }
        //把temp的数组元素拷贝到arr
        //重置tmp的索引下标
        t = 0;
        while (left <= right) {
            arr[left++] = tmp[t++];
        }
    }
}
