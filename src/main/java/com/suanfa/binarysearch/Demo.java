package com.suanfa.binarysearch;

public class Demo {
    public static void main(String[] args) {
    int arr[]={1,3,8,10,11,67,100};
    int index=binarySearch(arr,10);
        System.out.println("index="+index);
    }

    /**
     *
     * @param arr 要查找的有序属猪
     * @param target    查找的值
     * @return  target下标
     */
    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            //如果中间值大于目标值,向中间值左边查找
            if (arr[mid]>target){
                right=mid-1;
            }else {
                //如果中间值小于目标值,向中间值右边查找
                left=mid+1;
            }
        }
        //未找到
        return -1;
    }
}
