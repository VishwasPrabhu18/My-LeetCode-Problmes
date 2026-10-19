class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstPos = findFirst(nums, target);
        if(firstPos == -1){
            return new int[]{-1,-1};
        }
        int lastPos = findLast(nums, target);
        
        return new int[]{firstPos, lastPos};
    }

    public int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1;
            }  else if (nums[mid] < target) {
                left = mid + 1;
            }  else {
                right = mid - 1;
            }
        }
        return result;
    }

    public int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1;
            }   else if (nums[mid] < target) {
                left = mid + 1;
            }   else {
                right = mid - 1;
            }
        }

        return result;
    }
}