package com.example.algorithm.ready.jedi;

import com.example.datastructure.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [Jedi] 求二叉树高度(最大深度)
 * <p>
 * 问题
 * 给一个二叉树根节点，求该二叉树高度
 */
public class HeightOfBT {


    public static void main(String[] args) {
        Node root = Node.bulidTestNode();

        System.out.println("递归方式返回：" + getHeight(root));
        System.out.println("循环方式返回：" + getHeight_1(root));
    }

    /**
     * 参考：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/javashi-xian-san-chong-fang-fa-di-gui-shi-xian-die/
     */

    //思路一：递归/深度优先遍历：用递归算法得到左子树高度和右子树高度，取两者大者，加 1 即整棵树的高度
    //* 时间复杂度O(n)
    //* 空间复杂度:线性表最差O(n)、二叉树完全平衡最好O(logn)
    private static int getHeight(Node root) {
        if (null == root)
            return 0;
        int leftMaxHeight = getHeight(root.leftChild);
        int rightMaxHeight = getHeight(root.rightChild);
        return Math.max(leftMaxHeight, rightMaxHeight) + 1;
    }

    //思路二：循环/广度优先遍历：用一个队列，遍历完一层结点后，高度+1。
    // 难点在于如何判断一层的结点已经遍历完，可以用一个变量保存上一层的结点有多少个子结点，然后下一层的遍历用一个 for 循环控制；
    // 或者更简单，在每一层遍历之前，取队列的 size，然后 for 循环 size 次（开始遍历一层之前，队列里所有的结点就是这一层的结点，没有别层的结点）。
    //时间复杂度O(n)
    //空间复杂度:线性表最差O(n)、二叉树完全平衡最好O(logn)
    private static int getHeight_1(Node root) {
        if (null == root)
            return 0;
        int height = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //这里的逻辑第一次写错了。
            height++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = ((LinkedList<Node>) queue).pollFirst();
                if (null != root.leftChild) {
                    ((LinkedList<Node>) queue).add(root.leftChild);
                }
                if (null != root.rightChild) {
                    ((LinkedList<Node>) queue).add(root.rightChild);
                }
            }
        }
        return height;
    }
}
