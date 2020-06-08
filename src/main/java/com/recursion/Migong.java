package com.recursion;

public class Migong {
    public static void main(String[] args) {
        //创建一个二维数组,模拟迷宫
        //地图 8行7列
        int[][] map = new int[8][7];
        //使用1表示墙
        //把地图围起来
        //把上下围起来
        for (int i = 0; i < 7; i++) {
            //第1行
            map[0][i] = 1;
            //第8行
            map[7][i] = 1;
        }
        //把左右围起来
        for (int i = 0; i < 8; i++) {
            //第1列
            map[i][0] = 1;
            //第7列
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


        setWay(map, 1, 1);
        System.out.println("\n小球走过的路");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * map[6][5] 位置为出口
     * map[i][j] 位置为入口
     * <p>
     * 当map[i][j] 为0表示该点没有走过, 1表示为墙,2为可以走
     * 3为已经走过但是走不通
     * <p>
     * 设置一个策略 先走 下->右->上->左
     *
     * @param map 地图
     * @param i   x坐标
     * @param j   y坐标
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //通路找到
            return true;
        } else {
            if (map[i][j] == 0) {
                //该点还没有走过,可以走
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //说明该点是走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //如果map[i][j]!=0 ,可能是1 2 3,
                return false;
            }
        }

    }
}
