class Solution {
    public int maxProduct(int n) {
        int largest = -1;
        int secondLargest = -1;
        int largestCount = 0;

        while (n > 0) {
            int digit = n % 10;

            if (digit > largest) {
                secondLargest = largest;
                largest = digit;
                largestCount = 1;
            } else if (digit == largest) {
                largestCount++;
            } else if (digit > secondLargest) {
                secondLargest = digit;
            }

            n /= 10;
        }

        if (largestCount >= 2) {
            return largest * largest;
        }

        return largest * secondLargest;
    }
}