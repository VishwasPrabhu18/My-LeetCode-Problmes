class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Build the smallest valid interval for each character
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int current = s.charAt(i) - 'a';

                // This character has an occurrence
                // before our current interval.
                if (first[current] < left) {
                    valid = false;
                    break;
                }

                // We must include all occurrences
                // of this character.
                right = Math.max(right, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            // Non-overlapping
            if (left > previousEnd) {

                answer.add(s.substring(left, right + 1));

                previousEnd = right;
            }
        }

        return answer;
    }
}