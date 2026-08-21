class Solution {
    public long findKthSmallest(int[] coins, int k) {
        
        long low = 1;
        long high = (long) getMin(coins) * k;

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {

        int n = coins.length;
        long total = 0;

        // Inclusion-Exclusion
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    // LCM is already larger than x,
                    // so it cannot contribute any multiple.
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long multiples = x / lcm;

            if ((bits & 1) == 1) {
                total += multiples;
            } else {
                total -= multiples;
            }
        }

        return total;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {

        return a / gcd(a, b) * b;
    }

    private int getMin(int[] coins) {

        int min = coins[0];

        for (int coin : coins) {
            min = Math.min(min, coin);
        }

        return min;
    }
}