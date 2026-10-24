class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int aliceStart = getDays(arriveAlice, daysInMonths);
        int aliceEnd = getDays(leaveAlice, daysInMonths);
        int bobStart = getDays(arriveBob, daysInMonths);
        int bobEnd = getDays(leaveBob, daysInMonths);

        int start = Math.max(aliceStart, bobStart);
        int end = Math.min(aliceEnd, bobEnd);

        return start > end ? 0 : end - start + 1;
    }

    private int getDays(String date, int[] daysInMonths) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int total = day;

        for(int i=0; i<month - 1; i++) {
            total += daysInMonths[i];
        }

        return total;
    }
}