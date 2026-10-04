class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for(char c: s.toCharArray()) {
            if(c == '(') {
                minOpen++;
                maxOpen++;
            } else if(c == ')') {
                minOpen--;
                maxOpen--;
            } else {
                // if it is * then it can be ( or )
                minOpen--;
                maxOpen++;
            }

            // Too many closing parentheses
            if (maxOpen < 0) {
                return false;
            }

            // '*' can be empty, so minimum cannot go below 0
            minOpen = Math.max(0, minOpen);
        }

        return minOpen == 0;
    }
}