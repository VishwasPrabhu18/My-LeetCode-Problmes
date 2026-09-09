class Solution {
    public long countCommas(long n) {
        long total = 0;
        long start = 1000; // 1,000

        for (long commas = 1; start <= n; commas++) {
            long end;

            // Avoid overflow while calculating start * 1000
            if (start > Long.MAX_VALUE / 1000) {
                end = Long.MAX_VALUE;
            } else {
                end = start * 1000 - 1;
            }

            long upper = Math.min(n, end);

            long count = upper - start + 1;

            total += count * commas;

            if (start > Long.MAX_VALUE / 1000) {
                break;
            }

            start *= 1000;
        }

        return total;
    }
}