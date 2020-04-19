package com.example.algorithm.leetcode.string;

import java.util.Arrays;


/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * */
public class LeetCode_242 {

    public static void main(String[] args) {
        System.out.println(isAnagram_2("a","b"));
    }

    //思路一：将两个字符串按照字母排序后重新组合，如果相同则为字母异位词
    //复杂度分析
    //
    //时间复杂度：O(n \log n)O(nlogn)，
    // 假设 nn 是 ss 的长度，排序成本 O(n\log n)O(nlogn) 和比较两个字符串的成本 O(n)O(n)。
    // 排序时间占主导地位，总体时间复杂度为 O(n \log n)O(nlogn)。
    //空间复杂度：O(1)O(1)，
    // 空间取决于排序实现，如果使用 heapsort，通常需要 O(1)O(1) 辅助空间。
    // 注意，在 Java 中，toCharArray() 制作了一个字符串的拷贝，
    // 所以它花费 O(n)O(n) 额外的空间，但是我们忽略了这一复杂性分析，因为：这依赖于语言的细节。
    //这取决于函数的设计方式。例如，可以将函数参数类型更改为 char[]。
    public static boolean isAnagram_1(String s, String t) {

        if (s.length() != t.length()){
            return false;
        }
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();

        Arrays.sort(schar);
        Arrays.sort(tchar);

        return Arrays.equals(schar,tchar);
    }

    //思路2：计算两个字符串中每个字母的出现次数并进行比较
    //我们可以先用计数器表计算 s，然后用 t 减少计数器表中的每个字母的计数器。
    //如果在任何时候计数器低于零，我们知道 t 包含一个不在 s 中的额外字母，并立即返回 FALSE。
    public static boolean isAnagram_2(String s,String t){
        if (s.length() != t.length()){
            return false;
        }

        int[] table = new int[26];

        for (int i = 0;i<s.length();i++){
            table[s.charAt(i) - 'a']++;
        }

        for (int i = 0;i<t.length();i++){
            table[t.charAt(i)-'a']--;
            if (table[t.charAt(i)-'a'] < 0){
                return false;
            }
        }

        return true;
    }
}
