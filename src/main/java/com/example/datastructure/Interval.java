package com.example.datastructure;

public class Interval {

    public int left;
    public int right;

    public Interval(int left,int right){
        this.left = left;
        this.right = right;
    }

    public static int compareByLeft(Interval a,Interval b){
        return Integer.compare(a.left,b.left);
    }
}
