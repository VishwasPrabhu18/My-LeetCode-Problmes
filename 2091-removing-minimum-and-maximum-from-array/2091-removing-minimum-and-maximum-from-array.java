class Solution {
    public int minimumDeletions(int[] nums) {
        int minIdx = 0, maxIdx=0;
        int len = nums.length;

        for(int i = 0; i<len; i++){
            if(nums[i] < nums[minIdx]){
                minIdx = i;
            }
            if(nums[i] > nums[maxIdx]){
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        int bothFront = right + 1;
        int bothBack = len - left;

        int leftFrontRightBack = left + 1 + (len - right);
        int rightFrontLeftBack = right + 1 + (len - left);

        return Math.min(
            Math.min(bothFront, bothBack),
            Math.min(leftFrontRightBack, rightFrontLeftBack)
        );
    }
}