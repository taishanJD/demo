package com.example.algorithm.ready.jedi;


import com.example.datastructure.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 【jedi】
 * 2019-12-24
 * 问题：二叉树按层次打印，每层换行
 * */
public class BinaryTreeLevelOrderTraversal {

    /**
     * 评分标准
     * no hire
     * 不清楚二叉树遍历，采用错误的递归方法遍历而且跑用例时不清楚遍历顺序。
     * weak hire
     * 能写出层次遍历代码，对分层的处理没有思路。
     * hire
     * 写出层次遍历，分层处理细节存在小问题，让自己跑用例可以自己修正。
     * strong hire
     * 能完全正确代码，并且代码没有bug
     * */
    public static void main(String[] args) {
        Node head = new Node(1);
        head.leftChild = new Node(2);
        head.rightChild = new Node(3);
        head.leftChild.rightChild= new Node(4);
        head.rightChild.leftChild = new Node(5);
        head.rightChild.rightChild = new Node(6);
        head.leftChild.rightChild.leftChild=new Node(7);

        print(head);
    }



    /**
     *
     * 参考 https://yq.aliyun.com/articles/368366
     * 层序遍历与先序、中序、后序遍历不同。层序遍历用到了队列，而先、中、后序需要用到栈。
     *
     * 因此，先、中、后序遍历 可以 采用递归方式来实现，而层序遍历则没有递归方式。
     *
     * 算法步骤：
     *
     * 初始时，根结点入队列
     *
     * 然后，while循环判断队列不空时，弹出一个结点，访问它，并把它的所有孩子结点入队列。
     *
     * 如果要求每层换行的话，需要额外两个变量。一个变量用来保存当前层 还未打印的结点个数，另一个变量保存下一层待打印的结点个数。
     * */
    public static void print(Node head){
        Queue<Node> queue = new LinkedList<>();

        //用来保存当前层 还未打印的结点个数
        int current = 1;

        //保存下一层待打印的结点个数
        int next = 0;

        queue.offer(head);

        while (queue.size() != 0){
            Node currentNode = queue.poll();
            System.out.print(currentNode.data+" ");
            current--;//每出队一个，当前层还未打印的节点数减1
            if (currentNode.leftChild != null){
                queue.offer(currentNode.leftChild);
                next++;//每入队一个，下一层待打印的节点数加1
            }

            if (currentNode.rightChild != null){
                queue.offer(currentNode.rightChild);//
                next++;
            }

            if(current == 0){//当前层全部打印完毕，就换行
                System.out.println();
                current = next;
                next = 0;
            }
        }
    }

}
