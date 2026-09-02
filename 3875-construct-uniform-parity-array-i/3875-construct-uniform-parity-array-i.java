class Solution {
    public boolean uniformArray(int[] nums1) {
        if(nums1.length == 1) return true;
        
        for(int num: nums1) {
            if(num % 2 != 0) return true;
        }
        
        return true;
    }
}