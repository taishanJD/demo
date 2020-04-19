package com.example.algorithm.yqg.jedi;

import com.example.datastructure.ListNode;

import java.util.HashSet;

/**
 * [Jedi] 求两条相交单链表的相交节点
 * <p>
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * 问题
 * 已知两条相交单链表，求相交节点。
 * <p>
 * 例子：
 * 4->1->8->4->5  5->0->1->8->4->5 返回8
 * 这里注意，相交节点是指同一节点。如果两边的1属于两个不同节点，只是值相同的话，那么相交节点就不是1。
 */
public class IntersectionOfTwoLinkedLists {


    public static void main(String[] args) {
        ListNode intersectionNode = ListNode.build(new int[]{8, 4, 5});

        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(1);
        head1.next.next = intersectionNode;

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(0);
        head2.next.next = new ListNode(1);
        head2.next.next.next = intersectionNode;

        System.out.println("head1 == " + ListNode.print(head1));
        System.out.println("head2 == " + ListNode.print(head2));

        System.out.println("set实现：" + ListNode.print(getIntersectionNode(head1, head2)));
        System.out.println("双指针实现：" + ListNode.print(getIntersectionNode_1(head1, head2)));
    }


    //链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/dong-hua-yan-shi-160-xiang-jiao-lian-biao-by-user7/

    //利用set实现
    //时间复杂度 O(n) 空间复杂度O(n)
    public static ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        HashSet<ListNode> set = new HashSet<>();

        //我们先遍历链表a，将a链表的所有节点放入一个set中。
        while (null != head1) {
            set.add(head1);
            head1 = head1.next;
        }
        //之后再遍历b链表，如果b链表的某个节点出现在set中，那么就找到了第一个相交的节点
        while (null != head2) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }
        return null;
    }

    //双指针方式
    //时间复杂度 O(n) 空间复杂度O(1)
    public static ListNode getIntersectionNode_1(ListNode head1, ListNode head2) {
        ListNode l1 = head1;
        ListNode l2 = head2;

        //定义两个指针，分别从两链表头开始，只要两指针不相等，就继续
        while (l1 != l2) {
            //重点步骤：当l1、l2某一个到达尾端时，继续指向对方的开头，继续走，最终一定会相遇
            //如果有交点，会在交点处相遇，若没有则一定会在尾端null处相遇
            l1 = l1 == null ? head2 : l1.next;
            l2 = l2 == null ? head1 : l2.next;
        }
        return l1;
    }
}
