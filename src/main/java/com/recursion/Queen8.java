package com.recursion;

public class Queen8 {
    //定义一个max表示一共有多少个皇后
    int max = 8;
    //定义数组array,保存皇后放置位置的结果
    //比如 arr={0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    //统计次数
    static int count = 0;
    //运行了judge()多少次
    static int number = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共有 " + count + " 种解法");
        System.out.println("一共运行了 " + number + " 次judge()方法");
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) {
            //n=8,则第8个皇后放好了
            count++;
            System.out.print("第" + count + "种:");
            print();
            System.out.println();
            return;
        }
        //依次放入皇后,并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到改行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列
            if (judge(n)) {
                //接着放n+1个皇后,即开始递归
                check(n + 1);
            }
        }
    }

    /**
     * 判断皇后的位置是否产生冲突
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        number++;
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]
            // 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs()求绝对值
            //Math.abs(n-i) == Math.abs(array[n] -array[i])
            // 表示判断第n个皇后是否和第i个皇后是否在同一斜线
            //无需判断是否在同一行,因为n一直在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //输出皇后的位置
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
