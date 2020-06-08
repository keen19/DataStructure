package com.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0,};
        shellSort(arr);
    }

    private static void shellSort(int[] arr) {
        //定义一个载体,存放交换数据
        int tmp = 0;
        int count = 0;
        //gap为步长分组,如 长度10 则分5组, 两个数为1组,一直分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //分组里面的数据互相比较,
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果后面的数小于前面的数,则交换
                    if (arr[j + gap] < arr[j]) {
                        tmp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
            System.out.println("第" + (++count) + "次" + Arrays.toString(arr));

        }
    }

    private static void shellSort2(int[] arr) {
        //增量gap,并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                //定义载体,存放数据
                int tmp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    //插入排序
                    while (j - gap >= 0 && tmp < arr[j - gap]) {
                        //下标小但值大的 放到下标大的地方
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //下标大但值小的 放到下标小的地方
                    arr[j] = tmp;
                }
            }
        }
    }

}
