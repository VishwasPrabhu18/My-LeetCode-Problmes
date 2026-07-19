class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];

        // Store the last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Skip if already included
            if (visited[c - 'a']) continue;

            // Remove larger characters that appear again later
            while (!stack.isEmpty()
                    && stack.peekLast() > c
                    && last[stack.peekLast() - 'a'] > i) {

                visited[stack.pollLast() - 'a'] = false;
            }

            stack.offerLast(c);
            visited[c - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pollFirst());
        }

        return ans.toString();
    }
}