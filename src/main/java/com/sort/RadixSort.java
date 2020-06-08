package com.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void radixSort(int[] arr) {
        //取出最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //判断最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组,表示10个桶,每个桶就是一个二维数组
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候,数据溢出,则每个以为数组(桶)大小定位arr.length
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中,实际放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        // bucketElementCounts[0] 记录的是bucket[0] 这一个桶的放入的数据的个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n=1;i < maxLength; i++,n*=10) {
            //针对每个元素的位数进行排序处理 , 第一次是个位, 第二次是十位,第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个数
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来数组)
            int index = 0;
            //遍历每个桶,取出数据,放回原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶有数据,才放回原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶,取出数据
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出数据放到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //处理完一轮后,把桶的计数单位 bucketElementCounts[k]清零
                bucketElementCounts[k]=0;
            }
        }
    }
}
