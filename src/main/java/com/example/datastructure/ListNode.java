package com.example.datastructure;


/**
 * 链表节点
 * */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        this.val = x;
    }

    public static ListNode build(int[] data){
        ListNode head = new ListNode(data[0]);
        ListNode current = head;
        for (int i = 1; i < data.length; i++) {
            ListNode temp = new ListNode(data[i]);
            current.next = temp;
            current = temp;
        }
        return head;
    }

    public static String print(ListNode head){
        String res = "";
        while (null != head){
            res += head.val+"->";
            head = head.next;
        }
        return res+"null";
    }
}