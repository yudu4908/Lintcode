/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */

public class NToLastSolution {
    //找到单链表倒数第n个节点
     /*
     * @param head: The first node of linked list.
     * @param n: An integer
     * @return: Nth to last node of a singly linked list. 
     */
    //两个指针，先让快指针走n步， 然后一起走，快指针到头的时候，慢指针就是倒数第n个。
    public ListNode nthToLast(ListNode head, int n) {
        if (head == null || n < 1) {
            return null;
        }

        ListNode p1 = head;
        ListNode p2 = head;
        for (int j = 0; j < n - 1; ++j) { //这个位置是因为倒数的时候只有倒数第1没有倒数第0，所以这里是n - 1；背下来即可
            if (p2 == null) { //比如单链表中只有三个元素你却要让他找倒数第五个元素那显然找不到，返回null
                return null;
            }
            p2 = p2.next;
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}