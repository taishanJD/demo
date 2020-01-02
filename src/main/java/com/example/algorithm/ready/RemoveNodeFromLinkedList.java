package com.example.algorithm.ready;


import com.example.datastructure.ListNode;

import java.util.Stack;

/**
 * [Jedi] 单向链表结点查找删除
 * 2019-12-30
 * 问题
 * Remove all elements from a linked list of integers that have value val.
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 */
public class RemoveNodeFromLinkedList {

    public static void main(String[] args) {
        int[] datas = {6, 1, 2, 6, 3, 4, 5, 6};
        ListNode head = ListNode.build(datas);
        System.out.println("Given:" + ListNode.print(head));

//        ListNode solute1 = solute1(head,6);
//        System.out.println("solute1 return:"+ListNode.print(solute1));

        ListNode solute = solute(head, 6);
        System.out.println("solute return:" + ListNode.print(solute));
    }

    // 参考：https://blog.csdn.net/gkq_tt/article/details/87902662
    // 时间复杂度O(n) 空间复杂度O(1)
    public static ListNode solute(ListNode head, int val) {

        //重点：找到第一个值不是val的node，以此为head，就相当于把开头的n个val删除
        while (null != head) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }

        ListNode cur = head;
        ListNode pre = head;
        while (null != cur) {
            if (cur.val == val) {
                //如果当前节点的值就是val，则把pre的next指向cur的next
                pre.next = cur.next;
            } else {
                //如果不是，pre就向后一位
                pre = cur;
            }
            //逻辑判断完之后，cur向后一位，继续遍历
            cur = cur.next;
        }
        return head;
    }

    // 时间复杂度O(n) 空间复杂度O(n)
    public static ListNode solute1(ListNode head, int val) {
        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            if (head.val != val) {
                stack.push(head);
            }
            head = head.next;
        }

        //这边很关键，如何把栈转化为链表顺序不倒。比如1，2，3入栈后，返回的链表是1->2->3
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }
}
