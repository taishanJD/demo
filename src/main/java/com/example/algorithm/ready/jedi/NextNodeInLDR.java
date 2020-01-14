package com.example.algorithm.ready.jedi;

import com.example.datastructure.Node;

/**
 * [Jedi] 中序遍历的下一个节点
 *
 * 问题
 * 输入一个二叉树的节点，要求返回按中序遍历的顺序，输入节点的下一个节点。要求 O(1) 空间复杂度。
 * 注意：树中的结点的数据结构中不仅包含左右子结点，同时包含指向父结点的指针parent。
 * 示例
 *        1
 *      /  \
 *    2     3
 *  /  \    / \
 * 4   5   6   7
 *         /
 *        8
 * 以上面的二叉树为例，
 * 输入节点 4，返回节点 2；
 * 输入节点 2，返回节点 5；
 * 输入节点 5，返回节点 1；
 * 输入节点 7，返回节点 null。
 * */
public class NextNodeInLDR {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.leftChild = node2;
        node1.rightChild = node3;

        node2.leftChild = node4;
        node2.rightChild = node5;

        node3.leftChild = node6;
        node3.rightChild = node7;

        node6.leftChild = node8;

        node1.parent = null;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = node2;
        node5.parent = node2;
        node6.parent = node3;
        node7.parent = node3;
        node8.parent = node6;

        System.out.println("2的下一个是："+next(node2).data);
        System.out.println("4的下一个是："+next(node4).data);
        System.out.println("5的下一个是："+next(node5).data);
        System.out.println("7的下一个是："+(next(node7) == null ? "null":next(node7).data));

    }

    private static Node next(Node node){

        if (null == node)
            return null;
        //情况1：如果该节点有右子树，那么他的下一个就是他的右子树的最左孩子节点；否则再进行判断
        if(null != node.rightChild){
            return getMostLeftInRight(node.rightChild);
        }
        //情况2：如果该节点没有右子树，那么情况还需要进一步细分
        return getSubTreeParent(node);
    }

    //获取一个节点的最左孩子节点
    private static Node getMostLeftInRight(Node node){
        while (null != node.leftChild){
            node = node.leftChild;
        }
        return node;
    }

    //节点没有右子树
    private static Node getSubTreeParent(Node node){

        while (null != node.parent){
            //情况1：节点是父节点的左节点，那就直接返回该父节点。
            if (node.parent.leftChild == node){
                return node.parent;
            } else {
                //情况2：节点是父节点的右节点，那就找他的父节点的父节点的父节点...直到遍历到的结点是其父节点的左孩子位置，返回该父结点。
                node = node.parent;
            }
        }
        return null;
    }
}
