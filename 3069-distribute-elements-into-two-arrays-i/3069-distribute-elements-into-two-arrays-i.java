class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(nums[0]);
        arr2.add(nums[1]);

        for(int i = 2; i < nums.length; i++) {
            if(arr1.getLast() > arr2.getLast()) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        arr1.addAll(arr2);
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            res[i] = arr1.get(i);
        }
        
        return res;
    }
}