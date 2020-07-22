package com.tree;

public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排列
        int arr[] = {4, 6, 8, 5, 9,2,3,7,1};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int tmp = 0;

        //把数组调整成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            //交换,把最大值放到末端
            tmp=arr[j];
            arr[j]=arr[0];
            arr[0]=tmp;
            adjust(arr,0,j);
        }

    }
    //将一个数组(二叉树),调整成一个大顶堆

    /**
     * @param arr    要排序的数组
     * @param i      非叶子结点在数组中的索引
     * @param length 多少个元素进行调整,length逐渐减少
     */
    public static void adjust(int[] arr, int i, int length) {
        //辅助变量,取出当前元素的值
        int tmp = arr[i];

        //j是i 结点的左子结点,
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                //左子结点小于右子结点
                //j+1,指向右子结点
                j++;
            }
            if (arr[j] > tmp) {
                //子节点大于父结点,则交换
                arr[i] = arr[j];
                //i指向j,继续循环比较
                i = j;
            } else {
                //假如一直找不到
                break;
            }
            //for循环结束,比较结束,把tmp的值放到i上 即放到底部
            arr[i] = tmp;
        }
    }
}

