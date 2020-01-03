package com.example.algorithm.ready;


import java.util.Stack;

/**
 * [Jedi] 用两个栈实现一个队列
 * <p>
 * 问题
 * class Queue {
 * Stack s1;
 * Stack s2;
 * <p>
 * enqueue(Object o){}
 * Object dequeue(){}
 * }
 * <p>
 * Stack 支持以下操作
 * push(Object o)
 * Object pop()
 * int size()
 * <p>
 * 求 enqueue()（入队）, dequeue()（出队） 实现
 */
public class TwoStackToQueue<T> {

    public static void main(String[] args) {
        Integer a = 12;
        Integer b = 13;
        Integer c = 14;

        TwoStackToQueue<Integer> queue = new TwoStackToQueue<>();
        System.out.println("empty? ==" + queue.empty() + ",size == " + queue.size());
        queue.enqueue(a);
        System.out.println("empty? ==" + queue.empty() + ",size == " + queue.size());
        queue.enqueue(b);

        System.out.println("出栈 ==" + queue.dequeue());
        System.out.println("empty? ==" + queue.empty() + ",size == " + queue.size());
        queue.enqueue(c);
        System.out.println("empty? ==" + queue.empty() + ",size == " + queue.size());

        System.out.println("出栈 ==" + queue.dequeue());
        System.out.println("出栈 ==" + queue.dequeue());
        System.out.println("empty? ==" + queue.empty() + ",size == " + queue.size());
        System.out.println("出栈 ==" + queue.dequeue());

    }

    private Stack<T> stackPush;
    private Stack<T> stackPop;

    public TwoStackToQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /**
     * 思路：
     * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/shi-yong-liang-ge-zhan-yi-ge-zhuan-men-ru-dui-yi-g/
     * 1、使用两个栈，一个栈（stackPush）用于元素进栈，一个栈（stackPop）用于元素出栈；
     * <p>
     * 2、pop() 或者 peek() 的时候：
     * <p>
     * （1）如果 stackPop 里面有元素，直接从 stackPop 里弹出或者 peek 元素；
     * <p>
     * （2）如果 stackPop 里面没有元素，一次性将 stackPush 里面的所有元素倒入 stackPop。
     * <p>
     * 为此，可以写一个 pushToPop 辅助方法，一次性将 stackPush 里的元素倒入 stackPop。
     * <p>
     * 注意：
     * <p>
     * 一定要保证 stackPop 为空的时候，才能把元素从 stackPush 里拿到 stackPop 中。
     */


    //入队
    public void enqueue(T t) {
        stackPush.push(t);
    }

    //出队
    public T dequeue() {
        pushToPop();
        if (!stackPop.empty()) {
            return stackPop.pop();
        }
        throw new RuntimeException("队列空");
    }

    public int size() {
        return stackPush.size() + stackPop.size();
    }

    public boolean empty() {
        return stackPop.empty() && stackPush.empty();
    }

    /**
     * 辅助方法：一次性将 stackPush 里的所有元素倒入 stackPop
     * 注意：1、该操作只在 stackPop 里为空的时候才操作，否则会破坏出队入队的顺序
     * 2、在 peek 和 pop 操作之前调用该方法
     */
    private void pushToPop() {
        if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }
}
