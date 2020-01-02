package com.example.algorithm.ready;

import com.example.datastructure.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * 【jedi】
 * 2019-12-25
 * 问题
 * 定义 Interval 的数据结构
 * Interval {
 *   int left;
 *   int right;
 * }
 * 输入一个 List 的 Interval，要求把所有有重合的 Interval 合并。
 * 示例
 * 输入
 * [(1,3), (5, 6), (2,6), (8,9)]
 * 返回
 * [(1,6), (8,9)]
 */
public class MergeIntervals {

    public static void main(String[] args) {

        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1,3));
        input.add(new Interval(5,6));
        input.add(new Interval(2,6));
        input.add(new Interval(8,9));


        List<Interval> result = solute(input);
        System.out.println();

    }

    /**
     * 思路：
     * 1、以左端为基准排序
     * 2、从第一个开始依次向下判断是否能合并，能合并就合并，到了某一个不能合并，则从这个开始继续
     *
     * */
    public static List<Interval> solute(List<Interval> input){

        input.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.left,o2.left);
            }
        });

        List<Interval> result = new ArrayList<>();

        Interval current = input.get(0);
        result.add(current);
        for (int i = 1; i < input.size(); i++) {
            if(!merge(current,input.get(i))){
                current = input.get(i);
                result.add(current);
            }
        }
        return result;
    }

    //// 尝试合并 Interval a 和 b，
    // 如果 a b 有重合，则 a 为合并后的新 Interval，方法返回 true；否则返回 false。
    private static boolean merge(Interval a,Interval b){

        //a，b重合的判断：a的右端大于b的左端
        //a，b合并：取a，b右端中较大的那个作为a的右端，然后返回a
        if (a.right > b.left){
            a.right = Math.max(a.right,b.right);
            return true;
        }
        return false;
    }
}
