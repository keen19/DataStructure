package com.suanfa.KMP;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1="你好好好你你";
        String s2="好你你";

        int i = violenceMatch(s1, s2);
        System.out.println("索引:"+i);
    }

    /**
     *
     * @param str1 目标字符串
     * @param str2  匹配的字符串
     * @return  str1下标
     */
    public static int violenceMatch(String str1, String str2) {
        //把字符串转换为字符数组
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        //s1的长度
        int s1Len = s1.length;
        //s2的长度
        int s2Len = s2.length;

        //s1的索引
        int i = 0;
        //s2的索引
        int j = 0;

        while (i < s1Len && j < s2Len) {
            //当前字符匹配成功
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                //匹配错误,i减去j索引走的次数,并+1,往后移一位,继续重新匹配
                i = i - j + 1;
                //使j重新指向第一位字符
                j = 0;
            }
        }
        //如果j的长度移动到s2的长度则匹配完成,返回i-j的索引
        if (j == s2Len) {
            return i - j;
        } else {
            //匹配失败
            return -1;
        }
    }
}
