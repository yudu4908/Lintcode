package Tencent;
//Given a linked list, determine if it has a cycle in it.
public class LinkedListCycle {
    /**
    Example 1:
		Input: 21->10->4->5,  then tail connects to node index 1(value 10).
		Output: true
		
	Example 2:
		Input: 21->10->4->5->null
		Output: false
     */
    /**
    Given a linked list, determine if it has a cycle in it.

    Follow up:
    Can you solve it without using extra space?

快慢指针的经典题。
快指针每次走两步，慢指针一次走一步。
在慢指针进入环之后，快慢指针之间的距离每次缩小1，所以最终能相遇。
     */
    public Boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}