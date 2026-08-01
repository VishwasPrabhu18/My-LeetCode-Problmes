class Solution {
    public int minimumCost(int[] nums) {
        int minSecond = nums[1];
        int ans = Integer.MAX_VALUE;

        for(int i=2; i<nums.length; i++) {
            ans = Math.min(ans, nums[0] + minSecond +nums[i]);
            minSecond = Math.min(minSecond,nums[i]);
        }
        
        return ans;
    }
}