class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        // bestPrefix[i] = shortest target-sum subarray
        // completely inside arr[0...i]
        int[] bestPrefix = new int[n];

        Arrays.fill(bestPrefix, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;

        int best = Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int currentLength = right - left + 1;

                // There is a previous non-overlapping subarray
                if (left > 0 &&
                  bestPrefix[left - 1] != Integer.MAX_VALUE) {

                    answer = Math.min(
                      answer,
                      currentLength + bestPrefix[left - 1]
                    );
                }

                // Current subarray can become the best previous one
                best = Math.min(best, currentLength);
            }

            bestPrefix[right] = best;
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}