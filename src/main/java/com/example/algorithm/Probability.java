package com.example.algorithm;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Probability {

    public static void main(String[] args) {
        String line = "dadfafe@123 fa@ @@@qwe问我  @jiadao_贾岛 ";
//        String line = "@123 @123  ";
        ArrayList<String> list = match(line);
        System.out.println(list);
    }

    /**
     * 抽奖
     *
     * @param percent
     * @return
     */
    public boolean draw4Prize(int percent) {

        Random random = new Random();
        int num = random.nextInt(100);
        if (num <= percent)
            return true;
        return new Random().nextInt(100) <= percent;
    }

    public static ArrayList<String> match(String line) {
        ArrayList<String> res = new ArrayList<>();
        Pattern p = Pattern.compile("@[a-zA-Z0-9_\\u4e00-\\u9fa5]+\\s+");
        Matcher m = p.matcher(line);
        while (m.find()) {
            res.add(m.group().trim());
        }
        return res;
    }

}
