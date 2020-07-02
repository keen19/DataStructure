package com.search;

//线性查找
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 3, 6, 7, 12, 65, 6, 2};
        int index = seqSearch(arr, 4);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到,下标为:" + index);
        }
    }

    /**
     * @param arr   要查找的数组
     * @param value 要查找的值
     * @return i 数组对应的下标
     */
    private static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
