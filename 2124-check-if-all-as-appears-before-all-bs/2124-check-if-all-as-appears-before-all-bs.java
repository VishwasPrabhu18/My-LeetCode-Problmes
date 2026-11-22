class Solution {
    public boolean checkString(String s) {
        boolean isValid = true;

        for(char ch: s.toCharArray()) {
            if(ch == 'b') {
                isValid = false;
            } else if(!isValid) {
                return false;
            }
        }

        return true;
    }
}