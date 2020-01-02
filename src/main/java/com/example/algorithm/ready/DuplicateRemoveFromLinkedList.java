package com.example.algorithm.ready;


import com.example.datastructure.ListNode;

/**
 * [Jedi] 有序链表去重
 * 2019-12-30
 * 问题
 * 给定一个排序链表（默认正整数），删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 * （简化版：给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。）
 * <p>
 * 示例
 * 输入: 1->1->2->3
 * 输出: 2->3
 * <p>
 * 简化版示例
 * 输入: 1->1->2->3->3->4
 * 输出: 1->2->3->4
 */
public class DuplicateRemoveFromLinkedList {

    public static void main(String[] args) {

        int[] data = {1, 1, 2, 3, 3, 4};
        ListNode head = ListNode.build(data);
        System.out.println("输入：" + ListNode.print(head));
//        ListNode res1 = deleteDuplicates(head);
//        System.out.println("删除所有重复元素后：" + ListNode.print(res1));
        ListNode res2 = deleteduplictes_1(head);
        System.out.println("保留一个重复元素后：" + ListNode.print(res2));

    }

    //删除所有
    //链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/xun-huan-jie-fa-jian-dan-gao-xiao-tu-jie-by-wu-yan/
    public static ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode temp = new ListNode(-1); //使用一个临时节点放在开头
        temp.next = head;
        ListNode pre;
        ListNode cur = temp;

        while (null != cur) {
            pre = cur;
            cur = cur.next;
            while (null != cur && null != cur.next && cur.val == cur.next.val) {
                int val = cur.val;
                while (null != cur && cur.val == val) {
                    cur = cur.next;
                }
            }
            pre.next = cur;
        }
        return temp.next;
    }

    //保留一个
    public static ListNode deleteduplictes_1(ListNode head) {

        ListNode cur = head;
        while (null != cur && null != cur.next){
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

}
