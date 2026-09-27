class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            
            if(stack.peek() == ')') {
                stack.pop(); // removing top ')'
                Queue<Character> queue = new LinkedList<>();
                while(!stack.peek().equals('(')) {
                    queue.offer(stack.pop());
                }
                stack.pop(); // removing top '('
                while(!queue.isEmpty()) {
                    stack.push(queue.remove());
                }
            }
        }
        
        stack.forEach(result::append);
        
        return result.toString();
    }
}