class Solution {
    public int sumCounts(List<Integer> nums) {
        int count = 0;

        for(int i=0; i<nums.size(); i++){

        Set<Integer> uniqueSet = new HashSet<>();

            for(int j=i; j<nums.size(); j++){
                uniqueSet.add(nums.get(j));
                int distinct = uniqueSet.size();
                
                count += distinct * distinct;
            }
        }

        return count;
    }
}