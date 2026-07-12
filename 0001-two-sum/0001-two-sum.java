class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentSum = target - nums[i];

            if (sumMap.containsKey(currentSum)) {
                return new int[] { sumMap.get(currentSum), i };
            }

            sumMap.put(nums[i], i);
        }

        return new int[] {};
    }
}