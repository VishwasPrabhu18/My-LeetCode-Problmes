class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0;
        int cnt1 = 0;
        int cnt2 = 0;

        for (int stone : stones) {
            int rem = stone % 3;

            if (rem == 0) {
                cnt0++;
            } else if (rem == 1) {
                cnt1++;
            } else {
                cnt2++;
            }
        }

        if (cnt0 % 2 == 0) {
            return cnt1 > 0 && cnt2 > 0;
        }

        return Math.abs(cnt1 - cnt2) > 2;
    }
}