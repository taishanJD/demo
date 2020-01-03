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

    //写死一个树用来测试
    public static Node bulidTestNode(){
        Node a = new Node(8);
        Node b = new Node(3);
        Node c = new Node(10);
        Node d = new Node(1);
        Node e = new Node(6);
        Node f = new Node(14);
        Node g = new Node(4);
        Node h = new Node(7);
        Node i = new Node(13);

        a. leftChild = b;
        a. rightChild = c;
        b. leftChild = d;
        b. rightChild = e;
        c. rightChild = f;
        e. rightChild = g;
        e. rightChild = h;
        f. leftChild = i;

        return a;
    }

    public void display(){
        System.out.println(data);
    }
}
