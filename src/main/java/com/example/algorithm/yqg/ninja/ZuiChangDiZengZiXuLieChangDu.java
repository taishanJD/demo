package com.example.algorithm.yqg.ninja;


/**
 *  问题
 * 给定长度为N的数组, 返回最长自增子序列的长度
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4 
 * Explanation: 最长自增子序列式 [2,3,7,101], 长度为 4.
 *
 * */
public class ZuiChangDiZengZiXuLieChangDu {

    public static void main(String[] args) {
       int[] nums =  {10,9,2,5,3,4,101,18};
//       System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS_1(nums));
    }

    /**
     *
     * 使用dp数组放置当前升序的子序列，且长度为max
     * 遍历数组nums，找到元素num，在dp中满足dp[i-1] < num < dp[i] 的i，将dp[i] = num
     *
     * nums = [10 9 2 5 3 4 101 18], tail = []
     * i = 0，tail = [10] ，size = 1
     * i = 1，tail = [9]，size = 1，因为9比10小，且没有比9更小的数，所以直接把10替换为9
     * i = 2，tail = [2]，size = 1，同上
     * i = 3，tail = [2, 5]， size = 2， tail的最后一个数字小于5，所以可以直接把5放到后面
     * i = 4，tail = [2, 3]， size = 2， 2 < 3 < 5，所以3相对于5是更优解
     * i = 5，tail = [2,3,4]，size = 3，4 > 3,直接append
     * i = 6，tail = [2,3,4,101]， size = 4， 101 > 4,直接append
     * i = 7， tail = [2,3,4,18], size = 4，4 < 18 < 101。 所以结果为 4
     * 优化： 使用二分法找到 当前数需要插入到tail中的位置
     *
     * */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        for (int num : nums) {
            int low = 0;
            int high = max;

            // 在区间[0, max) 找到
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (dp[mid] < num) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            dp[low] = num;
            if (low == max) {
                max++;
            }
        }
        return max;
    }

    /**
     *  On2 On
     *  最长上升子序列 这个问题可以等价为:
     *  先求每个数字左边的比它小且递增的数字的个数dp[i]，再求dp[]数组中的最大值，结果为max(dp[])+1。
     *  假设dp[i]=n表示第i个数字左边有n个数字比nums[i]小且递增，
     *  那么可知当对于每一个满足0<=j<i的j有：当nums[i]>nums[j]时，dp[i] = dp[j] +1；
     *  在区间[0,i)对j进行遍历, 取最大的dp[j] +1赋值给dp[i].
     *  最终dp[]中的最大值加1即为题目所求。
     *
     * */
    public static int lengthOfLIS_1(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        dp[0] = 0;
        int ans = 0;
        for(int right = 1; right < nums.length; right++) {
            int tmp = 0;
            for(int left = 0; left<right; left++) {
                if(nums[left] < nums[right]) {
                    tmp = Math.max(tmp, dp[left]+1);
                }
            }
            dp[right] = tmp;
            ans = Math.max(ans, dp[right]+1);
        }
        return ans;
    }
}
