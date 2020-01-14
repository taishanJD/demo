package com.example.algorithm.ready.ninja.numchardecode;

/**
 * 问题
 * 给定一个26个元素的map, 里面
 * a = 1,
 * b = 2,
 * c = 3,
 * ...
 * z = 26
 * 输入一个只包含数字[0-9]的string, 根据上面的map, 输出这个string能被转义成字母string的个数.
 */
public class NumCharDecode {

    public static void main(String[] args) {
//        System.out.println(numDecodings("622"));//2
//        System.out.println(numDecodings("12345"));//3
//        System.out.println(numDecodings("01"));//0
//        System.out.println(numDecodings("20"));//1
//        System.out.println(numDecodings("30"));//0
//        System.out.println(numDecodings("111111"));//13
//        System.out.println(numDecodings("1204"));//1

        System.out.println(numDecodings_1("111111"));//13

    }

    // https://blog.csdn.net/u010002184/article/details/91533966
    public static int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0')
                dp[i] += dp[i - 1];//先看前1位，不为0则相加.在前s[0,i-1]字符后面再加上s[i]这个字符，则dp[i] += dp[i-1]
            int val = Integer.valueOf(s.substring(i - 2, i));
            if (val >= 10 && val <= 26)
                dp[i] += dp[i - 2];//再看前2位，是10-26则相加，在前[0,i-2]字符后面再加上s[i-1,i]这两个字符，则dp[i] += dp[i-2]

            System.out.println("i=" + i + ",s.charAt(i - 1):" + s.charAt(i - 1) + ",s.substring(i - 2, i):" + s.substring(i - 2, i) + ",dp[" + i + "]=" + dp[i]);

        }
        return dp[s.length()];
    }


    //https://blog.csdn.net/zuochao_2013/article/details/78844831
    //暴力递归的方法(时间复杂度O(2^n)，空间复杂度O(n))
    private static int numDecodings_1(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        //字符串转换为数组
        char[] ch = str.toCharArray();

        return process(ch, 0);

    }

    //递归函数(i表示已经处理了的字符串数目)
    private static int process(char[] ch, int i) {
        if (i == ch.length) {
            return 1;
        }
        if (ch[i] == '0') {
            return 0;
        }
        int res = process(ch, i + 1);

        if (i + 1 < ch.length && (ch[i] - '0') * 10 + ch[i + 1] - '0' < 27) {
            res += process(ch, i + 2);
        }
        return res;
    }

    //

}
