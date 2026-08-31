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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;

        int idx = 1;

        int firstCriticalPoint = -1, prevCriticalPoint = -1;

        int minDistance = Integer.MAX_VALUE, maxDistance = -1;

        while (curr.next != null) {
            ListNode next = curr.next;

            boolean isCriticalPoint = (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);

            if (isCriticalPoint) {
                if (firstCriticalPoint == -1) {
                    firstCriticalPoint = idx;
                } else {
                    minDistance = Math.min(minDistance, idx - prevCriticalPoint);
                    maxDistance = idx - firstCriticalPoint;
                }

                prevCriticalPoint = idx;
            }

            prev = curr;
            curr = next;
            idx++;
        }

        if (firstCriticalPoint == -1 || firstCriticalPoint == prevCriticalPoint) {
            return new int[] { -1, -1 };
        }

        return new int[] { minDistance, maxDistance };
    }
}