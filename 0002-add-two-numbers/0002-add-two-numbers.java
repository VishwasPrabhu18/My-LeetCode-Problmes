/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0, sum = 0;

        ListNode result = new ListNode();
        if (l1 != null || l2 != null) {
            int total = l1.val + l2.val;
            if (total > 9) {
                result.val = total % 10;
                carry = 1;
            } else {
                result.val = total;
            }
        }

        ListNode current = result;
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null || l2 != null) {
            int num1 = 0, num2 = 0;
            if (l1 != null) {
                num1 = l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                num2 = l2.val;
                l2 = l2.next;
            }

            int total = num1 + num2 + carry;
            if (total > 9) {
                carry = 1;
                current.next = new ListNode(total % 10);
                current = current.next;
            } else {
                carry = 0;
                current.next = new ListNode(total);
                current = current.next;
            }
        }

        if (carry == 1) {
            current.next = new ListNode(carry);
            current = current.next;
        }

        return result;
    }
}