package com.example.algorithm.yqg.ninja;

import java.util.*;

/**
 * https://blog.csdn.net/ZeromaXHe/article/details/91622341
 *  问题
 * 一个无序正整数数组，求数组中最长连续序列的长度，时间复杂度越简单越好
 * 示例
 * Input: [100, 4, 200, 5, 3, 2] 无序整数数组
 * Output: 4 (最长连续序列[2, 3, 4, 5]的长度)
 *
 * */
public class ZuiChangLianXuZiXuLie {

    public static void main(String[] args) {
        int[] nums = {100,4,200,5,3,2};
//        System.out.println(longestConsecutive(nums));

        System.out.println(longestConsecutive_1(nums));
    }

    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> countForNum = new HashMap<>();
        for (int num : nums) {
            countForNum.put(num, 1);
        }
        for (int num : nums) {
            forward(countForNum, num);
        }
        return maxCount(countForNum);
    }

    private static int forward(Map<Integer, Integer> countForNum, int num) {
        if (!countForNum.containsKey(num)) {
            return 0;
        }
        int cnt = countForNum.get(num);
        if (cnt > 1) {
            return cnt;
        }
        cnt = forward(countForNum, num + 1) + 1;
        countForNum.put(num, cnt);
        return cnt;
    }

    private static int maxCount(Map<Integer, Integer> countForNum) {
        int max = 0;
        for (int num : countForNum.keySet()) {
            max = Math.max(max, countForNum.get(num));
        }
        return max;
    }

    //以下是暴力解法，时间复杂度On3，空间复杂度O1
    private static boolean arrayContains(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
    public static int longestConsecutive_1(int[] nums) {
        int longestStreak = 0;

        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;

            while (arrayContains(nums, currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }

        return longestStreak;
    }
    //暴力解法结束

    //以下是排序解法
    /**
     * 在我们开始算法之前，
     * 首先检查输入的数组是否为空数组，如果是，函数直接返回 0 。
     * 对于其他情况，我们将 nums 数组排序，并考虑除了第一个数字以外的每个数字与它前一个数字的关系。
     * 如果当前数字和前一个数字相等，那么我们当前的序列既不会增长也不会中断，我们只需要继续考虑下一个数字。
     * 如果不相等，我们必须要检查当前数字是否能延长答案序列（也就是 nums[i] == nums[i-1] + 1）。
     * 如果可以增长，那么我们将当前数字添加到当前序列并继续。
     * 否则，当前序列被中断，我们记录当前序列的长度并将序列长度重置为 1 。
     * 由于 nums 中的最后一个数字也可能是答案序列的一部分，
     * 所以我们将当前序列的长度和记录下来的最长序列的长度的较大值返回。
     * */
    //时间复杂度：O(nlgn)
    //算法核心的 for循环恰好运行 n次，所以算法的时间复杂度由 sort 函数的调用决定，
    // 通常会采用 O(nlgn)O(nlgn) 时间复杂度的算法。
    //空间复杂度：O(1)（或者 O(n)）
    //以上算法的具体实现中，由于我们将数组就低排序，所以额外的空间复杂度是常数级别的。
    // 如果不允许修改输入数组，我们需要额外的线性长度的空间来保存中间结果和排好序的数组。
    public static int longestConsecutive_2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }
        return Math.max(longestStreak, currentStreak);
    }


    /**
     * 以下是借助额外空间解法 On On
     * */
    public static int longestConsecutive_3(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }


}
