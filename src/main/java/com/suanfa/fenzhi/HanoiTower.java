package com.suanfa.fenzhi;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * @param num 盘的数目
     * @param a   初始柱子
     * @param b   中间柱子
     * @param c   目标柱子
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第 1 个盘从" + a + "->" + c);
        } else {
            //盘的数目大于1个,把最大的盘看成一个整体,把其他盘看成另一个整体
            //先把上面的盘移到B柱子,以B为目标柱子,C为中间柱子
            hanoiTower(num - 1, a, c, b);
            //把最大的盘移到C柱子
            System.out.println("第 " + num + " 个盘从 " + a + "->" + c);
            //把B柱子的盘移到C盘,以C为目标柱子,B为中间柱子
            hanoiTower(num - 1, b, a, c);
        }
    }
}
