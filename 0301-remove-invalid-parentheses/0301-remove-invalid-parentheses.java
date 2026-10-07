class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String cur = queue.poll();

                if(isValid(cur)) {
                    result.add(cur);
                    found = true;
                    continue;
                }

                if(found) {
                    continue;
                }

                for(int j = 0; j < cur.length(); j++) {
                    char ch = cur.charAt(j);
                    if(ch != '(' && ch != ')') {
                        continue;
                    }

                    String next = cur.substring(0, j) + cur.substring(j + 1);

                    if(visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return result;
    }

    private boolean isValid(String s) {
        int balance = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                balance++;
            }  else if(ch == ')') {
                balance--;

                if(balance < 0) {
                    return false;
                }
            }
        }
        
        return balance == 0;
    }
}