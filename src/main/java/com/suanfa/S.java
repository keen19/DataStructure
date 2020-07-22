package com.suanfa;


import java.util.HashSet;
import java.util.Set;

class S {
    public static void main(String[] args) {
        int row=0,column=0;
        int[][] ints = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        Solution solution = new Solution();
        System.out.println(solution.findNumberIn2DArray(ints,15));
//        System.out.println(ints.length);
//        int nums[] = {1, 2, 2, 4, 5, 2};
//        Solution solution = new Solution();
//        int repeatNumber = solution.findRepeatNumber(nums);
//        System.out.println(repeatNumber);
    }
}

//class Solution {
//    public int findRepeatNumber(int[] nums) {
//        int len=nums.length;
//        for(int i=0;i<len;i++){
//            for(int j=i+1;j<len;j++){
//                if(nums[i]==nums[j]){
//                    return nums[i];
//                }
//            }
//        }
//        return -1;
//    }
//}
//class Solution {
//    public int findRepeatNumber(int[] nums) {
//        int repeat=-1;
//        Set<Integer> set=new HashSet<>();
//        for(int a:nums){
//            if(set.add(a)==false){
//                repeat=a;
//                return repeat;
//            }
//        }
//        return repeat;
//    }
//}
class Solution {
    public int findRepeatNumber(int[] nums) {
        //如果没有重复数字，那么正常排序后，数字i应该在下标为i的位置，
        // 所以思路是重头扫描数组，遇到下标为i的数字如果不是i的话，（假设为m),
        // 那么我们就拿与下标m的数字交换。在交换过程中，
        // 如果有重复的数字发生，那么终止返回ture
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        //有多少列
        int lie =matrix[0].length;
        //有多少行
        int hang=matrix.length;

        //定义一个行下标,列下标
        int row=0,column=lie-1;
        //如果最小值大于target或者最大值小于target，则没这个整数
        if(matrix == null || matrix.length == 0){
            return false;
        }
        //以行的最后一个为根结点,把矩阵看作二叉树
        //如果小于当前下标的值则往左一格，反之向下一格
        while(row<hang&&column>=0){
            if(target>matrix[row][column]){
                //往下一格
                row++;
            }else if(target<matrix[row][column]){
                //往左一格
                column--;
            }else{
                return true;
            }
        }
        return false;
    }
}