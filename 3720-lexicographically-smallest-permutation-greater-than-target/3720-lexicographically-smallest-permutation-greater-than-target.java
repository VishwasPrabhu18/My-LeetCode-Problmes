class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] ans = new char[n];

        for (int i = 0; i < n; i++) {

            int t = target.charAt(i) - 'a';

            // Try to keep the prefix equal to target
            if (freq[t] > 0) {

                ans[i] = target.charAt(i);
                freq[t]--;

                continue;
            }

            // Cannot match target[i].
            // Try to put the smallest character > target[i].
            for (int c = t + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    ans[i] = (char) ('a' + c);
                    freq[c]--;

                    fillRemaining(ans, i + 1, freq);

                    return new String(ans);
                }
            }

            /*
             * Cannot make the current position greater.
             * Backtrack to an earlier position.
             */
            for (int j = i - 1; j >= 0; j--) {

                // Restore the character used at j.
                freq[ans[j] - 'a']++;

                int targetChar = target.charAt(j) - 'a';

                // Try a character greater than target[j].
                for (int c = targetChar + 1; c < 26; c++) {

                    if (freq[c] > 0) {

                        ans[j] = (char) ('a' + c);
                        freq[c]--;

                        fillRemaining(ans, j + 1, freq);

                        return new String(ans);
                    }
                }
            }

            return "";
        }

        /*
         * Entire target was matched exactly.
         * We need a strictly greater permutation,
         * so backtrack from the last position.
         */
        for (int i = n - 1; i >= 0; i--) {

            // Restore ans[i]
            freq[ans[i] - 'a']++;

            int targetChar = target.charAt(i) - 'a';

            // Find smallest character greater than target[i].
            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    ans[i] = (char) ('a' + c);
                    freq[c]--;

                    fillRemaining(ans, i + 1, freq);

                    return new String(ans);
                }
            }
        }

        return "";
    }

    private void fillRemaining(char[] ans, int start, int[] freq) {

        int pos = start;

        for (int c = 0; c < 26; c++) {

            while (freq[c] > 0) {
                ans[pos++] = (char) ('a' + c);
                freq[c]--;
            }
        }
    }
}