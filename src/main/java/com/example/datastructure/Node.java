package com.example.datastructure;


/**
 * 二叉树的节点结构
 * */
public class Node {

    public int data;
    public Node leftChild;
    public Node rightChild;
    public Node parent; //2020-1-5 为了NextNodeInLDR一题加的

    public Node(int data) {
        this.data = data;
    }

    /**
     * * 测试例子：（n指null）
     *  *              8
     *  *          3       10
     *  *      1     6    n   14
     *  *   n   n  4  7 n  n n  15
     * */
    public static Node bulidTestNode(){
        Node a = new Node(8);
        Node b = new Node(3);
        Node c = new Node(10);
        Node d = new Node(1);
        Node e = new Node(6);
        Node f = new Node(14);
        Node g = new Node(4);
        Node h = new Node(7);
        Node i = new Node(15);

        a. leftChild = b;
        a. rightChild = c;
        b. leftChild = d;
        b. rightChild = e;
        c. rightChild = f;
        e. leftChild = g;
        e. rightChild = h;
        f. rightChild = i;

        a.parent = null;
        b.parent = a;
        c.parent = a;
        d.parent = b;
        e.parent = b;
        f.parent = c;
        g.parent = e;
        h.parent = e;
        i.parent = f;

        return a;
    }

    public void display(){
        System.out.println(data);
    }
}
