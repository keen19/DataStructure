package com.sparsearray;

import java.util.concurrent.ForkJoinPool;

public class test {
    public static void main(String[] args) {
        //创建原始二维数组11*11
        // 0:没有棋子 1:黑子 2:蓝子
        int[][] chessArr=new int[11][11];
        //第2行第3列有一个黑子
        chessArr[1][2]=1;
        //第3行第4列有一个蓝子
        chessArr[2][3]=2;
        chessArr[3][3]=2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr) {
            for (int item : row) {
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //将二维数组转为 稀疏数组
        //1.先遍历二维数组，得到非0的个数
        int sum=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j]!=0){
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][]=new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=11;

        //遍历二维数组，将非0的值存放到稀疏数组
        // count记录是第几个非0数据
        int count=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                //chessArr[i][j]为非0的值即1或2
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为:");
        System.out.println("行\t列\t值");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //稀疏数组 转为 二维数组
        /**
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，
         * 2.读取稀疏数组后几行的数据，并返回原始的二维数组即可
         */

        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int row=sparseArr[0][0];
        int column=sparseArr[0][1];
        //获取二维数组的行列
        int chessArr2[][]=new int[row][column];

        //2.读取稀疏数组后几行的数据，并赋值给二维数组

        for (int i = 1; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] ints: chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }
}
