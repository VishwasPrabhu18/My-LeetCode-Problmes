class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;

        long NEG = Long.MIN_VALUE / 4;

        long inc = NEG;
        long dec = NEG;
        long tri = NEG;

        long ans = NEG;

        for (int i = 1; i < n; i++) {
            long newInc = NEG;
            long newDec = NEG;
            long newTri = NEG;

            if (nums[i - 1] < nums[i]) {
                // Start a new increasing segment
                newInc = (long) nums[i - 1] + nums[i];

                // Extend increasing segment
                if (inc != NEG) {
                    newInc = Math.max(newInc, inc + nums[i]);
                }

                // Start final increasing phase
                // after increasing -> decreasing
                if (dec != NEG) {
                    newTri = Math.max(newTri, dec + nums[i]);
                }

                // Extend final increasing phase
                if (tri != NEG) {
                    newTri = Math.max(newTri, tri + nums[i]);
                }
            }

            if (nums[i - 1] > nums[i]) {
                // Start decreasing phase from increasing phase
                if (inc != NEG) {
                    newDec = Math.max(newDec, inc + nums[i]);
                }

                // Continue decreasing phase
                if (dec != NEG) {
                    newDec = Math.max(newDec, dec + nums[i]);
                }
            }

            inc = newInc;
            dec = newDec;
            tri = newTri;

            ans = Math.max(ans, tri);
        }

        return ans;
    }
}