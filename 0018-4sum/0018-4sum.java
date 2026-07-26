class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();

        int n = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            // Skip 1st duplicate num
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            for (int j = i + 1; j < n - 2; j++) {
                // Skip 2nd duplicate num
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        resultList.add(
                                Arrays.asList(
                                        nums[i], nums[j], nums[left], nums[right]));

                        left++;
                        right--;

                        // Skip 3rd duplicate num
                        while (left < right && nums[left] == nums[left - 1])
                            left++;

                        // Skip 4th duplicate num
                        while (left < right && nums[right] == nums[right + 1])
                            right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return resultList;
    }
}