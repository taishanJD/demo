package com.example.algorithm.ready.jedi;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * [jedi]
 * 2019-12-25
 * 问题(变种的约瑟夫环问题)
 * 1.n个人编号为0~n-1，围成一圈坐。每个人手里持有一个纸片，纸片上是一个大于等于0的int数字。
 * 2.从0号游戏者从0开始报数，报到0号手里纸片上数字的人被kill掉，淘汰出局，此为一轮游戏。
 * 3.第二轮接着从上一轮被淘汰的下一个相邻游戏者开始继续从0报数，报到此轮第一个报数人手里纸片数字的人被kill掉。
 * 4.以此反复，直到n-1轮后，还剩一个人，问剩下的是几号游戏者。
 * */
public class KillGame {

    public static void main(String[] args) {

        int[] input = {10,17, 4, 28, 13, 6};
        System.out.println("args = [" + solute(input) + "]");

//        System.out.println("args = [" + restList + "]");
    }

    public static int solute(int[] input){

        if (null == input || input.length == 0)
            return -1;
        if (input.length == 1)
            return 0;

        //start变量定义每一轮开始计数的下标
        int start = 0;

        //存一下input数组的下标，每一轮过后都会删除某一个下标
        List<Integer> restList = IntStream.range(0, input.length).boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        for (int i = 0; i < input.length - 1; i++) {
            //重点一：计算哪个下标需要被删除
            int killIndex = input[restList.get(start)] % restList.size();
            restList.remove(killIndex);
            //重点二：计算删除后从哪个下标开始
            start = killIndex % restList.size();
        }
        return restList.get(0);
    }
}
