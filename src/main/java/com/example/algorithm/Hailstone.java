package com.example.algorithm;

import java.util.ArrayList;
import java.util.List;


/**
 * hailstone序列
 *  (1）输入一个正整数n；
 * （2）如果n=1则结束；
 * （3）如果n是奇数，则n变为3n+1，否则n变为n/2；
 * （4）转入第（2）步。
 * 结果最后一个总是1，但n的大小和序列的长度似乎是不成正比的，n=27时，序列就很长。
 *
 * 猜想：对于任意的一个序列，长度是否是有限的？目前还没有结论。
 * 基于算法特性：有输入输出，正确性，可行性，有穷性。
 * 所以：以下的小程序未必是一个算法。
 */
public class Hailstone {

    public static void main(String[] args){
        System.out.println(getHailstoneArr(27));
    }

    static List<Integer> getHailstoneArr(int n){
        List<Integer> res = new ArrayList<>();
        res.add(n);
        while (n>1){
            n = n % 2 ==0?n/2:n*3+1;
            res.add(n);
        }
        return res;
    }
}
