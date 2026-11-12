class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int incCount = 1;
        int decrCount = 1;
        int maxCount = 1;

        for(int i=0; i<nums.length - 1; i++) {
            if(nums[i] < nums[i+1]) {
                incCount++;
                decrCount = 1;
            } else if(nums[i] > nums[i+1]) {
                incCount = 1;
                decrCount++;
            } else {
                incCount = 1;
                decrCount = 1;
            }

            maxCount = Math.max(maxCount, Math.max(incCount, decrCount));
        }
        return maxCount;
    }
}