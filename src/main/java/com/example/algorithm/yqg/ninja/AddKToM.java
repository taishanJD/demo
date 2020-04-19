package com.example.algorithm.yqg.ninja;

import java.util.ArrayList;
import java.util.List;


/**
 * 问题
 * 给定随机数组 data[]，寻找指定k个数的和为m的所有组合
 * 示例
 * 输入
 * int data[] = {1,2,3,4,5,6,8,10};
 * int k = 2;
 * int m = 7;
 * 返回
 * {{1, 6}, {2, 5}, {3, 4}}
 *
 * 回溯算法
 */
public class AddKToM {

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,6,7,8,9,10};
        int count = 3;
        int sum = 7;

        List<List<Integer>> list = findData(data,count,sum);
        System.out.println(list);

    }

    public static List<List<Integer>> findData(int[] data, int count, int sum) {
        List<List<Integer>> resList = new ArrayList<>();
        if (count > 0) {
            backtrackData(data, count, sum, -1, new ArrayList<>(), resList);
        }
        return resList;
    }

    private static void backtrackData(int[] data, int count, int sum, int lastIndex, List<Integer> tmpList, List<List<Integer>> result) {
        if (count == 0) {
            if (sum == 0) {
                result.add(new ArrayList<>(tmpList));
            }
            return;
        }
        for (int i = lastIndex+1; i < data.length; i++) {
            tmpList.add(data[i]);
            backtrackData(data, count - 1, sum - data[i], i, tmpList, result);
            tmpList.remove(tmpList.size()-1);
        }
    }
}
