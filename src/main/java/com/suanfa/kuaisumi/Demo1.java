package com.suanfa.kuaisumi;

public class Demo1 {
    public double myPow(double x, int n) {
        //把n变为long ，防止越界
        long N = n;
        //如果N小于0，把x变成分数，把N变为整数
        if (N < 0) {
            x = 1 / x;
            N *= -1;
        }

        double res = 1;
        while (N > 0) {
            if ((N % 2) == 1) {
                res *= x;
            }

            x *= x;
            N /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.myPow(2, 3);
    }

}
