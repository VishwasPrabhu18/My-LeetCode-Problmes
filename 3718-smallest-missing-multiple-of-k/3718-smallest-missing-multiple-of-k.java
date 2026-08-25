class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> uniqueSet = new HashSet<>();

        for(int num: nums) {
            uniqueSet.add(num);
        }

        int multipleOfK = k;
        while(uniqueSet.contains(multipleOfK)) {
            multipleOfK += k;
        }
        return multipleOfK;
    }
}