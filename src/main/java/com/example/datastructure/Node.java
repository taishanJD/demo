package com.example.datastructure;


/**
 * 二叉树的节点结构
 * */
public class Node {

    public int data;
    public Node leftChild;
    public Node rightChild;

    public Node(int data) {
        this.data = data;
    }

    public void display(){
        System.out.println(data);
    }
}
