package com.example.algorithm.ready.jedi;


import com.example.datastructure.ListNode;
import java.util.Stack;

/**
 * [jedi]
 * 2019-12-26
 * 问题
 * 两个不定长单向链表，将链表的数据按右对齐的方式，对应位相加，给出一个新链表。相加结果超过10，向前进位。
 * 不推荐使用链表反转方式
 * 示例
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *
 * */
public class AddTwoLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(7);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);
        node1.next.next.next = new ListNode(3);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        ListNode result = solute(node1,node2);

        //2019-12-26 写错的一点：链表遍历条件，写成了while (null != result.next)
        while (null != result){
            System.out.print(result.val+ " ");
            result = result.next;
        }
    }


    /**
     * 解法一 双栈算法
     * 思路：遍历两个链表，将指向节点的指针存入两个栈中。从栈顶开始取节点的值求和。每求出一对节点的和，出栈节点。直到两栈都为空。
     *
     * 例如：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     *
     * 遍历链表得出两个栈。A=[7,2,4,3],B=[5,6,4]。
     *
     * 栈顶求和3+4=7。栈顶弹出。A=[7,2,4],B=[5,6]。
     *
     * 栈顶求和4+6=10。值为0，进1。栈顶弹出。A=[7,2],B=[5]。
     *
     * 栈顶求和2+5+1=8。栈顶弹出。A=[7],B=[]。
     *
     * 栈顶求和7+0=7。栈顶弹出。A=[],B=[]。
     *
     * 如此，结果是7,8,0,7。
     *
     * 思路：
     *
     * s1，s2:两个栈;
     * for(ListNode x in l1){
     *     x 入 s1;
     * }
     * for(ListNode x in l2){
     *     x 入 s2;
     * }
     * d = 0;
     * while(s1不空 || s2不空){
     *     if(s1不空) d+=s1栈顶出栈;
     *     if(s2不空) d+=s2栈顶出栈;
     *     值d % 10，挂入结果链表;
     *     进位 d /=10;
     * }
     * if(d){
     *    d，挂入结果链表;
     * }
     *
     * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii/solution/shi-yong-liang-ge-zhan-by-tui-qiao/
     * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii/solution/liang-chong-jie-fa-by-jason-2-31/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public static ListNode solute(ListNode l1,ListNode l2){
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while(l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;//进位
        ListNode head = null; //返回结果

        //判断carry!=0原因是 当两个栈都为空了，也就是所有对位都相加完毕后，最后还有进位
        while (!stack1.empty() || !stack2.empty() || carry != 0 ){
            int value = 0;//相加的和

            if (!stack1.empty()){
                value += stack1.pop();
            }
            if (!stack2.empty()){
                value += stack2.pop();
            }

            value += carry;

            ListNode node = new ListNode( value % 10);
            node.next = head;
            head = node;
            carry = value / 10;
        }
        return head;
    }


}
