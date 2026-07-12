class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;

        int rev = 0;
        int n = x;
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return n == rev;
    }
}