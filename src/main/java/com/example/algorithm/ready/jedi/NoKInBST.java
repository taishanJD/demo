package com.example.algorithm.ready.jedi;


import com.example.datastructure.Node;
import java.util.Stack;

/**
 * [Jedi] 求二叉树的第k大(小)值
 * 2020-1-2
 * 例子：（n指null）
 *              8
 *          3       10
 *      1     6    n   14
 *   n   n  4  7 n  n n  13
 *
 *  返回 10
 * */
public class NoKInBST {

    private static int count = 0; //递归中需要一个外部的全局变量计数


    /**
     * 评分标准
     no hire
    对二叉搜索树树没有概念，写不出。
    weak hire
    理解二叉树，二叉搜索树数据结构，思路清晰，经提示至少写出递归算法，
    hire
    较为迅速写出任一一种算法。
    strong hire
    两种算法都顺畅完成。*/
    public static void main(String[] args) {
        Node a = Node.bulidTestNode();
        int k = 3;

        getKthBiggest(a,k);
        getKthBiggest_1(a,k);
    }

    /**
     * 思路：
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/di-gui-yu-fei-di-gui-xie-fa-tong-li-wan-cheng-di-1/
     * 二叉搜索树BST有一个重要性质：中序遍历为排序数组，
     *  根据这个性质，我们可将问题转化为寻找中序遍历第 kk 个节点的值；
     *  可以用递归和非递归两种方式
     */

    // 递归方式 找最大：先访问右孩子，再访问左孩子。反之找最小。
    private static void getKthBiggest(Node root, int k){
        if (null == root)
            return;
        getKthBiggest(root.rightChild,k);
        count++;
        if (count == k){
            System.out.println("使用递归方式得出答案："+root.data);
        }
        getKthBiggest(root.leftChild,k);
    }

    // 非递归方式
    private static void getKthBiggest_1(Node root, int k){
        Stack<Node> stack = new Stack<>();
        while (null != root || !stack.empty()){

            //一直找当前节点的右孩子，那么最终在栈顶的一定是值最大的节点。若找最小，那就找左孩子。
            while (null != root){
                stack.push(root);
                root = root.rightChild;
            }

            if (!stack.empty()){
                //栈顶出栈，也就是最大值就出来了，这样就完成了一次排序，当完成k次时就可以输出了
                root = stack.pop();
                --k;
                if (k == 0)
                    System.out.println("使用非递归方式得出答案："+root.data);
                // 接下来最大的值一定是该节点的左孩子的最右节点（如果有的话），
                // 所以接下来就开始遍历左孩子，再次进入上边的while
                root = root.leftChild;
            }
        }
    }


}
