package com.example.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * https://www.nowcoder.com/practice/2baa6aba39214d6ea91a2e03dff3fbeb?tpId=37&tqId=21242&tPage=1&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 题目描述
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 *
 * 处理：
 *
 *
 * 1、 记录最多8条错误记录，循环记录（或者说最后只输出最后出现的八条错误记录），对相同的错误记录（净文件名称和行号完全匹配）只记录一条，错误计数增加；
 *
 *
 * 2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
 *
 *
 * 3、 输入的文件可能带路径，记录文件名称不能带路径。
 *
 * 输入
 * E:\V1R2\product\fpgadrive.c   1325
 * E:\V1R2\product\fpgadrive.c   1325
 * 输出
 * fpgadrive.c 1325 2
 * */
public class CuoWuJiLu {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        HashMap<String,Integer> map = new LinkedHashMap<>();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line != null && !line.isEmpty()){
                String[] lines = line.split("\\s+"); //以一个或多个空白字符分割
                String name = lines[0].substring(lines[0].lastIndexOf("\\")+1);
                if (name.length()>16){
                    name = name.substring(name.length()-16,name.length());
                }
                name = name + " " + lines[1];
                Integer count = map.get(name);
                if (count == null ){
                    map.put(name,1);
                } else {
                    map.put(name,count+1);
                }
            } else {
                break;
            }
        }
        int count = 0;
        for (HashMap.Entry e:map.entrySet()) {
            if (map.size()-count<=8){
                System.out.println(e.getKey()+" "+e.getValue());
            }
            count++;
        }


        //以下是优化后的答案
//        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
//        Map<String,Integer> m=new LinkedHashMap<String,Integer>();
//        String tstr=null;
//        while((tstr = cin.readLine()) != null && !tstr.equals("")){      //&& !tstr.equals(""))没有性能影响
//            String[] str=tstr.split("\\s+");
//            String fname=str[0].substring(str[0].lastIndexOf("\\")+1);
//            fname=fname.substring(Math.max(fname.length()-16 ,0))+" "+str[1];  //max 最快推荐 ？：也可以 if太麻烦
//            Integer tmp=m.get(fname);  //get==null较快写法
//            if(tmp==null)
//                m.put(fname,1);
//            else
//                m.put(fname, tmp+1);
//        }
//        int cnt=0;
//        for(Map.Entry<String,Integer> it:m.entrySet()){
//            if(m.size()-cnt<=8)
//                System.out.println(it.getKey()+" "+it.getValue());
//            cnt++;
//        }
    }
}
