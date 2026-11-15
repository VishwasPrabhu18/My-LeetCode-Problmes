class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;

        long evenCount = 1;
        long oddCount = 0;
        long prefixSum = 0;
        long result = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 1) {
                result = (result + evenCount) % MOD;
                oddCount++;
            } else {
                result = (result + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) result;
    }
}