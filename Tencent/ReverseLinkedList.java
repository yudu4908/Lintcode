package Tencent;
/**
Reverse a linked list.
 */

 /**
  * 
Definition for ListNode
public class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
  */
public class ReverseLinkedList {
    /**
     *  Example:Input: 1->2->3->null
        Output: 3->2->1->null
     */
    /*
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        //这题虽然返回的是new head一个节点， 但是因为LinkedList是具有.next属性的，可以一个一个把下一个节点找出，等于就是返回
        //整个链表了
        //prev 表示前继节点
        LisNode prev = null; //链表head的前继节点一开始肯定是空
        while (head != null) {
            //temp记录下一个节点，head是当前节点
            ListNode temp = head.next;
            head.next = prev; //颠倒第一个节点的链表顺序
            prev = head;
            head = temp;
        }
        return prev;

    }

}