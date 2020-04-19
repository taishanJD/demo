package com.example.algorithm.yqg.ninja;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;

public class LRUCache<K, V> {

    int maxSize = 0;
    int currentSize = 0;
    ListNode head = new ListNode(null, null);
    ListNode tail = new ListNode(null, null);

    HashMap<K, ListNode> hashMap = Maps.newHashMap();

    public LRUCache(int size) {
        this.maxSize = size;
        head.next = tail;
        tail.pre = head;
    }

    interface Loader<K, V> {
        V load(K key);
    }

    //这个是定义的定义存储的Node节点
    class ListNode {
        K key;
        V value;
        ListNode pre;
        ListNode next;

        public ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // 为了验证代码，加了一个ToString，方便测试
        @Override
        public String toString() {
            return "ListNode{" + "key=" + key + ", value=" + value + '}';
        }
    }

    // head的最早，tail的数据最新，新插入的放在tail节点
    public V getData(K key, Loader<K, V> loader) {
        ListNode v = hashMap.get(key);
        // 如果已经存在，则只需要修改相应的指针即可
        if (v != null) {
            // 这个地方也可能再加个当前数量是否等于1的判定，优化下这种情况
            remove(v);
            addToTail(v);
            return v.value;
        }
        V value = loader.load(key);
        ListNode node = new ListNode(key, value);
        if (currentSize >= maxSize) {
            remove(head.next);
            currentSize--;
            hashMap.remove(key);
        }
        addToTail(node);
        hashMap.put(key, node);
        currentSize++;
        return value;
    }

    private void addToTail(ListNode v) {
        v.pre = tail.pre;
        tail.pre.next = v;
        tail.pre = v;
        v.next = tail;
    }

    private void remove(ListNode v) {
        v.pre.next = v.next;
        v.next.pre = v.pre;
    }

    // 方便测试
    @Override
    public String toString() {
        List<ListNode> listNodes = Lists.newArrayList();
        ListNode node = tail;
        while (node.pre != head) {
            listNodes.add(node.pre);
            node = node.pre;
        }
        return listNodes.toString();
    }

    public static void main(String[] args) {
        LRUCache<String, String> lru = new LRUCache<>(5);

        // 定义了一个传入什么就输出什么的loader
        Loader<String, String> loader = key -> key;

        lru.getData("1", loader);
        lru.getData("2", loader);
        lru.getData("3", loader);
        System.out.println(lru);
        lru.getData("1", loader);   // 把1调成最新的数据
        System.out.println(lru);
        lru.getData("4", loader);
        lru.getData("5", loader);   // 现在容器满了
        System.out.println(lru);
        lru.getData("6", loader);   // 逐出2
        System.out.println(lru);
    }
}
