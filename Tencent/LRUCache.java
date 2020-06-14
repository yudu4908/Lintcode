package Tencent;

import java.util.HashMap;

public class LRUCache {
    /**
     Design and implement a data structure for Least Recently Used (LRU) cache.
      It should support the following operations: get and set.

get(key) Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) Set or insert the value if the key is not already present. 
    When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
Finally, you need to return the data from each get.
     */
    /**
    输入：
LRUCache(2)
set(2, 1)
set(1, 1)
get(2)
set(4, 1)
get(1)
get(2)
输出：[1,-1,1]
解释：
cache上限为2，set(2,1)，set(1, 1)，get(2) 然后返回 1，
set(4,1) 然后 delete (1,1)，因为 （1,1）最少使用，get(1) 然后返回 -1，get(2) 然后返回 1。
     */
    /**
     * 考点：

链表
模拟
题解：
涉及删除和移动操作，使用链表，链表是有序的，一直维护，近期最多使用的放于尾部，
那么每次缓存达到上限的时候，删除头部即可，其余为链表的基础操作模拟即可。
     */
    private class Node {
        Node prev;
        Node next;
        int key;
        int value;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    //constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if (!hs.containsKey(key)) {
            return -1;
        }

        //如果查找的这个key存在的话就先remove current，然后把他移动到tail
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        //move current to tail since it is recently used and should not be deleted from cache next time
        move_to_tail(current); //每次get，使用次数+1，最近使用，放于尾部
        return hs.get(key).value; //这个hashmap通过整数key get到的是一个Node， 再通过Node.value返回真正的值
    }

    public void set(int key, int value) {
        //get 这个方法会把key挪到最末端，因此，不需要再调用 move_to_tail。 注意，set方法也算是使用了一次这个cache，它也不应该被最先删除
        if (get(key) != -1) {
            hs.get(key).value = value;
            return;
        }

        if (hs.size() == capacity) { ////超出缓存上限
            hs.remove(head.next.key); //????删除头部数据， 头部数据是在第一次set时调用到了move_to_tail()插入在head和tail中间的
            head.next = head.next.next;
            head.next.prev = head;
        }
        Node insert = new Node(key, value); //create new Node
        hs.put(key, insert);
        move_to_tail(insert);
    }

    //一开始这个链表只有head 和 tail两个（-1， -1）的Node，当第一次调用set方法时把第一个Node通过下述代码插入到了head 和tail之间
    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}