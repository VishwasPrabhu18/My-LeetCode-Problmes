class Solution {
    public long sumAndMultiply(int n) {
        int sum = 0, newNum = 0;
        int multiply = 1;

        while (n > 0) {
            int rem = n % 10;
            n /= 10;
            if(rem != 0) {
                newNum = (rem * multiply) + newNum;
                multiply *= 10;
                sum += rem;
            }
        }

        return (long) sum * newNum;
    }
}