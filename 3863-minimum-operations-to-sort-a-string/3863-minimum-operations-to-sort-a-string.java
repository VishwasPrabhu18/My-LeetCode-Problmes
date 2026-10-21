class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int[] freq = new int[26];

        boolean sorted = true;

        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;

            if (i > 0 && s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char min = 'a';
        char max = 'z';

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                min = (char) ('a' + i);
                break;
            }
        }

        for (int i = 25; i >= 0; i--) {
            if (freq[i] > 0) {
                max = (char) ('a' + i);
                break;
            }
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        if (first == min || last == max) {
            return 1;
        }

        if (first == max && last == min
                && freq[min - 'a'] == 1
                && freq[max - 'a'] == 1) {
            return 3;
        }

        return 2;
    }
}