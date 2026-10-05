class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int score = 0;

                if (innerScore == 0) {
                    score = 1;
                } else {
                    score = 2 * innerScore;
                }

                stack.push(stack.pop() + score);
            }

        }

        return stack.peek();
    }
}