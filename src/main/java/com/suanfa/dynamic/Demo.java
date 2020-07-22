package com.suanfa.dynamic;

public class Demo {
    public static void main(String[] args) {
        //物品的重量
        int[] weight = {1, 4, 3};
        //物品价值
        int[] value = {1500, 3000, 2000};
        //背包的容量
        int m = 4;
        //物品个数
        int number = value.length;

        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int [][] v=new int[number+1][m+1];
    }
}
