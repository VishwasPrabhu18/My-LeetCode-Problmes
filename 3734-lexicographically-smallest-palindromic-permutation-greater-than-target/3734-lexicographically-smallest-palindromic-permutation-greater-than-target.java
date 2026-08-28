class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check whether a palindrome is possible.
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;

        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        char[] half = new char[halfLen];

        // Try to match target's first half.
        for (int i = 0; i < halfLen; i++) {

            int t = target.charAt(i) - 'a';

            if (halfFreq[t] > 0) {

                half[i] = target.charAt(i);
                halfFreq[t]--;

            } else {

                // Try to make this position greater.
                for (int c = t + 1; c < 26; c++) {

                    if (halfFreq[c] > 0) {

                        half[i] = (char) ('a' + c);
                        halfFreq[c]--;

                        fillSmallest(half, i + 1, halfFreq);

                        return buildPalindrome(half, middle);
                    }
                }

                // Cannot increase here.
                // Backtrack.
                return backtrack(half, halfFreq, target, i - 1, middle);
            }
        }

        /*
         * We matched the entire first half.
         *
         * IMPORTANT:
         * The palindrome itself might already be > target.
         */
        String palindrome = buildPalindrome(half, middle);

        if (palindrome.compareTo(target) > 0) {
            return palindrome;
        }

        // Otherwise we need to increase the first half.
        return backtrack(half, halfFreq, target, halfLen - 1, middle);
    }

    private String backtrack(
            char[] half,
            int[] freq,
            String target,
            int start,
            int middle) {

        for (int i = start; i >= 0; i--) {

            // Restore the character currently at i.
            freq[half[i] - 'a']++;

            int targetChar = target.charAt(i) - 'a';

            // Find smallest available character > target[i].
            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    half[i] = (char) ('a' + c);
                    freq[c]--;

                    fillSmallest(half, i + 1, freq);

                    return buildPalindrome(half, middle);
                }
            }
        }

        return "";
    }

    private void fillSmallest(
            char[] half,
            int start,
            int[] freq) {

        int pos = start;

        for (int c = 0; c < 26; c++) {

            while (freq[c] > 0) {

                half[pos++] = (char) ('a' + c);
                freq[c]--;
            }
        }
    }

    private String buildPalindrome(char[] half, int middle) {

        int halfLen = half.length;

        int n = halfLen * 2 + (middle == -1 ? 0 : 1);

        char[] result = new char[n];

        // First half
        for (int i = 0; i < halfLen; i++) {
            result[i] = half[i];
        }

        // Middle character
        if (middle != -1) {
            result[halfLen] = (char) ('a' + middle);
        }

        // Reverse of first half
        for (int i = 0; i < halfLen; i++) {
            result[n - 1 - i] = half[i];
        }

        return new String(result);
    }
}