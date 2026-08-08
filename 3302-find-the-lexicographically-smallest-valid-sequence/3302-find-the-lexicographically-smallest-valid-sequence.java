class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // left[i] = earliest index in word1 that can match
        // word2[i] while matching word2[0..i-1] exactly.
        int[] left = new int[m];

        int p = 0;

        for (int i = 0; i < m; i++) {

            while (p < n && word1.charAt(p) != word2.charAt(i)) {
                p++;
            }

            if (p == n) {
                break;
            }

            left[i] = p++;
        }

        // right[i] = latest index in word1 that can match
        // word2[i] while matching word2[i+1..m-1] exactly.
        int[] right = new int[m];

        p = n - 1;

        for (int i = m - 1; i >= 0; i--) {

            while (p >= 0 && word1.charAt(p) != word2.charAt(i)) {
                p--;
            }

            if (p < 0) {
                break;
            }

            right[i] = p--;
        }

        int[] ans = new int[m];

        int prev = -1;
        boolean usedMismatch = false;

        for (int i = 0; i < m; i++) {

            boolean found = false;

            /*
             * We only need to check indices between prev + 1
             * and the earliest possible exact-match index.
             *
             * If word1[j] == word2[i], we can use j without
             * consuming the mismatch.
             *
             * Otherwise, j can be used as the one mismatch
             * if the remaining suffix can still be matched.
             */

            int limit = n - (m - i);

            for (int j = prev + 1; j <= limit; j++) {

                if (word1.charAt(j) == word2.charAt(i)) {

                    ans[i] = j;
                    prev = j;
                    found = true;
                    break;

                } else if (!usedMismatch) {

                    boolean suffixPossible = true;

                    if (i + 1 < m) {
                        /*
                         * After choosing j, we need to match
                         * word2[i+1..m-1].
                         *
                         * right[i+1] is the latest possible
                         * position for word2[i+1].
                         */
                        suffixPossible = right[i + 1] > j;
                    }

                    if (suffixPossible) {

                        ans[i] = j;
                        prev = j;
                        usedMismatch = true;
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                return new int[0];
            }
        }

        return ans;
    }
}